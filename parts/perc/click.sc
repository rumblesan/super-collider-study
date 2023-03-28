

Ndef(\click, Pbind(
  \instrument, \rim,
  \degree, 0,
  \attack, 0.01,
  \root, -5,
  \degree, 4,
  \decay, 0.05,
  \amp, 1,
  \click, Pseq([1.1], inf),
  \dur, Pseq([
    Pn(0.5, 4), 1
  ], inf) / 2,
))
Ndef(\click).clear;
