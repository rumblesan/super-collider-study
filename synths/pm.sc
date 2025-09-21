(
  SynthDef(\pm2, {
    var freq = \freq.kr(200);
    var gate = \gate.kr(1);
    var venv = Env.adsr(\attack.kr(0.1), \decay.kr(0.1), \level.kr(0.8), \release.kr(0.5)).ar(Done.freeSelf, gate);
    var penv = Env.perc(\pAttack.kr(0.01), \pDecay.kr(0.1), \pDepth.kr(0)).ar(gate: gate, levelBias: 1);
    var menv = Env.adsr(\modAttack.kr(0.1), \modDecay.kr(0.01), \modLevel.kr(0.1), \modRelease.kr(0.5)).ar(gate: gate);

    var modOsc = SinOsc.ar(freq * \ratio.kr(1.0), mul: menv) * (1 + (WhiteNoise.ar * (\noiseMod.kr(0.1) / 100)));
    var snd = SinOsc.ar(freq * penv * (1 + \bend.ar(0)), phase: modOsc * \mod.kr(1.0), mul: venv * \amp.kr(0.8));
    snd = (snd * (\gain.kr(1) / 2.1)).tanh * 2;

    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;
)
