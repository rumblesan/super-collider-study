
"Setup.scd".load;
s.plotTree

(
  SynthDef(\karpluscomb, {arg out=0, gate=1, freq=100, decay=1.5, fdiff=1, colour=0, gain=2;
    var env, imp, exciters, input, comb;
    env = EnvGen.kr(Env.linen(0, decay, 0), doneAction: 2);

    imp = Env.perc(0.0, 0.01, 1).kr(gate: gate);
    exciters = [SinOsc.ar(freq * fdiff), WhiteNoise.ar];
    input = LinSelectX.ar(colour * exciters.size, exciters);

    comb = (CombC.ar(imp * input, freq.reciprocal, freq.reciprocal, decay) * 2).tanh;
    Out.ar(out, comb);
  }).add
)

(
  SynthDef(\karpluspluck, {arg out=0, gate=1, freq=100, decay=1.5, fdiff=1, colour=0;
    var env, imp, exciters, input, pluck;
    env = EnvGen.kr(Env.linen(0, decay, 0), doneAction: 2);

    imp = Impulse.kr(0.1);
    exciters = [SinOsc.ar(freq * fdiff), WhiteNoise.ar];
    input = LinSelectX.ar(colour * exciters.size, exciters);

    pluck = (Pluck.ar(
      input,
      imp,
      freq.reciprocal,
      freq.reciprocal,
      decay,
      0.5
    ) * 2).tanh;

    Out.ar(out, pluck);
  }).add
)


(
  ~comb = Pbind(
    \instrument, \karpluscomb,
    \dur, 3,
    \freq, 50,
    \fdiff, Pseq([3, 7, 1, 4], inf),
    \gain, 5,
    \decay, 2,
    \colour, Pseq([0, 0.3, 0.7, 0, 1, 0.7, 0.1], inf),
    \amp, 1,
  )
)

~comb.play;
~comb.clear;

(
  ~pluck = Pbind(
    \instrument, \karpluspluck,
    \dur, 3,
    \freq, 50,
    \fdiff, 1,
    \gain, 15,
    \decay, 5,
    \colour, 0.7,
    \amp, 1,
  )
)

~pluck.play;

(
  SynthDef(\karplusdelay, {arg out=0, freq=100, decay=1.5, fdiff=1, colour=0, gain=2, feedback=0.9;
    var env, fb, imp, exciters, input, output, filtered, t;

    env = EnvGen.kr(Env.linen(0, decay, 0), doneAction: 2);

    fb = LocalIn.ar(1) * feedback;

    imp = Env.perc(0.0, 0.04, 1).kr();
    exciters = [SinOsc.ar(freq * fdiff), WhiteNoise.ar];
    input = LinSelectX.ar(colour * exciters.size, exciters) * imp;
    filtered = OnePole.ar(input + fb, 0.7);

    // ControlDur.ir is to account for the extra 1 block
    // of delay that gets added to LocalIn feedback paths
    t = (freq.reciprocal - ControlDur.ir);
    output = DelayN.ar(filtered, t, t);

    LocalOut.ar(output);

    Out.ar(out, output);
  }).add
)

(
  ~delaykp = Pbind(
    \instrument, \karplusdelay,
    \dur, 4,
    \freq, 50,
    \feedback, 0.999,
    \fdiff, 1,
    \gain, 1,
    \decay, 1.5,
    \colour, 0.1,
    \amp, 1,
  )
)

~delaykp.play;
~delaykp.clear;
