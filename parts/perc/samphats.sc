
Ndef(\samphats, Pbind(
  \instrument, \splay,
  \bufnum, d[\texture][\vinyl],
  \rate, 1.0,
  \release, 0.1,
  \attack, 0.0,
  \start, Pwhite(0.4, 0.5, inf),
  \legato, 0.2,
  \dur, Pseq([2], inf),
  \amp, -12.dbamp,
  \dur, Pseq([
    Pn(1, 4),
    Rest(2),
    Pn(1, 3),
    Rest(1),
    Pn(1, 5),
    Rest(1)
  ], inf)/4,
))

Ndef(\samphats).quant = 4;
Ndef(\samphats).free
