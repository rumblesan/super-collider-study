

Ndef(\v1, Pbind(
  \instrument, \fm3,
  \scale, Scale.minor,
  \octave, 4,
  \root, -4,

  [\degree, \dur], Pmetro(
    Pseq([0, 1,
      Pwrand([0, 2, 7], [10, 2, 1].normalizeSum),
      0, 7, 0, 2, 0, 7], inf),
    Pseq([2, 2, 4, 2, 1, 1, 2, 2], inf),
    "--*-*---",
    inf,
    0.25),

  \attack, 0.01,
  \decay, Pseq([
    0.5, 0.1, 0.1,
    Pn(Pwhite(0.01, 0.3), 3),
    0.5, 0.5,
    1, Pn(0.1, 4),
    0.01, 0.02,
    0.5, 0.1
  ], inf),

  \level, Pwrand([0, 0.3, 1], [10, 2, 3].normalizeSum, inf),
  \legato, 0.1,
  \amp, 1,

  \mod, Pseq([1.5, 1, 1, 1.5, 1], inf),

  \ratio1, Pseq([
    Pseq([1.5, 1, 1]),
    Pseq([1.5, 1, 1]),
    Prand([
      Pseq([2.5, 3]),
      Pseq([7, 7.5]),
    ])
  ], inf) + 0.01,
  \mod1, 5.5,
  \attack1, 0.01,
  \decay1, 0.1,
  \level1, 0,
  \output1Mix, 0,

  \ratio2, 2.7391,
  \mod2, 7,
  \attack2, 0.0,
  \decay2, 0.01,
  \level2, 0,
))
Ndef(\v1).play;
Ndef(\v1).stop;
