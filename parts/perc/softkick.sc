
Ndef(\softkick).quant = 4;
Ndef(\softkick)[0] = Pbind(
  \instrument, \modkick,
  \amp, Pseq([1.3, 1, 1], inf),
  \decay, 1,
  \rdecay, 0.02,
  \mdecay, 0.3,
  \pfreq, 50,
  \ramp, 3,
  \gain, 1.5,
  \mod, 0.5,
  \freq, 50,
  \dur, Pseq([3, 3, 2], inf)/2,
  )
)
Ndef(\softkick).clear
