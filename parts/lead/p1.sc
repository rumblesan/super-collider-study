
Ndef(\p1, Pbind(
  \instrument, \fm3,

  \scale, Scale.minor,
  \octave, 5,
  \root, -5,
  \degree, Pseq([0, 0, 0, 0, 0, 0, 7, 0], inf),

  \amp, Pseq([1, 1, 0.8, 1, 1, 0.8, 0.8, 0.8], inf),

  \attack, 0.001,
  \decay, 0.1,
  \legato, 0.2,
  \mod, Pseq([1.3, 1, 1], inf),

  \mod1, 1,
  \ratio1, 2.5,
  \attack1, 0.01,
  \decay1, 0.1,

  \mod2, 1.75,
  \ratio2, Pwrand([2.52, 3.57], [10, 1].normalizeSum, inf),
  \attack2, 0.01,
  \decay2, 0.1,
  \dur, Pn(
    Pfinval(16,
      Pseq([
        2, 1, 1,
        1, 2,
        Prand([1, 2, 4]),
        1, 1,
        Prand([2, 4])], inf) / 4,
    ), inf
  )
))
Ndef(\p1).clear
