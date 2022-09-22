WaveTableFile {
  var <filepath, <waves, <frames,
  <bufnum, <buffers;

  *new { |server, filepath, frameCount, waveCount|
    ^super.newCopyArgs(filepath, waveCount, frameCount).init(server)
  }

  init { |server|
    var tempfile, raw;
    tempfile = SoundFile.openRead(filepath);
    if (tempfile.numFrames < (waves * frames), {
      "% file is not large enough to have % wavetables of % samples\n".postf(filepath, waves, frames);
      ^nil
    });
    raw = FloatArray.newClear(waves * frames);
    tempfile.readData(raw);
    tempfile.close;
    // buffers are double the size because of the wavetable structure
    buffers = Buffer.allocConsecutive(waves, server, frames * 2, 1);

    waves.do({ |i|
      var offset, signal, wavetable;
      offset = i * frames;
      signal = Signal.newFrom(
        raw.copyRange(offset, offset + (frames - 1))
      );
      wavetable = signal.asWavetable;
      buffers[i].loadCollection(wavetable);
    });

    bufnum = buffers[0].bufnum;
  }

  at {|index|
    ^buffers[index]
  }

}


/*
  s.boot;
  x = WaveTableFile.new(s, "./wavetables/pistonhonda/1.wav", 256, 64);
  x[0].plot
*/
