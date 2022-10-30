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
    delay=0.25, timeScale=1, feedback=0.9,
    lopass=6000,
    drywet=0.2;

    var delayFeedback, signal,
    maxDelay = 2.0,
    taps = 4,
    tapSignals, delayOutput,
    filtered, output;

    delayFeedback = LocalIn.ar(1);

    signal = in + (delayFeedback * feedback);

    tapSignals = taps.collect({ |j|
      var i = (j+1);
      var maximum = maxDelay * i;
      var delayTime = delay * i * timeScale;
      signal = DelayN.ar(signal, maximum, delayTime);
    });
    delayOutput = Mix.new(tapSignals);
    filtered = LPF.ar(delayOutput, lopass);

    LocalOut.ar(filtered);

    output = (filtered * drywet) + (in * (1-drywet));

    Out.ar(out, output);
  }, [0, \ar]).add;
)


/*
(
  ~ping = Pbind(
    \instrument, \ping,
    \decay, 0.5,
    \freq, Pwrand([200, 300], [15, 1].normalizeSum, inf),
    \amp, 1,
    \dur, Pseq([8, Rest(3), 5], inf),
  )
)
~ping.play
~ping.stop

~delay[0] = \fourtapdelay
~delay.set(\drywet, 0.5)
~delay.set(\feedback, 0.01)
~delay.set(\delay, 1.0)
~delay.set(\timeScale, 1.5)
~delay.set(\lopass, 3500)

(
  ~delay[1] = \pset -> Pbind(
    \delay, 0.5,
    \timeScale, Pseq([Pn(1.0, 2), 1.5, Pn(0.5, 4), 0.75, Pwrand([0.75, 0.01], [10, 1].normalizeSum)], inf),
    \feedback, 0.25,
    \dur, 0.5
  )
)

~ping <>> ~delay <>> ~out

~out = { \in.ar(0!2) }; ~out.play;
*/
