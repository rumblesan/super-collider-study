
(
  SynthDef(\imbuia, {
    var freq = \freq.kr(220);

    var noise = WhiteNoise.ar * \noise.kr(0.2);

    var stickenv = Env.perc(0.0001, \stickdecay.kr(0.1)).ar;
    var stickmodosc = SinOsc.ar(freq * 0.58 * 1.003) * \stickmod.kr(1);
    var stickosc = SinOsc.ar(freq * 12.11 * (noise + 1), stickmodosc);

    var impactenv = Env.perc(0.0001, \decay.kr(0.1)).ar(Done.freeSelf);
    var impactmodosc = SinOscFB.ar(freq * 5.01 * 1.001, \feedback.kr(0.1)) * \impactmod.kr(0.6);
    var impactosc = SinOsc.ar(freq * 7 * 0.9995 * (noise + 1), impactmodosc);

    var snd = Mix.new([
      //stickosc * stickenv,
      impactosc * impactenv,
    ]);

    snd = snd * \amp.kr(1);
    snd = HPF.ar(snd, \hipass.kr(100));

    Out.ar(\out.kr(0), Pan2.ar(snd, \pan.kr(0)));
  }).add;
)
