// WIP
(
SynthDef(\face, {arg out=0, freq=50, gate=0.5, amp=0.5,
  detune=0,
  attack=0.01, decay=0.5, sustainLevel=0.9, release=0.5,
  mattack=0.01, mdecay=0.5, msustainLevel=0.9, mrelease=0.5,
  widthMod=0.01, widthModRate=1,
  lfoRate=1, lfoMod=0.1,
  pitchEnvMod=0, filterEnvMod=0,
  cutoff=1000, q=0.2, gain=1.0,
  fold = 1

  ;

  var lfo, widthLfo, modEnv, volEnv, snd;

  lfo = SinOsc.kr(lfoRate, mul: lfoMod.min(0.999));
  widthLfo = SinOsc.kr(widthModRate, mul: widthMod, add: 0.5);

  modEnv = Env.adsr(
    mattack, mdecay, msustainLevel, mrelease
  ).kr(gate: gate);

  volEnv = Env.adsr(
    attack, decay, sustainLevel, release
  ).kr(gate: gate, doneAction: Done.freeSelf);

  snd = Mix.new([
    SinOsc.ar(freq),
    Pulse.ar(freq * (1 + detune), widthLfo),
    Pulse.ar(freq * (1 - detune), widthLfo),
    Pulse.ar(freq * 2, widthLfo),
  ]) * 0.25;
  snd = Fold.ar(snd * (fold + 1), -1, 1);
  snd = BMoog.ar(snd * gain, cutoff * (1 + lfo), q) * volEnv * amp;
  snd = snd * volEnv;
  Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
}).add;
)
