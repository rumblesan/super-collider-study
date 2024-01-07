SampleFolder {
  var <folderPath, <lookup, <buffers;

  *new { |server, folderPath, action|
    ^super.newCopyArgs(folderPath, Dictionary.new, Array.new).init(server, action)
  }

  init { |server, finalAction|
    Continuation.iterator(folderPath.entries,
      { |sampleFile, continuation|
        var key = sampleFile.fileNameWithoutExtension.asSymbol;
        var b = Buffer.readChannel(server, sampleFile.fullPath, channels:[0], action:
        {
          lookup[key] = b;
          buffers = buffers.add(b);
          continuation.value;
        });
      },
    ).value({|samplefolder| finalAction.value(this) });
  }

  at {|key|
    ^if(key.isNumber, {buffers[key]}, {lookup[key]})
  }

  size { ^buffers.size }

}
