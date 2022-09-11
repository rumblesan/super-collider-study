
(
  "Setup.scd".load;
  m = ProxyMixer(p, 8);
)

(
  ~rimz = Pbind(
    \instrument, \rim,
    \amp, Pseq([0.8], inf),
    \freq, 200,
    \decay, 1.7,
    \dur, Pseq([1, 0.5, 0.5, 1, 2, 4], inf) / 4,
  )
)

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

(
  ~main = {
    arg headroom = 2;
    var makeup = (1/headroom) + 0.7;
    var ducking = (1 - EnvDetect.ar(~kickz, attack:0.1, release: 0.2));
    //var ducking = 1;
    var harsh = (((~rimz * 1.4) * ducking) + ~kickz).wrap2(headroom) * makeup;
    (harsh * 0.8) + (~kickz * 0.9);
  };
  ~main[1] = \set -> Pbind(
    \headroom, Pseq([0.7], inf),
    \dur, Pseq([1, 4, 2, 4], inf) / 4,
  );
)


~main.play
