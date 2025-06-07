
(

  SynthDef(\sinbass, {
    var snd, venv;
    venv = Env.asr(\attack.kr(1), 1, \release.kr(3)).kr(Done.freeSelf, \gate.kr(1));
    snd = SinOsc.ar(\freq.ar(50) + \bend.ar(0));
    snd = ((snd * \gain.ar(1.0)).tanh) * 1.3;
    snd = snd * \amp.ar(1.0);
    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;

  SynthDef(\blip, {
    var snd, env, pulse, blip;
    env = Env.perc(0.0, \decay.ar(0.001), 1.0, \curve.ar(-4.0)).ar();
    pulse = Impulse.ar(\impulsefreq.ar(5));
    blip = Env.perc(0.0, \blipdecay.ar(0.5), 1.0, \curve.ar(-4.0)).ar(Done.freeSelf);
    snd = LPF.ar(((blip * 0.3) + pulse), \freq.ar(200), \q.ar(0.7)) * \amp.kr(1.0);
    snd = (snd * \gain.ar(20)).clip2;
    Out.ar(\out.kr(0),
      Pan2.ar(snd, \pan.kr(0))
    );
  }).add;

  SynthDef(\ping, {
    var snd, venv, penv;
    venv = Env.perc(\attack.ar(0.01), \decay.ar(0.5)).kr(Done.freeSelf);
    penv = Decay2.kr(\ptrig.kr, 0.01, \pdecay.ar(0.1)) * \pmod.ar(0);
    snd = SinOsc.ar(\freq.ar(200) * (penv + 1)) * \amp.ar(1);
    snd = CrossoverDistortion.ar(snd, \distamp.ar(0.98), \distsmooth.ar(0.5));
    snd = snd * venv;
    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.ar(0)));
  }).add;

  SynthDef('elkick',
    {
      var venv = Env.perc(\attack.kr(0.01), \decay.kr(0.8)).kr(Done.freeSelf);

      var penv = Env.perc(\rampattack.kr(0.0), \rampdecay.kr(0.02)).ar() * \ramp.kr(2.0);
      var nenv = Env.perc(\noiseattack.kr(0.0), \noisedecay.kr(0.05)).ar() * \noise.kr(0.3);
      var osc = Mix.new([
        SinOsc.ar(\freq.kr(50) * (penv + 1)) * venv,
        WhiteNoise.ar * nenv;
      ]);

      Out.ar(\out.kr(0), (osc * \gain.kr(1.0)).tanh) * \amp.kr(0.8);
    }).add;

)
