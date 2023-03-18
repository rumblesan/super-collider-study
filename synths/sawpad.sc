
(
  SynthDef(\sawpad, { arg out=0, freq=50, gate=1, amp=1,
    detune=0.01, pulsewidth=0.5,
    attack=0.1, decay=0.1, sustainLevel=0.5, release=0.5,
    foffset=0, res=0.85,
    fattack=0.1, fdecay=0.1, fsustainLevel=0.5, frelease=0.5,
    fenvDepth=0;
    var oscFreqMods, env, fenv, oscs, filtered;
    oscFreqMods = [
      1,
      1 - detune,
      1 + detune,
      2,
      2 + detune,
    ] * freq;
    env = Env.adsr(attack, decay, sustainLevel, release, peakLevel: amp).kr(2, gate: gate);
    fenv = Env.adsr(
      fattack, fdecay, fsustainLevel, frelease
    ).kr(gate: gate);
    oscs = Mix.new(oscFreqMods.collect({|f|
      VarSaw.ar(f, width: pulsewidth, mul: oscFreqMods.size.reciprocal);
    }));
    filtered = RLPF.ar(oscs, freq * (1 + foffset) * (1 + (fenv * fenvDepth)), res.reciprocal);
    Out.ar(out, filtered * env);
  }).add;
)

/*
~lfo = {SinOsc.kr(0.07, mul: 0.035)}

(
  ~pads = Pbind(
    \instrument, \sawpad,
    \octave, 3,
    \detune, 0.003,
    \scale, Scale.minor,
    \pulsewidth, 0.3,
    \foffset, 0.0,
    \fenvDepth, 3,
    \fattack, 3,
    \fdecay, 3,
    \fsustain, 1,
    \octave, Prand([3, 4], inf),
    \degree, Pseq([
      [0, 3, 5],
      [3, 5, 7],
      [3, 5, 9],
      [0, 7, 9],
    ], inf),
    \attack, 1.3,
    \release, 3,
    \amp, 1.0,
    \legato, 1/2,
    \dur, 8,
  )
)
~pads.quant = 32;

~pads.play
*/
