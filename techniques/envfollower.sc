
"Setup.scd".load;

(
  ~kickz = Pbind(
    \instrument, \bkick,
    \amp, 1,
    \decay, 2,
    \rdecay, 0.01,
    \mdecay, 0.3,
    \pfreq, 160,
    \ramp, 0.3,
    \mod, 50,
    \freq, 50,
    \dur, Pseq([2, 0.5, 0.5, 1, 1, 4, 0.5, 2], inf),
  )
)
~kickz.play;
~kickz.stop;

(
  ~ef = { |gain=1, attack=0.01, release=0.7|
    var env = EnvDetect.ar(~kickz * 1, attack, release).min(1);
    env;
  };
)

~ef.set(\release, 0.1);
~ef.scope


(
  ~rimz = Pbind(
    \instrument, \rim,
    \amp, Pseq([0.8], inf),
    \freq, 200,
    \decay, 0.7,
    \dur, Pseq([1, 0.5, 0.5, 1, 2, 4], inf) / 2,
  )
)
~rimz.play;
~rimz.stop;

~ducker = {(1.1 - ~ef) * 16}
(
  ~dist = {|gain=0.3|
    (~rimz * gain).tanh
  }
)
~dist.set(\gain, ~ducker)
