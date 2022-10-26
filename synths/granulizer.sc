(
  SynthDef(\granulizer, {arg out=0, in, bufferLength=5,
    preLevel=0,
    density=0, size=0.1, position=0, spread=0.01, speed=1, repeats=1;
    var buffer = LocalBuf.new(bufferLength * SampleRate.ir, 1);
    var repeatTrig = Impulse.kr(repeats);
    var repeatTime = repeats.reciprocal;
    var pos = (position * repeatTime) + WhiteNoise.ar(mul: spread);
    RecordBuf.ar(in, buffer, loop: 1, preLevel: preLevel, trigger: repeatTrig);
    Out.ar(
      out,
      GrainBuf.ar(2, Impulse.kr(density), size, buffer, speed, pos, 2, 0)
    );
  }, [0, \ar, \ir]).add;
)

/*
  ~granmix = { (~arpverb)  }
  ~gran[0] = \granulizer
  ~gran <<> ~granmix
  ~gran.play
  ~gran.stop
  ~gran.set(\density, 350)
  ~gran.set(\size, 0.02)
  ~gran.set(\speed, 2.0)
  ~gran.set(\spread, 0.1)
  ~gran.set(\preLevel, 0.0)
  ~gran.set(\repeats, 1.0)
  ~gran.set(\position, 0.4)

  (
    ~gran[1] = \pset -> Pbind(
      \density, Pseq([Pn(12, 7), Pn(150, 2), 50], inf),
      \preLevel, 0.7,
      \speed, Pseq([1.0], inf),
      \size, Pseq([Pn(1, 3), Pn(0.2, 2), Pn(0, 1), 0.3], inf),
      \spread, Prand([0.3, 0, 0], inf),
      \position, (Pseq([Pn(0.003, 5), Pn(0.1, 2)], inf) + 0.11),
      \repeats, Pseq([0.5], inf),
      \dur, Pseq([0.5, 0.5, 0.25, 1.25], inf),
    )
  )

*/
