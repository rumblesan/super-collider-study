(
  SynthDef(\granulizer, {arg out=0, in, bufferLength=5,
    density=5, randDensity=0, size=0.1,
    position=0, rate=1, spread=0.1,
    feedback=0.1, drywet=0.2;
    var signal = (LocalIn.ar(1) * feedback) + in;
    var audio;
    var frames = bufferLength * SampleRate.ir;
    var phase = Phasor.ar(0, 1, 0, frames);
    var buffer = LocalBuf.new(frames, 1);
    var pos = (
      (phase / frames) - position + LFNoise2.kr(0.1).range(0, spread)
    ).wrap(0, 1 - ControlDur.ir);
    var trigs = Impulse.ar(density) + Dust.ar(randDensity);

    BufWr.ar(signal, buffer, phase, loop: 1);
    audio = GrainBuf.ar(1, trigs, size, buffer, rate, pos, 2, 0);
    LocalOut.ar(audio);
    Out.ar(
      out,
      XFade2.ar(in, audio, (drywet * 2) - 1)
    );
  }, [0, \ar, \ir]).add;
)
