
/*
  "Setup.scd".load;
  s.plotTree
*/


(
SynthDef(\face, {arg out=0, freq=50, gate=0.5, amp=0.5,
  wavetableBufNum, wavetableWaves=64,
  waveMod=0, spread=0,
  attack=0.01, decay=0.5, sustainLevel=0.9, release=0.5,
  mattack=0.01, mdecay=0.5, msustainLevel=0.9, mrelease=0.5,
  lfoRate=1, lfoMod=0.1,
  pitchEnvMod=0, filterEnvMod=0,
  cutoff=1000, q=0.2, gain=1.0,

  fold=0;


  var lfo, modEnv, volEnv, oscCount, tablepos, oscs, snd;

  lfo = SinOsc.kr(lfoRate, mul: lfoMod);

  modEnv = Env.adsr(
    mattack, mdecay, msustainLevel, mrelease
  ).kr(gate: gate);

  volEnv = Env.adsr(
    attack, decay, sustainLevel, release
  ).kr(gate: gate, doneAction: Done.freeSelf);

  tablepos = wavetableBufNum + (waveMod * wavetableWaves);
  oscCount = 6;
  oscs = oscCount.collect({|i|
    var f = freq * (1 + (i * spread)) * (1 + modEnv);
    var g = 1/(i+1);
    VOsc.ar(tablepos, f, mul: g)
  });

  snd = Mix.new(oscs) * 0.5;
  snd = Fold.ar(snd * (fold + 1), -1, 1);
  snd = ((snd * gain).tanh) * 1.1;
  snd = BMoog.ar(snd, cutoff * (1 + lfo), q) * volEnv * amp;
  Out.ar(out, snd * volEnv);
}).add;
)
