
(
  SynthDef(\sawpad, { arg out=0, freq=50, gate=1, amp=1,
    detune=0.01, pulsewidth=0.5,
    attack=0.1, decay=0.1, sustainLevel=0.5, release=0.5,
    foffset=0, res=0.85,
    fattack=0.1, fdecay=0.1, fsustainLevel=0.5, frelease=0.5,
    fenvDepth=0;
    var oscFreqMods = [
      1,
      1 - detune,
      1 + detune,
      2,
      2 + detune,
    ] * freq;
    var env = Env.adsr(attack, decay, sustainLevel, release, peakLevel: amp).kr(2, gate: gate);
    var fenv = Env.adsr(
      fattack, fdecay, fsustainLevel, frelease
    ).kr(gate: gate);
    var oscs = Mix.new(oscFreqMods.collect({|f|
      VarSaw.ar(f, width: pulsewidth, mul: oscFreqMods.size.reciprocal);
    }));
    var filtered = RLPF.ar(oscs, freq * (1 + foffset) * (1 + (fenv * fenvDepth)), res.reciprocal);
    var snd = filtered * env;
    Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
  }).add;
)
