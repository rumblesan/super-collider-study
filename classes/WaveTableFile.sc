WaveTableFile {
  var <filePath, <waves, <frames,
  <bufnum, <buffers;

  *new { |filePath, waveCount, frameCount|
    ^super.newCopyArgs(filePath, waveCount, frameCount)
  }

  *loadFolderAsync { |server, filePath, waveCount, frameCount, finalAction|
    var tempfile, raw, waveTableFile, waveTableBuffers;
    tempfile = SoundFile.openRead(filePath);
    if (tempfile.numFrames < (waveCount * frameCount), {
      "% file is not large enough to have % wavetables of % samples\n".postf(filePath, waveCount, frameCount);
      ^nil
    });
    raw = FloatArray.newClear(waveCount * frameCount);
    tempfile.readData(raw);
    tempfile.close;
    waveTableFile = WaveTableFile.new(filePath, waveCount, frameCount);
    // buffers are double the size because of the wavetable structure
    waveTableBuffers = Buffer.allocConsecutive(waveCount, server, frameCount * 2, 1);

    Continuation(waveTableBuffers,
      { |buffer, continuation, idx|
        var offset, signal, wavetable;
        offset = idx * frameCount;
        signal = Signal.newFrom(
          raw.copyRange(offset, offset + (frameCount - 1))
        );
        wavetable = signal.asWavetable;
        buffer.loadCollection(wavetable, action: continuation);
      },
      {
        waveTableFile.add(waveTableBuffers);
        finalAction.value(waveTableFile);
      }
    ).value();
  }

  at {|index|
    ^buffers[index]
  }

  add {|waveTableBuffers|
    buffers = waveTableBuffers;
    bufnum = buffers[0].bufnum;
    ^this;
  }

}
