
NpatLoop(\kick, 16,
  \instrument, \modkick,
  \decay, 1.2,
  \ramp, 4,
  \rampdecay, 0.002,
  \moddepth, Pwrand([0.5, 2, 4], [10, 2, 1].normalizeSum, inf),
  \moddecay, 0.5,
  \pfreq, 50,
  \gain, 1.5,
  \freq, 50,
  \dur, Pseq([2, 3, 1, 3, 2, 2, Pn(1/4, 4), 2, 2], inf) / 2,
)
Ndef(\kick).quant = 8;
