
NpatLoop(\hardkick, 24,
  \instrument, \modkick,
  \amp, Pseq([1.1, 1, 1], inf),
  \decay, 0.5,
  \rampdecay, 0.1,
  \moddecay, 1.8,
  \pfreq, 300,
  \ramp, 10,
  \moddepth, 1.2,
  \freq, 50,
  \gain, 5,
  \dur, Pseq([Pn(2, 2), Pn(0.5, 2), Pn(3, 2)], inf) / 2,
)
Ndef(\hardkick).quant = 4;

Ndef(\harderkick).quant = 4;
NpatLoop(\harderkick, 24,
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
