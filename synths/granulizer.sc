(
  SynthDef(\granulizer, {arg out=0, in, bufferLength=5,
    feedback=0,
    density=0, size=0.1, speed=1, position=0, spread=0;
    var buffer, signal, trig, posPhasor, posSpread, output;
    buffer = LocalBuf.new(bufferLength * SampleRate.ir, 1);
    signal = (LocalIn.ar(1) * feedback) + in;
    RecordBuf.ar(signal, buffer);
    //trig = Impulse.kr(density);
    trig = Dust.kr(density);
    posPhasor = LFSaw.kr(1/bufferLength, mul: 0.5, add: 0.5);
    posSpread = (posPhasor + position + (WhiteNoise.ar(mul: 0.5, add: 0.5) * spread)) % 1;
    output = GrainBuf.ar(2, trig, size, buffer, speed, posSpread, 2, 0);
    LocalOut.ar(output);
    Out.ar(out, output);
  }, [0, \ar, \ir]).add;
)

/*
  ~granmix = { ~ping  }
  ~gran[0] = \granulizer
  ~gran <<> ~granmix
  ~gran.play
  ~gran.set(\density, 50)
  ~gran.set(\size, 0.1)
  ~gran.set(\speed, 1.0)
  ~gran.set(\position, 0.3)
  ~gran.set(\spread, 0.1)
  ~gran.set(\feedback, 0.1)

  (
    ~gran[1] = \pset -> Pbind(
      \density, Pwrand([10, 50, 250], [5, 10, 2].normalizeSum, inf),
      \speed, Pseq([1, 1, 0.25, 3, 1, 1], inf),
      \size, Pwrand([0.01, 0.1, 0.7], [3, 15, 1].normalizeSum, inf),
      \dur, Pseq([0.5, 0.5, 0.25, 0.25], inf),
    )
  )
*/
