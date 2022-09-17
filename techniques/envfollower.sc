
"Setup.scd".load;
ProxyMixer(p, 8);
s.plotTree;

(
  ~rimz = Pbind(
    \instrument, \rim,
    \amp, Pseq([0.8], inf),
    \freq, 200,
    \decay, 0.7,
    \dur, Pseq([1, 0.5, 0.5, 1, 2, 4], inf) / 4,
  )
)
~rimz.play;
~rimz.stop;

(
  ~kickz = Pbind(
    \instrument, \bkick,
    \amp, Pseq([0.8], inf),
    \decay, 3,
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
  ~main = { |gain = 30|
    var duck = 1 - EnvDetect.ar(~kickz, attack:0.01, release: 0.7);

    (~rimz * duck * gain).tanh + ~kickz;
  };
)
~main.set(\gain, 80)
~main.play
