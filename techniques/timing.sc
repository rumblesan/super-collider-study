
"Setup.scd".load;


(
  ~kick = Pbind(
    \instrument, \bkick,
    \amp, 0.8,
    \decay, 1,
    \rdecay, 0,
    \mdecay, 1,
    \ramp, 1,
    \freq, 50,
    \dur, Pseq([2], inf),
  )
)
~kick.play;

(
  ~rim = Pbind(
    \instrument, \rim,
    \amp, Pseq([0.8], inf),
    \freq, 200,
    \decay, 0.7,
    \dur, Pseq([1, 2, 1], inf),
  )
)
~rim.play;
~rim.pause;
~rim.resume;


(
  ~click = Pbind(
    \instrument, \clikr,
    \amp, 3,
    \decay, 0.2,
    \ffreq, 1000,
    \dur, Pseq([3, Rest(1)], inf),
  )
)
~click.play;
~click.reset;
