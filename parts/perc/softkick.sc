Npat(\kick,
  \loop, 16,
  \instrument, \modkick,
  \decay, 0.8,
  \ramp, 4,
  \rampdecay, 0.03,
  \modDepth, 0.6,
  \modDecay, 0.5,
  \gain, 2.5,
  \freq, 50,
  \dur, Pseq([2, 2, 3, 3, 4, 2, 2, 2], inf) / 2,
)
Ndef(\kick).quant = 8;
