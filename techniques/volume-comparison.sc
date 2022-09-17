
"Setup.scd".load;

~foo = {SinOsc.ar};
~foo.play;
~foo.clear;
~foo.plot;



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
    \decay, 1,
    \freq, 220,
    \dur, 1,
    \amp, 1,
  )
)
~ping.play;
~ping.plot;
