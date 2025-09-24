(

  SynthDef(\sinbass, {
    var snd, venv;
    venv = Env.asr(\attack.kr(1), 1, \decay.kr(3)).ar(Done.freeSelf, \gate.kr(1));
    snd = SinOsc.ar(\freq.kr(50) + \bend.kr(0));
    snd = ((snd * \gain.kr(1.0)).tanh) * 1.3;
    snd = snd * \amp.kr(1.0);
    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;

  SynthDef(\blip, {
    var snd, env, pulse, blip;
    env = Env.perc(0.0, \decay.kr(0.001), 1.0, \curve.kr(-4.0)).ar;
    pulse = Impulse.ar(\impulsefreq.kr(5));
    blip = Env.perc(0.0, \blipdecay.kr(0.5), 1.0, \curve.kr(-4.0)).ar(Done.freeSelf);
    snd = LPF.ar(((blip * 0.3) + pulse), \freq.kr(200), \q.kr(0.7)) * \amp.kr(1.0);
    snd = (snd * \gain.kr(20)).clip2;
    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;

  SynthDef(\ping, {
    var snd, venv, penv;
    venv = Env.perc(\attack.kr(0.01), \decay.kr(0.5)).ar(Done.freeSelf);
    penv = Decay2.kr(\ptrig.kr, 0.01, \pdecay.kr(0.1)) * \pmod.kr(0);
    snd = SinOsc.ar(\freq.kr(200) * (penv + 1)) * \amp.kr(1);
    snd = CrossoverDistortion.ar(snd, \distamp.kr(0.98), \distsmooth.kr(0.5));
    snd = snd * venv;
    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;

  SynthDef(\elkick, {
    var venv = Env.perc(\attack.kr(0.01), \decay.kr(0.8)).ar(Done.freeSelf);

    var penv = Env.perc(\rampattack.kr(0.0), \rampdecay.kr(0.02)).ar * \ramp.kr(2.0);
    var nenv = Env.perc(\noiseattack.kr(0.0), \noisedecay.kr(0.05)).ar * \noise.kr(0.3);
    var osc = Mix.new([
      SinOsc.ar(\freq.kr(50) * (penv + 1)) * venv,
      WhiteNoise.ar * nenv;
    ]);
    var snd = ((osc * \gain.kr(1.0)).tanh) * \amp.kr(0.8);

    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;

)
