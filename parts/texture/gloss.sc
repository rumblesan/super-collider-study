// NEEDS WORK

Ndef(\gloss, Pbind(
  \instrument, \splay,
  \bufnum, d[\vox][0],
  \amp, 1,
  \rate, Pwrand([1, 1.2], [10, 3].normalizeSum, inf),
  \release, 0.01,
  \attack, 0.01,
  \start, Pseq([Pn(0, 4), 0.3, 0.1, 0.5, 0.1, Pn(0.2, 4)], inf),
  \legato, Pseq([Pn(0.1, 5), 0.3, Pn(0.25, 3), Pn(0.3, 5), 0.7], inf) * 1.5,
  \dur, Pseq([2, 1, 0.5, 0.5, 2, 2, 1, 1, 1, Rest(2), 1, 1, 1], inf) / 2,
))
Ndef(\gloss).quant = 4;

p.clock.tempo = 160/60;
