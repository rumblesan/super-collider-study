
Ndef(\rad, Pbind(
  \instrument, \splay,
  \bufnum, Pwrand([
    d[\texture][\intro1],
    d[\texture][\outro2]
  ], [3, 0].normalizeSum, inf),
  \rate, 2.0,
  //\release, Pwrand([0.1, 0.7], [10, 1].normalizeSum, inf),
  \release, 0.1,
  \attack, 0.0,
  \start, Pwhite(0.4, 0.5, inf),
  \start, 0.4,
  \legato, 0.05,
  \amp, Pseq([1, 0.8, 0.8], inf),
  \dur, Pseq([Pn(1, 4), Rest(1), Pn(1, 3),Rest(1), Pn(1, 6), Rest(1)], inf)/4,
))
Ndef(\rad).quant = 4;
Ndef(\rad).play;
Ndef(\rad).stop;

Ndef(\sdelay)[0] = \simpledelay

(
  Ndef(\sdelay)[1] = \pset -> Pbind(
    \delay, Pseq([1, 2], inf),
    \feedback, 0.2,
    \dur, 5,
  )
)
Ndef(\sdelay) <<> Ndef(\rad)
Ndef(\sdelay)[1].play;
Ndef(\sdelay)[1].stop;
