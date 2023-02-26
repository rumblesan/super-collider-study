(
  ~notson = Pbind(
    \instrument, \fm3filter,
    \freq, 100,
    \ratio1, 0.5,
    \mod, 0.2,
    \mod1, 1,
    \attack, 0.01,
    \release, 0.01,
    \amp, -22.dbamp,
    \legato, 1,
    \q, 0.5,
    \gain, 1.1,
    \dur, Pseq([4, Rest(4)], inf) * 2
  )
)
~notson.quant = 4;
~notson.stop

~notson.map(\cutoff, 500);

(
  ~cutoffmod = Pcontrol(
    \mod,
    \value, Pseq([500, 100, 500, 1000], inf),
    \slew, 0.1,
    \dur, 0.5
  )
)
~cutoffmod.quant = 4;

~notson.map(\bend, ~penv);

(
  ~penv = Pcontrol(
    \percenv,
    \value, 3,
    \dur, Pseq([3, 5, 1], inf),
  )
)
