(
  SynthDef(\modkick, {
    var freq = \freq.kr(50);

    var modenv = Env.perc(0.0, \moddecay.kr(0.2)).ar();
    var modosc = SinOsc.ar(freq * \ratio.kr(2)) * modenv * \moddepth.kr(0.5);

    var rampenv = Env.perc(\rampattack.kr(0.01), \rampdecay.kr(0.2), curve: \rampcurve.kr((-4))).ar();

    var venv = Env.perc(\attack.kr(0.01), \decay.kr(0.5), \amp.kr(0.8)).ar(Done.freeSelf);
    var osc = SinOsc.ar(freq * (rampenv * \ramp.kr(0.3) + 1) * (modosc + 1), mul: venv);

    var snd = (osc * \gain.kr(1.3)).tanh;
    snd = HPF.ar(snd, \hipass.kr(50));
    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  },
    variants:(
      synthetic: (moddepth: 18, moddecay: 0.03, decay: 2, ratio: 2.7, ramp: 16, rampattack: 0.0, rampdecay: 0.1, gain: 2)
    )
  ).add;


  SynthDef('bkick',
    {
      var venv = Env.perc(\attack.kr(0.01), \decay.kr(0.8)).kr(Done.freeSelf);

      var penv = Env.perc(\rampattack.kr(0.0), \rampdecay.kr(0.02)).ar() * \ramp.kr(2.0);
      var nenv = Env.perc(\noiseattack.kr(0.0), \noisedecay.kr(0.05)).ar() * \noise.kr(0.3);
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


  SynthDef(\snaredrum, {arg out=0, freq=100,
    decay = 0.6,
    noiseMod = 0.2,
    filterDecay = 0.2,
    noise = 0.3, noiseDecay = 0.1;

    var fenv = Env.perc(0.0, filterDecay).kr();
    var venv = Env.perc(0.0, decay).kr(2);
    var nenv = Env.perc(0.0, noiseDecay).kr(0);

    var snaposc = LPF.ar(HPF.ar(WhiteNoise.ar(1),500), 10000) * nenv;
    var drumosc = Pulse.ar(freq, (0.5 + (snaposc * noiseMod)));
    var filtered = LPF.ar(drumosc,(fenv * 1000) + 30);
    var output = Mix.new([
      filtered,
      snaposc * noise
    ]);
    var snd = output * venv;

    Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
  }).add;


  SynthDef(\digiclap, {

    var delayTime = 0.01;
    //var noise = (BPF.ar(SinOscFB.ar(freq, feedback),filterFreq, q) * gain).tanh;
    var noise = WhiteNoise.ar;
    var venv = Env.perc(\attack.kr(0.01), \decay.kr(0.5)).kr(Done.freeSelf);
    var clps = 10.collect({|i|
      Env.perc(\cattack.kr(0.01), \cdecay.kr(0.05)).delay(i * \density.kr(8).reciprocal * delayTime).kr() * noise;
    }) * venv;
    var snd = Mix.new(clps);

    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;


  SynthDef(\clap, {
    var venv = Env.perc(\attack.kr(0.01), \decay.kr(0.05)).kr(Done.freeSelf);
    var delayTime = 0.01;
    var noise = (BPF.ar(WhiteNoise.ar(1),\filterfreq.kr(1500), \resonance.kr(0.9)) * \gain.kr(3)).tanh;
    var snd = Mix.new(10.collect({|i|
      Env.perc(0, \clapdecay.kr(0.1)).delay(i * \density.kr(8).reciprocal * delayTime).kr() * noise;
    }));
    snd = snd * venv;

    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;


  SynthDef(\rim, {arg out, freq=50, hipass=200, decay=0.2, click=0.3, amp=0.8;
    var cenv = Env.perc(0.0, 1.8, click).kr();
    var venv = Env.perc(0.0, decay, amp).kr(2);
    var osc = SinOscFB.ar(freq, feedback: cenv, mul: venv);
    var snd = HPF.ar(osc, hipass);

    Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
  }).add;


  SynthDef(\stick, {
    var freq = \freq.kr(200);
    var vattack = \attack.kr(0.0);
    var vdecay = \decay.kr(0.0);

    var penv = (Env.perc(0.0, \pdecay.kr(0.02)).kr() * \bend.kr(0.7) + 1).reciprocal;
    var venv = Env.perc(vattack, vdecay).kr(Done.freeSelf);

    var modosc = SinOsc.ar(freq * \ratio.kr(1)) * \mod.kr(0.3);

    var clickenv = Env.perc(0.0, \clickdecay.kr(0.01)).kr() * \click.kr(0.3);
    var clickosc = SinOsc.ar(freq * \clickratio.kr(1)) * clickenv;

    var nenv = Env.perc(\noiseattack.kr(0.0), \noisedecay.kr(0.1)).kr();
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


  SynthDef(\clikr, {arg out, gate=1, attack=0.0, decay=1, freq=200, harmonics=200, amp=1;
    var env = Env.asr(attack, amp, decay).kr(doneAction: 2, gate: gate);
    var osc = Blip.ar(freq, harmonics);
    var snd = osc * env;
    Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
  }).add;

  SynthDef(\fmhat, {
    arg out=0, gate=1, freq=220, amp=0.8,
    attack=0.01, decay=0.1, level=1,
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
    var snd = HPF.ar(fmOut + noise, hpcutoff);

    Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
  }).add;



)
