
"Setup.scd".load;

(
  ~kick = Pbind(
    \instrument, \bkick,
    \amp, 0.8,
    \decay, 2,
    \rdecay, 0.03,
    \mdecay, 3,
    \ramp, 3,
    \freq, 50,
    \dur, Pseq([2], inf),
  )
)
~kick.play;


// This pattern will be fixed to 4 beats
(
  ~rim = Pn(Psync(Pbind(
    \instrument, \rim,
    \amp, Pseq([0.8], inf),
    \freq, Pseq([400, Pn([200], 36)], inf),
    \decay, 0.7,
    \dur, Pseq([0.25, 0.25, 0.25, 1, 0.25, 0.25, 0.125, 0.125, 1], inf),
  ), 4, 4), inf)
)

(
  ~rim = Pbind(
    \instrument, \rim,
    \amp, Pseq([0.8], inf),
    \freq, Pseq([400, Pn([200], 36)], inf),
    \decay, 0.7,
    \dur, Pseq([0.25, 0.25, 0.25, 1, 0.25, 0.25, 0.125, 0.125, 1], inf),
  )
)

~rim.play;

~rim.pause;
~rim.resume;
