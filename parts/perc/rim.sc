

NpatLoop(\rim, 16,
  \instrument, \rim,
  \attack, 0.01,
  \degree, 3,
  \decay, Pwhite(0.04, 0.04, inf),
  \legato, Pseq([0.5, Pn(0.2, 3), 0.5, Pn(0.07, 3), 0.7, 0.1], inf),
  \click, Pseq([1.1, 0.8, 0.9, 1.1, 0.8, 1.2], inf),
  //\click, 1.0,
  \dur, Pseq([
    Pn(0.5, 4), 1
  ], inf) / 2,
)
)
Ndef(\rim).clear;
