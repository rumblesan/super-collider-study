
"Setup.scd".load;

s.plotTree;

(
SynthDef(\ping, {arg out=0, freq=220, amp=1, decay=0.5;
  var osc, env;
  env = Env.perc(0.01, decay, amp).kr(doneAction: 2);
  osc = SinOsc.ar(freq);
  Out.ar(out, osc * env);
}).add;
)


(
  ~ping = Pbind(
    \instrument, \ping,
    \decay, 0.2,
    \freq, 220,
    \dur, Pseq([4], inf),
    \amp, 1,
  )
)
~ping.play;
~ping.stop;
~ping.clear;



(
  SynthDef(\myverb, {arg out=0, a_input, feedback=0.5;
    var loopback, d1, d2, d3;
    loopback = LocalIn.ar(1) * feedback + a_input;

    d1 = CombC.ar(loopback, 0.2, 0.017, 0.5) + loopback;
    d2 = CombC.ar(loopback, 0.2, 0.07, 0.5) + loopback;
    d3 = DelayC.ar(d2, 2, 0.2);

    LocalOut.ar(d3);
    Out.ar(out, Pan2.ar(d3), 0);
  }).add;
)


(
  SynthDef(\myverb, {arg out=0, a_input;
    Out.ar(out, JPverb.ar(a_input));
  }).add;
)

(
  ~outverb = {

  }
)

// Changing the SynthDef means having to re-evaluate the Pmono to
// refresh it on the server
(
  ~outverb = Pmono(
    \myverb,
    \input, ~ping,
    \dur, 0.5,
  )
)
~outverb.fadeTime = 4;
~outverb.play;
~outverb.clear;
