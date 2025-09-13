// NEEDS WORK

Npat(\samphats,
  \loop, 16,
  \instrument, \splay,
  \bufnum, d[\texture][\vinyl],
  \rate, 8.0,
  \release, Pwhite(0.01, 0.09, inf),
  \attack, 0.0,
  \start, Pwhite(0.4, 0.5, inf),
  \legato, 0.2,
  \dur, Pseq([2], inf),
  \amp, 1,
  \dur, Pseq([
    Pn(1, 4),
    Rest(2),
    Pn(1, 3),
    Rest(1),
    Pn(1, 5),
    Rest(1)
  ], inf)/4,
)

Ndef(\samphats).quant = 4;
