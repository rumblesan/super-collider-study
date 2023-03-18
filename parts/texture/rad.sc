
(
  ~rad = Pbind(
    \instrument, \splay,
    \bufnum, Pwrand([d[\texture][\intro1], d[\texture][\outro2]], [3, 5].normalizeSum, inf),
    \rate, 2.0,
    \release, Pwrand([0.1, 0.7], [10, 1].normalizeSum, inf),
    \attack, 0.0,
    \start, Pwhite(0.4, 0.5, inf) + 0.1,
    \legato, 0.5,
    \amp, -12.dbamp,
    \dur, Pseq([Pn(1, 4), Rest(1), Pn(1, 3),Rest(1), Pn(1, 6), Rest(1)], inf)/4,
  )
)
~rad.quant = 4;
~rad.play;
~rad.stop;

~sdelay = \simpledelay

(
  ~sdelay[1] = \pset -> Pbind(
    \delay, Pseq([1, 2], inf),
    \feedback, 0.2,
    \dur, 5,
  )
)
~sdelay <<> ~rad
~sdelay[1].play;
~sdelay[1].stop;
