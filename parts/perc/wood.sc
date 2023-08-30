
p.clock.tempo = 180/60;

Ndef(\perc,
  Pbind(
    \instrument, \jarrah,
    \freq, 200,
    \decay, 0.3,
    \noise, 0.1,
    \dur, 0.5,
    \hipass, 400,
    \amp, Pseq([1, 0.8, 0.8], inf),
  )
)
Ndef(\perc).play;

Ndef(\perc).clear;

(
  SynthDef(\jarrah, {
    var freq = \freq.kr(220);

    var noise = WhiteNoise.ar * \noise.kr(0.2);

    var venv = Env.perc(0.0001, \decay.kr(0.1)).ar();
    var env1 = Env.perc(0.0001, \decay1.kr(0.1)).ar();
    var env2 = Env.perc(0.0001, \decay2.kr(0.1)).ar();

    var carrier1 = SinOsc.ar(freq * 6) * \carrier1mod.kr(0.3);
    var fundamental1 = SinOscFB.ar(freq * 3.46, carrier1) * \fundamental2mod.kr(0.6);
    var fundamental2 = SinOsc.ar(freq * 5.19, \feedback.kr(0.6)) * \fundamental1mod.kr(0.6);
    var snd = SinOsc.ar(freq * 0.9995 * (noise + 1), fundamental1 + fundamental2);
    snd = snd * venv;
    snd = snd * \amp.kr(1);
    snd = HPF.ar(snd, \hipass.kr(100));

    Out.ar(\out.kr(0), snd);
  }).add;
)
