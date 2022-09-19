WaveTableFile {
  var <filepath, <waves, <frames,
  <bufnum, <buffers;

  *new { |server, filepath, frameCount, waveCount|
    ^super.newCopyArgs(filepath, waveCount, frameCount).init(server)
  }

  init { |server|
    buffers = Buffer.allocConsecutive(waves, server, frames, 1);
    Buffer.read(server, filepath, action: {
      |tempbuf|
      waves.do({ |i|
        tempbuf.copyData(buffers[i], srcStartAt: i * frames, numSamples: frames);
      });
      tempbuf.free;
    });
    bufnum = buffers[0].bufnum;
  }

  at {|index|
    ^buffers[index]
  }

}
