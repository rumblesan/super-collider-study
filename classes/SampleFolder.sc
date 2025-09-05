SampleFolder {
  var <folderPath, <lookup, <buffers;

  *new { |folderPath|
    ^super.newCopyArgs(folderPath, Dictionary.new, Array.new)
  }

  *loadFolderAsync { |server, folderPath, finalAction|
    var sampleFolder = SampleFolder.new(folderPath);
    Continuation(
      folderPath.entries,
      { |sampleFile, continuation|
        var key, b;
        key = sampleFile.fileNameWithoutExtension.asSymbol;
        b = Buffer.readChannel(
          server, sampleFile.fullPath, channels:[0], action:
          {
            sampleFolder.add(key, b);
            continuation.value;
          }
        );
      },
      { finalAction.value(sampleFolder) }
    ).value();
  }

  at {|key|
    ^if(key.isNumber, {buffers[key]}, {lookup[key]})
  }

  add {|key, buffer|
    lookup[key] = buffer;
    buffers = buffers.add(buffer);
    ^this;
  }

  size { ^buffers.size }

}
