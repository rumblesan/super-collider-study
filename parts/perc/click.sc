

Ndef(\click, Pbind(
  \instrument, \rim,
  \degree, 0,
  \attack, 0.01,
  \root, -5,
  \degree, 3,
  \decay, Pwhite(0.04, 0.07, inf),
  \amp, 1,
  \click, Pseq([1.1, 0.8, 0.9, 1.1, 0.8, 1.2], inf),
  \dur, Pseq([
    Pn(0.5, 4), 1
  ], inf) / 2,
)
)
Ndef(\click).clear;
