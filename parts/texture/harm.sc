Ndef(\harm,
  Pbind(
    \instrument, \fm2,
    \degree, Prand([0, 2, 3, 5,
      Prand([
        [0, 3],
        [3, 5],
      ], 1)
    ], inf),
    \root, -5,
    \octave, 5,
    \mod, 2,
    \mod1, 1,
    \ratio, 2.5,


    \attack, 0.1,
    \decay, 0.05,
    \legato, 0.1,

    \amp, 1,
    \dur, Pseq([8.5, 3, 3.25, 2], inf),
  )
)
Ndef(\harm).clear;
