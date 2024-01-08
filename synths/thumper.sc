(

SynthDef(\thumper, {

  var penv = Env.perc(\pattack.kr(0.01), \pdecay.kr(0.1), \pmod.kr(0.1), \pcurve.kr(-4.0)).kr(0);
  var venv = Env.perc(\attack.kr(0.01), \decay.kr(0.5), \amp.kr(0)).kr(2);
  var nenv = Env.perc(\nattack.kr(0.01), \ndecay.kr(0.2), \noise.kr(0)).kr(0);
  var menv = Env.perc(\mattack.kr(0.01), \mdecay.kr(0.2), \modEnv.kr(0)).kr(0);

  var noiseSig = WhiteNoise.ar * nenv;
  var makeup = 16;
  var oscCount = 6;
  var freq = \freq.kr(50);
  var spread = \spread.kr(0);
  var modosc = Silent.ar;
  var snd = Mix.new(oscCount.collect({|i|
    var f, osc;
    f = freq;
    f = f * (1 + penv);
    f = f * (1 + (i * spread));
    f = f * (1 + (modosc * \mod.kr(0) * menv));
    osc = LinSelectX.ar(\wave.kr(0.0), [
      SinOsc.ar(f), BlitB3Tri.ar(f), Saw.ar(f), Pulse.ar(f)
    ]) * (1/6);
    modosc = osc;
    osc;
  }));

  snd = (snd * venv) + noiseSig;
  snd = snd * venv;
  snd = Fold.ar(snd * (\fold.kr(0) + 1), -1, 1);
  snd = Clip.ar(snd * (\clip.kr(0.95) + 1), -1.0, 1.0);
  snd = (snd * \gain.kr(1)).tanh * 3.dbamp;
  Out.ar(\out.kr(0), snd);
}).add;

)
