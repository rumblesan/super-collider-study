(
  SynthDef(\granulizer, {arg out=0,
    density=5, randDensity=0, size=0.1,
    position=0.0, rate=1, spread=0.0,
    feedback=0.0, drywet=0.2;
    var in = \in.ar(0);
    var signal = (LocalIn.ar(1) * feedback) + in;
    var bufferLength = \bufferLength.ir(5);
    var grainAudio, output;
    var frames = bufferLength * SampleRate.ir;
    var buffer = LocalBuf.new(frames, 1);
    var phase = Phasor.ar(0, 1, 0, frames);
    var trigs = Impulse.ar(density) + Dust.ar(randDensity);
    var pos = (
      (((phase / frames)
        - position)
        - ControlDur.ir)
      - Demand.ar(trigs, 0, Diwhite((spread.neg * 100), (spread * 100)) / 100)
    ).wrap(0, 1);

    BufWr.ar(signal, buffer, phase, loop: 1);

    grainAudio = GrainBuf.ar(1, trigs, size, buffer, rate, pos, 2, 0);
    LocalOut.ar(grainAudio);
    output = Mix.new([
      grainAudio * drywet,
      in * (1 - drywet)
    ]);
    Out.ar(out, output);
  }).add;
)
