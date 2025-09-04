
NPatLoop(\kick, 16,
  \instrument, \modkick,
  \amp, Pseq([1.3, 1, 1], inf),
  \decay, 0.5,
  \rdecay, 0.002,
  \mdecay, 0.3,
  \pfreq, 50,
  \ramp, 2,
  \gain, 1.5,
  \mod, 0.5,
  \freq, 50,
  \dur, Pseq([2], inf),
)
Ndef(\kick).quant = 8;
