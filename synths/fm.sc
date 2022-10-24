(
SynthDef(\fm2, {
  arg out=0, gate=1, freq=220, amp=0.8,
  attack=0.1, decay=0.1, level=1, release=0.5,
  pitchEnvAttack=0.01, pitchEnvDecay=0.1, pitchEnvDepth=0,
  bend=0,
  ratio=1.0,
  modEnvAttack=0.1, modEnvDecay=0.01, modDepth=0.1, modEnvRelease=0.5;
  var venv = Env.adsr(attack, decay, level, release).kr(2, gate);
  var penv = Env.perc(pitchEnvAttack, pitchEnvDecay, pitchEnvDepth).kr(gate: gate, levelBias: 1);
  var menv = Env.adsr(modEnvAttack, modEnvDecay, modDepth, modEnvRelease).kr(2, gate);

  var modOsc = SinOsc.ar(freq * ratio, mul: menv, add: 1);

  Out.ar(out, SinOsc.ar(freq * modOsc * penv * (1+bend), mul: venv * amp));
}).add;
)
