
"Setup.scd".load;
s.plotTree

(
  ~comb = Pbind(
    \instrument, \karpluscomb,
    \dur, 3,
    \freq, 50,
    \fdiff, Pseq([3, 7, 1, 4], inf),
    \gain, 5,
    \decay, 2,
    \colour, Pseq([0, 0.3, 0.7, 0, 1, 0.7, 0.1], inf),
    \amp, 1,
  )
)

~comb.play;
~comb.clear;

(
  ~pluck = Pbind(
    \instrument, \karpluspluck,
    \dur, 3,
    \freq, 50,
    \fdiff, 1,
    \gain, 15,
    \decay, 5,
    \colour, 0.7,
    \amp, 1,
  )
)

~pluck.play;

(
  ~delaykp = Pbind(
    \instrument, \karplusdelay,
    \dur, 4,
    \freq, 50,
    \feedback, 0.999,
    \fdiff, 1,
    \gain, 1,
    \decay, 1.5,
    \colour, 0.1,
    \amp, 1,
  )
)

~delaykp.play;
~delaykp.clear;
