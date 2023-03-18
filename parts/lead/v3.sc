
Ndef(\v3).quant = 4;
Ndef(\v3, Pbind(
  \instrument, \hd,
  \scale, Scale.major,
  \octave, 4,
  \root, -4,
  \degree, 7,

  \attack, 0.4,
  \decay, 0.0,
  \level, 1,
  \attack1, 0.001,
  \decay1, 0.2,
  \level1, 0,
  \ratio1, 2,
  \legato, 0.3,
  \mod, 1.3,
  \mod1, 0,
  \mod2, 0,
  \attack2, 0.2,
  \decay2, 0.2,
  \level2, 1,
  \ratio2, 3.5,
  \mod21, 0.1,
  \amp, 1,
  \fold, 0.3,
  \pan, Pseq([
    -0.2, 0.2,
    -0.2, Pwhite(0.2, 1, 1),
    1, Pwhite(-0.2, -1, 1),
    1], inf),
  \dur, Pseq([1, 1, 0.5, 0.5, 1, 3], inf)/2,
))
