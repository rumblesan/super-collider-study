
(
  SynthDef(\clikr, {arg out, attack=0.0, decay=1, freq=200, amp=1;
    var env = Env.perc(0, decay, amp).kr(doneAction: 2);
    Out.ar(out, WhiteNoise.ar * env);
  }).add;
)

(
  ~click = Pbind(
    \instrument, \clikr,
    \dur, 1,
    \amp, 0.1,
    \decay, 0.01,
  )
)
~click.play;


(
  SynthDef(\saw, { arg out=0, freq=50, gate=1, amp=1,
    pulsewidth=0.5,
    filterOffset=0, res=0.85,
    attack=0.1, decay=0.1, sustainLevel=0.5, release=0.5;
    var env = Env.adsr(attack, decay, sustainLevel, release, peakLevel: amp).kr(2, gate: gate);
    var osc = VarSaw.ar(freq, width: pulsewidth);
    var filtered = RLPF.ar(osc, freq * (1 + filterOffset), res);
    Out.ar(out, filtered * env);
  }).add;
)


(
  ~synth = Pbind(
    \instrument, \saw,
    \scale, Scale.minor,
    \octave, 4,
    \degree, Pseq([0, 3, 5, 7], inf),
    \attack, 1,
    \decay, 0.5,
    \sustainLevel, 1,
    \release, 3,
    \amp, 0.5,
    \legato, 1/2,
    \dur, 8,
  )
)

~synth.quant = 8

~synth.play

(
  ~synth = Pbind(
    \instrument, \saw,
    \scale, Scale.minor,
    \octave, 4,
    \degree, Pseq([
      [0, 3, 5],
      [3, 5, 7],
      [3, 5, 9],
      [0, 7, 9],
    ], inf),
    \attack, 1.3,
    \release, 3,
    \amp, 0.1,
    \legato, 0.5,
    \dur, 8,
  )
)

~synth.play
