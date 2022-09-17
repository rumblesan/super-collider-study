

"Setup.scd".load;

s.plotTree;

(
  ~kickz = Pbind(
    \instrument, \modkick,
    \amp, 0.8,
    \decay, 2,
    \rdecay, 0.8,
    \mdecay, 0.3,
    \pfreq, 30,
    \ramp, 3,
    \mod, 20,
    \freq, 50,
    \dur, 2,
  )
)

~kickz.play;
~kickz.stop;

(
  SynthDef(\distortion, {arg out=0, distortion=0.2, a_input;
    var d, v;
    d = (1/(distortion + 1));
    v = (1/d);
    Out.ar(out, a_input.wrap2(d) * v);
  }).add;
)

// Changing the SynthDef means having to re-evaluate the Pmono to
// refresh it on the server
(
  ~seqdist = Pmono(
    \distortion,
    \input, ~kickz,
    \distortion, Pseq([0.3, 0.1, 0, 10, 1, 2], inf),
    \dur, 0.5,
  )
)
~seqdist.play;

(
~mainout = {
  var distortion = 0.3;
  var d = (1/(distortion + 1));
  var v = (1/d);
  Pan2.ar(~kickz.wrap2(d) * v, 0)
};
)

~mainout.play;
~mainout.clear;
~mainout.free;
