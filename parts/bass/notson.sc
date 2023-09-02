
Ndef(\notson, Pbind(
  \instrument, \fm3filter,
  \root, -5,
  \octave, Pwrand([4, 5, 6], [15, 0, 0].normalizeSum, inf),
  \scale, Scale.major,
  \degree, Pseq([
    0, 2, Pn(0, 2), Prand([0, 2, 7]),
    2, 0, Pn(5, 2), Prand([0, 2, 7]),
  ], inf),
  \ratio1, 1.5,
  \mod, 0.2,
  \mod1, 1,
  \attack, 0.01,
  \release, 0.01,
  \amp, Pseq([
    Pseq([1.2, 1, 1.2, 1], 2),
    Pseq([1.2], 4),
  ], inf),
  \legato, Pseq([0.1, 0.1, 0.3, 0.1, 0.5, 0.1, 0.5], inf),
  \resonance, 0.3,
  \gain, 2.1,
  \cutoff, 300,

  //\dur, 4,
  \dur, Pseq([
    3, 1,1,Rest(1), 2,
    3, 1, Rest(1), 1, 2, 2, 3, 1
  ], inf) / 4,
)
)

Ndef(\notson).quant = 4

Ndef(\notson).clear

Ndef(\notson).map(\cutoff, Ndef(\notsonCutoff));

Ndef(\notsonCutoff, Pcontrol(
  \mod,
  \value, Pseq([Pn(500, 3), 2100, 300, Pn(1000, 2), 1000], inf),
  \slew, 0.1,
  \dur, 0.5
))
Ndef(\notsonCutoff).quant = 4;

Ndef(\notson).map(\bend, Ndef(\notsonThump));

Ndef(\notsonThump, Pcontrol(
  \percenv,
  \curve, -8,
  \decay, 0.1,
  \value, 8,
  \dur, Pseq([4, 1, 4, 1, 2], inf),
))
Ndef(\notsonThump).quant = 4




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
)
)

Ndef(\notson).clear
