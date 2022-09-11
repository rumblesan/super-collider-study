(
  "Setup.scd".load;
  m = ProxyMixer(p, 8);
)

(

  ~kickz = Pbind(
    \instrument, \bkick,
    \amp, Pseq([0.8], inf),
    \decay, 3,
    \rdecay, 0.08,
    \mdecay, 0.3,
    \pfreq, 160,
    \ramp, 5,
    \mod, 50,
    \freq, 50,
    \dur, Pseq([2, 0.5, 0.5, 1, 1, 4, 0.5, 2], inf),
  )
)

(
  ~rimz = Pbind(
    \instrument, \rim,
    \amp, Pseq([0.8], inf),
    \freq, 200,
    \decay, 0.7,
    \dur, Pseq([1, 0.5, 0.5, 1, 2, 4], inf) / 4,
  )
)

~kickz.class;

(
~mainout = {
  arg distortion=0;
  var d = (1/(distortion + 1));
  var v = (1/d);
  var out = (((~rimz + ~kickz) * 0.7).wrap2(d) * v);
  out;
};
)

(
  ~mainout[1] = \set -> Pbind(
    \distortion, Pseq([1, 0, 3, 0.7, 8, 0, 2], inf),
    \dur, Pseq([1, 4, 2, 4], inf) / 4,
  );
)

~mainout.nodeMap.postln;

~mainout.play;
