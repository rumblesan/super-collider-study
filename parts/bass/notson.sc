
Ndef(\notson, Pbind(
  \instrument, \fm3filter,
  \octave, Pwrand([4, 5, 6], [15, 0, 0].normalizeSum, inf),
  \degree, -3,
  \ratio1, 1.5,
  \mod, 0.2,
  \mod1, 1,
  \attack, 0.01,
  \release, Pwhite(0.1, 0.3, inf),
  \amp, Pseq([
    Pseq([1.2, 1, 1.2, 1], 2),
    Pseq([1.2], 4),
  ], inf),
  \legato, 0.3,
  \resonance, 0.3,
  \gain, 2.1,
  \cutoff, 300,

  \dur, 4,
  //\dur, Pseq([
    //3, 1,1,Rest(1), 2,
    //3, 1, Rest(1), 1, 2, 2, 3, 1
  //], inf) / 2,
))

Ndef(\notson).quant = 4

Ndef(\notson).free

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
))
