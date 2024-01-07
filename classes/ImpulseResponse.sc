ImpulseResponseFolder {
  var <folderPath, <fftsizes, <lookup, <buffers;

  *new { |server, folderPath, fftsizes, action|
    ^super.newCopyArgs(folderPath, fftsizes).init(server, action)
  }

  init { |server, action|
    var wavFiles = folderPath.entries.select({ |file| file.extension == "wav" });
    lookup = Dictionary.new;
    buffers = Array.new();

    Continuation.iterator([wavFiles, fftsizes].allTuples,
      {|sfSize, continuation|
        var sf = sfSize[0];
        var fftsize = sfSize[1];
        var key = "%_%".format(sf.fileNameWithoutExtension, fftsize).asSymbol;
        var b = ImpulseResponse(server, sf.fullPath, fftsize, {
          "    loaded % at fft size %\n".postf(sf.fullPath, fftsize);
          buffers = buffers.add(b);
          lookup[key] = b;
          continuation.value;
        });
      },
    ).value({ action.value(this); });
  }

  at {|key|
    ^if(key.isNumber, {buffers[key]}, {lookup[key]})
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
