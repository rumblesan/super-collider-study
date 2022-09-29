(
  SynthDef(\simpledelay, {arg out=0, in,
    delay=0.4, feedback=0.1, lopass=6000,
    drywet=0.2;

    var maxDelay = 4;
    var signal = (LocalIn.ar(1) * feedback) + in;
    var filtered = LPF.ar(signal, lopass);
    var delayed = DelayN.ar(filtered, maxDelay, delay);

    LocalOut.ar(delayed);
    Out.ar(out, Pan2.ar(XFade2.ar(in, delayed, drywet), 0));
  }, [0, \ar]).add;

  SynthDef(\fourtapdelay, {arg out=0, in,
    delay=0.25, scale=1, feedback=0.9,
    drywet=0.2;

    var delayFeedback, signal,
    maxDelay = 2.0,
    taps = 4,
    tapSignals, delayOutput, output;

    delayFeedback = LocalIn.ar(1);

    signal = in + (delayFeedback * feedback);

    tapSignals = taps.collect({ |i|
      signal = DelayC.ar(signal, maxDelay * (i+1), delay * (i+1));
    });
    delayOutput = Mix.new(tapSignals);

    LocalOut.ar(delayOutput);

    output = (delayOutput * drywet) + (in * (1-drywet));

    Out.ar(out, output);
  }, [0, \ar]).add;
)

/*
(
SynthDef(\ping, {arg out=0, freq=220, amp=1, decay=0.5;
  var osc, env;
  env = Env.perc(0.01, decay, amp).kr(doneAction: 2);
  osc = Saw.ar(freq);
  Out.ar(out, osc * env);
}).add;
)


(
  ~ping = Pbind(
    \instrument, \ping,
    \decay, 0.2,
    \freq, 200,
    \dur, Pseq([8], inf),
    \amp, 1,
  )
)
~ping.play
~ping.stop

~delay = \fourtapdelay
~delay.set(\drywet, 0.5)
~delay.set(\feedback, 0.1)
~delay.set(\delay, 0.5)

~ping <>> ~delay <>> ~out

~out = { \in.ar(0!2) }; ~out.play;
*/
