(
  SynthDef(\simpledelay, {arg out=0;
    var maxDelay = 4;
    var in = \in.ar(0!2);
    var signal = (LocalIn.ar(2) * \feedback.kr(0.1)) + in;
    var delayed = DelayN.ar(signal, maxDelay, \delay.kr(0.4));
    var filtered = LPF.ar(delayed, \lopass.kr(6000));

    LocalOut.ar(filtered);
    Out.ar(out,
      XFade2.ar(in, delayed, (\drywet.kr(0.2) * 2) - 1)
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
