
"Setup.scd".load;

~granmix = {~piston + SinOsc.ar(50, mul: 0.8)}

~gran[0] = \granulizer
~gran <<> ~granmix
~gran.play
~gran.stop

(
  ~gran[1] = \pset -> Pbind(
    \density, 650,
    \randDensity, 0,
    \size, Pwrand([0.01, 0.07, 0.1], [25, 3, 0].normalizeSum, inf),
    \position, 0.0,
    \rate, Pwrand([1.0, 2.0], [10, 3].normalizeSum, inf),
    \spread, 0.05,
    \feedback, 0.0,
    \drywet, 0.5,
    \dur, 0.5,
  )
)
