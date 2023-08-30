
Ndef(\dc5, Pbind(
  \instrument, \fm3,
  \root, -5,
  \octave, 4,
  \scale, Scale.minor,
  \degree, Prand([0, 2, 4, 7], inf),
  \attack, 0.8,
  \legato, 0.7,
  \decay, 1.0,
  \mod, 0.5,
  \attack2, 0.51,
  \ratio2, 3,
  \decay2, 0.1,
  \level2, 1,
  \level1, 1,
  \mod2, 3,
  \mod1, 1,
  \ratio1, 1.5,

  \amp, 1,
  \dur, Pseq([16], inf),
)
)
Ndef(\dc5).clear;
