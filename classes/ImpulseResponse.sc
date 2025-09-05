ImpulseResponseFolder {
  var <folderPath, <fftSizes, <lookup, <buffers;

  *new { |folderPath, fftSizes|
    ^super.newCopyArgs(folderPath, fftSizes, Dictionary.new, Array.new)
  }

  *loadFolderAsync { |server, folderPath, fftSizes, finalAction|
    var irFolder = ImpulseResponseFolder.new(folderPath, fftSizes);
    var wavFiles = folderPath.entries.select({ |file| file.extension == "wav" });
    var combinations = [wavFiles, fftSizes].allTuples;
    Continuation(combinations,
      {|sfInfo, continuation|
        var sf = sfInfo[0];
        var fftsize = sfInfo[1];
        var key = "%_%".format(sf.fileNameWithoutExtension, fftsize).asSymbol;
        var b = ImpulseResponse(server, sf.fullPath, fftsize, {
          irFolder.add(key, b);
          continuation.value;
        });
      },
      { finalAction.value(irFolder) }
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

  keys { ^lookup.keys }

}

ImpulseResponse {
  var <filepath, <fftsize, <buffer, <bufnum;

  *new { |server, filepath, fftsize, action|
    ^super.newCopyArgs(filepath, fftsize).init(server, action)
  }

  init { |server, finalAction|
    Buffer.readChannel(server, filepath, channels:[0], action: {|loaded|
      var irSpectrumSize = PartConv.calcBufSize(fftsize, loaded);
      buffer = Buffer.alloc(server, irSpectrumSize, 1);
      buffer.preparePartConv(loaded, fftsize);
      bufnum = buffer.bufnum;
      finalAction.value(this);
    });
  }

}
