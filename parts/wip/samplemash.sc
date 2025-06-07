"Setup.scd".load;

Ndef(\mash)[0] = Pbind(
  \instrument, \splay,
  \bufnum, d[\pads][13],
  \amp, 1,
  \rate, 1.0,
  \release, 0.1,
  \attack, 0.1,
  \start, Pseq([Pn(0, 4), Pn(0.2, 4), Pn(0, 6), 0.5, 0.9], inf),
  \legato, 0.1,
  //\dur, Pseq([2, 2, 0.5, 1, 2, 2, 0.5, 0.5, 0.5, 1], inf) / 2,
  \dur, Pseq([8], inf),
)
Ndef(\mash).quant = 4;
Ndef(\mash).clear;
