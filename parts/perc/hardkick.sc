
Ndef(\hardkick).quant = 4;
Ndef(\hardkick)[0] = Pbind(
  \instrument, \modkick,
  \amp, Pseq([1.1, 1, 1], inf),
  \decay, 1,
  \rampdecay, 0.1,
  \moddecay, 1.8,
  \pfreq, 300,
  \ramp, 10,
  \moddepth, 1.2,
  \freq, 50,
  \gain, 15,
  \dur, 1,
  )

Ndef(\harderkick).quant = 4;
Ndef(\harderkick)[0] = Pbind(
  \instrument, \bkick,
  \amp, Pseq([1.1, 1, 1], inf),
  \decay, 2,
  \rampattack, 0.01,
  \rampdecay, 0.2,
  \ramp, 4,
  \octave, 3,
  \root, -5,
  \gain, 6,
  \dur, 4,
  )
