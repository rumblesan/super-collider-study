(
  SynthDef(\karpluscomb, {arg out=0, gate=1, freq=100,
    amp=0.8,
    decay=1.5, fdiff=1, colour=0, gain=2;
    var env, imp, exciters, input, comb;
    env = EnvGen.kr(Env.linen(0, decay, 0), doneAction: Done.freeSelf);

    imp = Env.perc(0.0, 0.01, 1).ar;
    exciters = [SinOsc.ar(freq * fdiff), WhiteNoise.ar];
    input = LinSelectX.ar(colour * exciters.size, exciters);

    comb = (CombC.ar(imp * input, freq.reciprocal, freq.reciprocal, decay) * 2).tanh;
    Out.ar(out, Pan2.ar(comb * amp, \pan.kr(0)));
  }).add;

  SynthDef(\karpluspluck, {arg out=0, gate=1, freq=100,
    amp=0.8, gain=1,
    decay=1.5, fdiff=1, colour=0, damping=0.5;
    var env, imp, exciters, input, pluck;
    env = EnvGen.kr(Env.linen(0, decay, 0), doneAction: 2);

    imp = Impulse.ar(0.1);
    exciters = [SinOsc.ar(freq * fdiff), WhiteNoise.ar];
    input = LinSelectX.ar(colour * exciters.size, exciters);

    pluck = (Pluck.ar(
      input,
      imp,
      freq.reciprocal,
      freq.reciprocal,
      decay,
      damping,
    ) * gain).tanh;

    Out.ar(out, Pan2.ar(pluck * amp, \pan.kr(0)));
  }).add;

  SynthDef(\karplusdelay, {arg out=0,
    freq=100, decay=1.5,
    fdiff=1, colour=0,
    amp=0.8,
    gain=2, feedback=0.9;
    var env, fb, imp, exciters, input, output, filtered, t;

    env = EnvGen.kr(Env.linen(0, decay, 0), doneAction: 2);

    fb = LocalIn.ar(1) * feedback;

    imp = Env.perc(0.0, 0.04, 1).ar();
    exciters = [SinOsc.ar(freq * fdiff), WhiteNoise.ar];
    input = LinSelectX.ar(colour * exciters.size, exciters) * imp;
    filtered = OnePole.ar(input + fb, 0.7);

    // ControlDur.ir is to account for the extra 1 block
    // of delay that gets added to LocalIn feedback paths
    t = (freq.reciprocal - ControlDur.ir);
    output = DelayN.ar(filtered, t, t);

    LocalOut.ar(output);

    Out.ar(out, Pan2.ar(output * amp, \pan.kr(0)));
  }).add;
)
