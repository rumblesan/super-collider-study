(
  SynthDef(\modkick, {arg out,
    freq=50, attack=0.01, decay=0.2,
    ramp=0.3, rampattack=0.01, rampdecay=0.2,
    moddepth=0.5, modratio=2, moddecay=0.2,
    gain=1.3, amp=0.8;

    var modenv = Env.perc(0.0, moddecay, moddepth).kr();
    var modosc = SinOsc.ar(freq * modratio, add: 1, mul: modenv);

    var rampenv = Env.perc(rampattack, rampdecay, ramp).kr() + 1;

    var venv = Env.perc(attack, decay, amp).kr(2);
    var osc = SinOsc.ar(freq * modosc * rampenv, mul: venv);

    Out.ar(out, (osc * gain).tanh);
  }).add;

  /*
  (
    ~kickz = Pbind(
      \instrument, \modkick,
      \freq, 50,
      \decay, 1.0,
      \moddepth, 2.2,
      \modratio, 0.5,
      \moddecay, 0.8,
      \ramp, 2.5,
      \rampdecay, 0.1,
      \amp, 1,
      \gain, 2,
      \dur, 1,
    )
  )

  ~kickz.play;
  ~kickz.quant = 4;
  ~kickz.stop;
  */

  SynthDef(\bkick, {arg out, amp=0.8,
    freq=50, attack=0.01, decay=0.8,
    ramp=2.0, rampattack=0.0, rampdecay=0.2,
    gain = 1.0;

    var venv = Env.perc(attack, decay, amp).kr(2);

    var penv = Env.perc(rampattack, rampdecay, ramp).kr(0);

    var osc = SinOsc.ar(freq + (penv * freq), mul: venv);

    Out.ar(out, (osc * gain).tanh);
  }).add;

  /*
  (
    ~kickz = Pbind(
      \instrument, \bkick,
      \freq, 50,
      \attack, 0.01,
      \decay, 1.0,
      \ramp, 2.0,
      \rampattack, 0.00,
      \rampdecay, 0.2,
      \amp, 1,
      \gain, 1.0,
      \dur, 2,
    )
  )

  ~kickz.play;
  ~kickz.quant = 4;
  ~kickz.stop;
  */


  SynthDef(\rim, {arg out, freq=50, hipass=200, decay=0.2, click=0.3, amp=0.8;
    var cenv = Env.perc(0.0, 1.8, click).kr();
    var venv = Env.perc(0.0, decay, amp).kr(2);
    var osc = SinOscFB.ar(freq, feedback: cenv, mul: venv);

    Out.ar(out, HPF.ar(osc, hipass));
  }).add;

  SynthDef(\clikr, {arg out, gate=1, attack=0.0, decay=1, freq=200, harmonics=200, amp=1;
    var env = Env.asr(attack, amp, decay).kr(doneAction: 2, gate: gate);
    var osc = Blip.ar(freq, harmonics);
    Out.ar(out, osc * env);
  }).add;

  SynthDef(\fmhat, {
    arg out=0, gate=1, freq=220, amp=0.8,
    attack=0.01, decay=0.1, level=1, release=0.5,
    pitchEnvAttack=0.01, pitchEnvDecay=0.1, pitchEnvDepth=0,
    bend=0,
    ratio=1.0, hpcutoff=1000,
    noiseMod=0.1, noiseLevel=0.4,
    modEnvAttack=0.1, modEnvDecay=0.01, modDepth=0.1, modEnvRelease=0.5;
    var venv = Env.perc(attack, decay).kr(2, gate);
    var penv = Env.perc(pitchEnvAttack, pitchEnvDecay, pitchEnvDepth).kr(gate: gate, levelBias: 1);
    var menv = Env.adsr(modEnvAttack, modEnvDecay, modDepth, modEnvRelease).kr(2, gate);

    var modOsc = SinOsc.ar(freq * ratio, mul: menv, add: 1);
    var noiseOsc = WhiteNoise.ar(mul:noiseMod, add: 1.0);
    var fmOut = SinOsc.ar(freq * modOsc * penv * (1+bend) * noiseOsc, mul: venv * amp);
    var noise = WhiteNoise.ar() * noiseLevel * venv * amp;
    var filtered = HPF.ar(fmOut + noise, hpcutoff);

    Out.ar(out, filtered);
  }).add;



)
