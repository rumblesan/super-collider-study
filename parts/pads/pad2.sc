Npat(\pad2,
  \instrument, \fm3filter,
  \octave, 6,
  \degree, Pseq([
    [0, 3, 5],
    [0, 4, 7] + 5,
    [0, 2, 6] + 1,
    [0, 3, 5] + 2,
    [0, 4, 7] + 3,
    [0, 3, 5] + 2,
  ], inf),
  \bend, [0.01, 0, 0.02],
  \mod, 0.2,
  \attack, 3.0,
  \release, 5.0,
  \legato, 0.8,
  \resonance, 0.3,
  \cutoff, 300,
  \dur, Pseq([
    4,
  ], inf) * 4,
)

Ndef(\bass).quant = 8

Ndef(\pad2).map(\amp, Ndef(\trem))

NpatControl(\trem,
  \mod,
  \value, Pseq([1, 0.4, 1.6], inf),
  \slew, 1,
  \dur, 1/4
)
