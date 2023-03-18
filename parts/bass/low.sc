
Ndef(\low, Pbind(
  \instrument, \fm3filter,
  \octave, 4,
  \root, -5,
  \degree, Prand([0, 3, -2], inf),
  \mod, 0.5,
  \attack, 0.01,
  \release, 0.4,
  \gain, 0.4,
  \amp, 2,

  \mod1, 1,
  \ratio1, 1.5,
  \output1Mix, 0.0,
  \mod2, 0.1,
  \ratio2, 0.5,
  \output2Mix, 0.0,

  \legato, 0.8,
  \resonance, 0.3,
  \cutoff, 600,

  \dur, 8,
))

Ndef(\low).quant = 4

Ndef(\low).free

Ndef(\low).map(\mod1, Ndef(\lowmod1));

Ndef(\lowmod1, Pcontrol(
  \mod,
  \value, Pwhite(0.0, 0.5, inf) * 8,
  \slew, 0.15,
  \dur, 0.5
))
Ndef(\lowmod1).quant = 4;

Ndef(\low).map(\bend, Ndef(\lowThump));

Ndef(\lowThump, Pcontrol(
  \percenv,
  \curve, -8,
  \value, 4,
  \dur, Pseq([4, 1, 4, 1, 2], inf),
))
Ndef(\lowThump).quant = 4




Ndef(\notbass, Pbind(
  \instrument, \fm3filter,
  \octave, 4,
  \degree, -3,
  \ratio1, 1,
  \mod, 0.2,
  \mod1, 1,
  \attack, 0.01,
  \release, 4,
  \amp, Pseq([1], inf),
  \legato, 0.8,
  \resonance, 0.6,
  \gain, 2.1,
  \cutoff, 300,
  \dur, Pseq([12 ], inf),
))
