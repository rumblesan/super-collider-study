Npat(\pad1,
  \instrument, \fm3filter,
  \octave, 6,
  \degree, Pseq([
    [0, 3, 5],
    [0, 3, 5, 7] + 3,
    [0, 3, 5] + 5,
    [0, 3, 5] + 2,
  ], inf),
  \mod, 1.5,
  \attack, 3.0,
  \release, 5.0,
  \gain, 0.4,
  \mod1, 4,
  \ratio1, 3,
  \output1Mix, 0.0,
  \mod2, 0.1,
  \ratio2, 3.5,
  \output2Mix, 0.0,

  \legato, 0.8,
  \resonance, 0.3,
  \cutoff, 1200,
  \dur, Pseq([
    4, 4, 8, 4
  ], inf) * 4,
)

Ndef(\bass).quant = 8
