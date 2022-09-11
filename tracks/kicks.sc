"Setup.scd".load;

p.clock.tempo = 180/60;

(
  m = ProxyMixer(p, 8);
)


(

  ~kickz = Pbind(
    \instrument, \modkick,
    \amp, 0.8,
    \decay, 2,
    \rdecay, 0.08,
    \mdecay, 0.3,
    \pfreq, 50,
    \ramp, 3,
    \mod, 20,
    \freq, 50,
    \dur, 2,
  )
)

~kickz.play;
~kickz.stop;

(
~mainout = {
  var distortion = 0.3;
  var d = (1/(distortion + 1));
  var v = (1/d);
  Pan2.ar(~kickz.wrap2(d) * v, 0)
};
)

~mainout.play;
