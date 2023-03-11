SampleFolder {
  var <folderPath, <lookup, <buffers;

  *new { |server, folderPath|
    ^super.newCopyArgs(folderPath).init(server)
  }

  init { |server|
    var files;
    lookup = Dictionary.new;
    files = folderPath.entries;
    buffers = Array.new();

    folderPath.entries.do({|sf, i|
      var b = Buffer.readChannel(server, sf.fullPath, channels:[0]);
      var key = sf.fileNameWithoutExtension.asSymbol;
      buffers = buffers.add(b);
      lookup[key] = b;
    });
    "loaded % samples from %\n".postf(files.size, folderPath);
  }

  at {|key|
    ^if(key.isNumber, {buffers[key]}, {lookup[key]})
  }

  size { ^buffers.size }

}
