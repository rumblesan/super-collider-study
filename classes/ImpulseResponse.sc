ImpulseResponseFolder {
  var <folderPath, <fftsizes, <lookup, <buffers;

  *new { |server, folderPath, fftsizes|
    ^super.newCopyArgs(folderPath, fftsizes).init(server)
  }

  init { |server|
    var files;
    lookup = Dictionary.new;
    files = folderPath.entries;
    buffers = Array.new();

    folderPath.entries.do({|sf, i|
      if (sf.extension == "wav", {
        fftsizes.do({|fftsize|
          var b = ImpulseResponse(server, sf.fullPath, fftsize);
          var key = "%-%".format(sf.fileNameWithoutExtension, fftsize).asSymbol;
          buffers = buffers.add(b);
          lookup[key] = b;
        });
      });
    });
    "loaded % samples from %\n".postf(lookup.size, folderPath);
  }

  at {|key|
    ^if(key.isNumber, {buffers[key]}, {lookup[key]})
  }

  size { ^buffers.size }

  keys { ^lookup.keys }

}

ImpulseResponse {
  var <filepath, <fftsize, <buffer, <bufnum;

  *new { |server, filepath, fftsize|
    ^super.newCopyArgs(filepath, fftsize).init(server)
  }

  init { |server|
    var b, irSpectrumSize;
    Buffer.readChannel(server, filepath, channels:[0], action: {|loaded|
      irSpectrumSize = PartConv.calcBufSize(fftsize, loaded);
      buffer = Buffer.alloc(server, irSpectrumSize, 1);
      buffer.preparePartConv(loaded, fftsize);
      bufnum = buffer.bufnum;
    });
  }

}
