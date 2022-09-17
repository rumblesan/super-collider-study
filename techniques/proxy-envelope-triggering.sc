"Setup.scd".load;
s.plotTree;


~sound;

(
  ~sound[0] = {arg freq=100, t_trig=1;
    var env = Decay2.kr(t_trig, 0.1, 1.5);
    SinOsc.ar(freq) * env;
  }
)

~sound.play;

(
  ~sound[1] = \set -> Pbind(
    \freq, 100,
    \t_trig, 1,
    \dur, Pseq([3, 3, 2], inf) / (2),
  );
)

(
  ~sound[2] = \filter -> { |in, distortion=0, t_dtrig=1|
    var env = Decay2.kr(t_dtrig, 0.1, 3.5);
    var d = (1/((distortion * env) + 1));
    var v = (1/d);
    var out = ((in * 0.7).wrap2(d) * v);
    out;
  }
)

~sound.set(\distortion, 6.0)

~sound[3] = \set -> Pbind(
  \freq, 50,
  \distortion, 0.5,
  \t_dtrig, 1,
  \dur, 2.5,
)
