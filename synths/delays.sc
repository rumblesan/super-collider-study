(
  SynthDef(\simpledelay, {arg out=0, in;
    var maxDelay = 4;
    var signal = (LocalIn.ar(1) * \feedback.kr(0.1)) + in;
    var delayed = DelayN.ar(signal, maxDelay, \delay.kr(0.4));
    var filtered = LPF.ar(delayed, \lopass.kr(6000));

    LocalOut.ar(filtered);
    Out.ar(out,
      Pan2.ar(
        XFade2.ar(in, delayed, (\drywet.kr(0.2) * 2) - 1)
      )
    );
  }, [0, \ar]).add;

  SynthDef(\fourtapdelay, {

    var delayFeedback, signal,
    maxDelay = 2.0,
    taps = 4,
    tapSignals, delayOutput,
    filtered, output;

    var in = \in.ar(0);
    var drywet = \drywet.kr(0.2);

    var delay = \delay.kr(0.25).min(maxDelay);
    delayFeedback = LocalIn.ar(1);

    signal = in + (delayFeedback * (\feedback.kr(0.1) / taps));

    tapSignals = taps.collect({ |j|
      var i = (j+1);
      var delayTime = delay * i * \timescale.kr(1);
      DelayC.ar(signal, maxDelay * i, delayTime);
    });
    delayOutput = Mix.new(tapSignals);
    filtered = LPF.ar(delayOutput, \lopass.kr(6000));
    filtered = HPF.ar(filtered, \hipass.kr(50));

    LocalOut.ar(filtered);

    output = (filtered * drywet) + (in * (1-drywet));

    Out.ar(\out.kr(0), output);
  }).add;
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
