(
  SynthDef(\modkick, {
    var freq = \freq.kr(50);

    var modenv = Env.perc(0.0, \modDecay.kr(0.2)).ar;
    var modNoise = WhiteNoise.ar * \modNoise.kr(0.0005);
    var modosc = SinOsc.ar(freq * \ratio.kr(2)) * modenv * \modDepth.kr(0.5) * (1 + modNoise);

    var rampenv = Env.perc(\rampattack.kr(0.01), \rampdecay.kr(0.2), curve: \rampcurve.kr((-4))).ar;

    var venv = Env.perc(\attack.kr(0.01), \decay.kr(0.5), \amp.kr(0.8)).ar(Done.freeSelf);
    var snd = SinOsc.ar(freq * (rampenv * \ramp.kr(0.3) + 1) * (modosc + 1)) * venv;

    snd = BHiShelf.ar(snd, \eqPeak.kr(200), \eqRes.kr(1.5), \eqGain.kr(14));
    snd = (snd * \gain.kr(1.3)).tanh;
    snd = HPF.ar(snd, \hipass.kr(50));
    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  },
    variants:(
      synthetic: (modDepth: 18, decay: 2, ratio: 2.7, ramp: 16, rampattack: 0.0, rampdecay: 0.1, gain: 2)
    )
  ).add;


  SynthDef(\bkick,
    {
      var venv = Env.perc(\attack.kr(0.01), \decay.kr(0.8)).ar(Done.freeSelf);

      var penv = Env.perc(\rampattack.kr(0.0), \rampdecay.kr(0.02)).ar * \ramp.kr(2.0);
      var nenv = Env.perc(\noiseattack.kr(0.0), \noisedecay.kr(0.05)).ar * \noise.kr(0.3);
      var osc = Mix.new([
        SinOsc.ar(\freq.kr(50) * (penv + 1)) * venv,
        WhiteNoise.ar * nenv;
      ]);
      var snd = (osc * \gain.kr(1.0).tanh) * \amp.kr(0.8);

      Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
    },
    variants: (
      tight: (attack: 0.1, decay: 0.5, ramp: 25, rampattack: 0.0, rampdecay: 0.035, noiseattack: 0.0, noisedecay: 0.01, noise: 0.3, gain: 2)
    )
  ).add;


  SynthDef(\snaredrum, {
    var fenv = Env.perc(0.0, \filterDecay.kr(0.2)).ar;
    var venv = Env.perc(0.0, \decay.kr(0.6)).ar(Done.freeSelf);
    var nenv = Env.perc(0.0, \noiseDecay.kr(0.1)).ar;

    var snaposc = LPF.ar(HPF.ar(WhiteNoise.ar(1),500), 10000) * nenv;
    var drumosc = Pulse.ar(\freq.kr(100), (0.5 + (snaposc * \noiseMod.kr(0.2))));
    var filtered = LPF.ar(drumosc,(fenv * 1000) + 30);
    var output = Mix.new([
      filtered,
      snaposc * \noise.kr(0.3)
    ]);
    var snd = output * venv;

    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;


  SynthDef(\digiclap, {

    var delayTime = 0.01;
    //var noise = (BPF.ar(SinOscFB.ar(freq, feedback),filterFreq, q) * gain).tanh;
    var noise = WhiteNoise.ar;
    var venv = Env.perc(\attack.kr(0.01), \decay.kr(0.5)).ar(Done.freeSelf);
    var clps = 10.collect({|i|
      Env.perc(\cattack.kr(0.01), \cdecay.kr(0.05)).delay(i * \density.kr(8).reciprocal * delayTime).ar * noise;
    }) * venv;
    var snd = Mix.new(clps);

    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;


  SynthDef(\clap, {
    var venv = Env.perc(\attack.kr(0.01), \decay.kr(0.05)).ar(Done.freeSelf);
    var delayTime = 0.01;
    var noise = (BPF.ar(WhiteNoise.ar(1),\filterfreq.kr(1500), \resonance.kr(0.9)) * \gain.kr(3)).tanh;
    var snd = Mix.new(10.collect({|i|
      Env.perc(0, \clapdecay.kr(0.1)).delay(i * \density.kr(8).reciprocal * delayTime).ar * noise;
    }));
    snd = snd * venv;

    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;


  SynthDef(\rim, {
    var cenv = Env.perc(0.0, 1.8, \click.kr(0.3)).ar;
    var venv = Env.perc(0.0, \decay.kr(0.2), \amp.kr(0.8)).ar(Done.freeSelf);
    var osc = SinOscFB.ar(\freq.kr(50), feedback: cenv, mul: venv);
    var snd = HPF.ar(osc, \hipass.kr(200));

    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;


  SynthDef(\stick, {
    var freq = \freq.kr(200);
    var vattack = \attack.kr(0.0);
    var vdecay = \decay.kr(0.0);

    var penv = (Env.perc(0.0, \pdecay.kr(0.02)).ar * \bend.kr(0.7) + 1).reciprocal;
    var venv = Env.perc(vattack, vdecay).ar(Done.freeSelf);

    var modosc = SinOsc.ar(freq * \ratio.kr(1)) * \mod.kr(0.3);

    var clickenv = Env.perc(0.0, \clickdecay.kr(0.01)).ar * \click.kr(0.3);
    var clickosc = SinOsc.ar(freq * \clickratio.kr(1)) * clickenv;

    var nenv = Env.perc(\noiseattack.kr(0.0), \noisedecay.kr(0.1)).ar;
    var noise = LPF.ar(WhiteNoise.ar, \noiselpf.kr(5000)) * \noise.kr(0.1);

    var harmonics = [1, 4, 5, 9, 11];
    var amplitudes = [1, 0.6, 0.3, 0.4, 0.2, 0.1];
    var detune = \detune.kr(0.01);
    var snd = Mix.new(harmonics.collect({ arg h, idx;
      SinOsc.ar(freq * (1 + (idx * detune)) * h * (clickosc + 1) * (noise + 1) * (modosc + 1)) * amplitudes.wrapAt(idx)
    }));
    snd = HPF.ar(snd, \hipass.kr(200));
    snd = LPF.ar(snd, \lopass.kr(10000));
    snd = snd * venv * \amp.kr(1);

    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;


  SynthDef(\clikr, {
    var env = Env.asr(\attack.kr(0.0), \amp.kr(1), \decay.kr(1)).ar(Done.freeSelf, \gate.kr(1));
    var osc = Blip.ar(\freq.kr(200), \harmonics.kr(200));
    var snd = osc * env;
    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;

  SynthDef(\fmhat, {
    var freq = \freq.kr(100);
    var venv = Env.perc(\attack.kr(0.01), \decay.kr(0.1)).ar(Done.freeSelf);
    var penv = Env.perc(\pitchEnvAttack.kr(0.01), \pitchEnvDecay.kr(0.1), \pitchEnvDepth.kr(0)).ar;
    var menv = Env.perc(\modEnvAttack.kr(0.1), \modEnvDecay.kr(0.5)).ar * \modDepth.kr(0.1);

    var modOsc = (SinOsc.ar(freq * \ratio.kr(1)) * menv);
    var noiseOsc = (WhiteNoise.ar * \noiseMod.kr(0.1));
    var fmOut = SinOsc.ar(freq * (modOsc + 1) * (penv + 1) * (\bend.kr(0) + 1) * (noiseOsc + 1));
    var noise = WhiteNoise.ar() * \noise.kr(0.4);
    var snd = HPF.ar(fmOut + noise, \hipass.kr(1000)) * \amp.kr(1) * venv;

    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;



)
