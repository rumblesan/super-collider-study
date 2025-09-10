
NpatLoop(\ping, 16,
  \instrument, \buzz1,
  \octave, 5,
  \degree, 0,
  \attack, 0.01,
  \sustain, 0.3,
  \fold, 1,
  \clip, 1,
  \decay, 0.1,
  \timingOffset, Pseq([0, 1/2, 0, 1/2, 0], inf),
  \timingOffset, 0,

  \dur, Pseq([1], inf),
)
Ndef(\).quant = 8;


NpatLoop(\ping, 16,
  \instrument, \ping,
  \decay, Pseq([Pn(0.3, 2), Pn(0.5, 2), Pn(0.3, 3), 0.7], inf),
  \octave, 6,
  \degree, 0,
  \ptrig, 1,
  \pmod, 0.01,
  \pdecay, 0.01,
  \distamp, 1,
  \dur, Pseq([
    3, 4, 4, 3, 2,
    2, 2, 3, 3, 4, 2
  ], inf) / 4,
)
