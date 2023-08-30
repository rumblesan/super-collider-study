(
  SynthDef(\frozensines, {
    var noiseamount = \noise.kr(0.1);
    var noise = 4.collect({PinkNoise.ar * noiseamount + 1});
    var freq = \freq.kr(220);
    var detune = [0, 0.1, 0.3, 0.7] * \detune.kr(0.001) + 1;
    var snd = Mix.new(detune.collect({|d, idx|
      SinOsc.ar(freq * d * noise.wrapAt(idx))
    }));

    var venv = Env.asr(\attack.kr(1), \amp.kr(1.0), \release.kr(3)).kr(Done.freeSelf, \gate.kr(1));

    snd = snd * venv;

    Out.ar(\out.kr(0), snd);
  }).add;
)
