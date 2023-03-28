
Ndef(\softkick).quant = 4;
Ndef(\softkick)[0] = Pbind(
  \instrument, \modkick,
  \amp, Pseq([1.1, 1, 1], inf),
  \decay, 2,
  \rdecay, 0.08,
  \mdecay, 0.3,
  \pfreq, 50,
  \ramp, 1,
  \mod, 0.5,
  \freq, 50,
  \dur, 4,
  )
)
