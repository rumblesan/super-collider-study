
"Setup.scd".load;

s.plotTree;
s.queryAllNodes;

(
SynthDef(\ping, {arg out=0, freq=220, amp=1, decay=0.5;
  var osc, env;
  env = Env.perc(0.01, decay, amp * 3).kr(doneAction: 2);
  osc = SinOsc.ar(freq);
  Out.ar(out, osc * env);
}).add;
)


(
  ~ping = Pbind(
    \instrument, \ping,
    \decay, 0.8,
    \freq, 220,
    \dur, Pseq([4], inf),
  )
)
~ping.play;
~ping.stop;
~ping.pause;
~ping.resume;
~ping.clear;

~test = {SinOsc.ar(400)}

(
  SynthDef(\feedbackdelay, {arg out=0, a_input, delaytime=0.4, feedback=0.1;
    var delayed, prev;
    prev = LocalIn.ar(1) * feedback + a_input;
    delayed = DelayN.ar(prev, 2, delaytime);
    LocalOut.ar(delayed);
    Out.ar(out, Pan2.ar(delayed + a_input, 0), 0);
  }).add;
)

// Changing the SynthDef means having to re-evaluate the Pmono to
// refresh it on the server
(
  ~output = Pmono(
    \feedbackdelay,
    \input, ~ping,
    \delaytime, Pseq([0.2, 0.1, 0.3, 1.7, 0.01, 0.9, 0.9], inf),
    \feedback, Pseq([0.75], inf),
    \dur, Prand([0.5, 0.5, 0.25, 0.75, 1, 0.25], inf),
  )
)
~output.play;
