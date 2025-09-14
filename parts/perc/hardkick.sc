Npat(\kick,
  \loop, 16,
  \instrument, \modkick,
  \decay, 0.5,
  \ramp, Pseq([Pn(10, 3), 20], inf),
  \rampdecay, Pseq([Pn(0.02, 3), Pwrand([0.02, 0.1, 0.5], [10, 2, 1].normalizeSum, 3)], inf),
  \modDecay, 0.1,
  \modNoise, 0.1,
  \modDepth, 1.1,
  \ratio, 1.7,
  \freq, 50,
  \gain, 3,
  \dur, Pseq([2, 2, 3, Pr(3, 1), 4, 2, Pr(4, 1), 4], inf) / 2,
)
Ndef(\kick).quant = 8;

Npat(\harderkick,
  \loop, 24,
  \instrument, \bkick,
  \amp, Pseq([1.1, 1, 1], inf),
  \decay, 2,
  \rampattack, 0.01,
  \rampdecay, 0.2,
  \ramp, Pwrand([4, 8, 12], [15, 4, 1].normalizeSum, inf),
  \octave, 3,
  \gain, 8,
  \dur, Pseq([
    2, 1, 2, Pn(0.25, 4), 2, Pn(2, 2), Pn(0.25, 8), Pn(2, 1)
  ], inf) / 2,
)
