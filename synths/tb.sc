
(

SynthDef(\tb, {
  arg out=0, freq=440, freqlag=0.1, gate=1, amp=1,
  attack=0.04, decay=0.2, level=0.6, release=0.1,
  fattack=0.04, fdecay=0.2, flevel=0.6, frelease=0.1,
  envmod=0.7,
  cutoff=2000, resonance = 0.3,
  wave=0.2, gain=0.3
  ;
    var f = Lag.kr(freq, freqlag);
    var fenv = Env.adsr(fattack, fdecay, flevel, frelease).kr(gate: gate);
    var venv = Env.adsr(attack, decay, level, release).kr(2, gate: gate);
    var osc = XFade2.ar(Pulse.ar(f), Saw.ar(f), (wave * 2) - 1) * gain;
    var sig = BMoog.ar(osc, ((fenv * envmod) + 1) * cutoff, resonance) * gain.min(1).reciprocal;
    Out.ar(out, sig * venv * amp)
}).add;

)
