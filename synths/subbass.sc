(
  SynthDef(\subbass, {
    var freq = \freq.kr(50);
    var env = Env.asr(
      \attack.kr(0.1),
      1,
      \release.kr(0.5)
    ).kr(Done.freeSelf, \gate.kr(1));

    var oscCount = 2;
    var snd = Mix.new(oscCount.collect({|i|
      var d = (i * \detune.kr(0.01) * freq);
      Pulse.ar(freq + d, \pulsewidth.kr(0.5));
    }));
    snd = (snd * \gain.kr(1)).tanh * 1.2;
    snd = RLPF.ar(snd, freq * (1 + \filteroffset.kr(0)), 0.85);
    snd = snd * \amp.kr(1);
    Out.ar(\out.kr(0), snd);
  }).add;
)


/*
Ndef(\bass,
    Pbind(
        \instrument, \subbass,
        \degree, Pseq([-3, -2, 0, 1], inf),
        \octave, 3,
        \attack, 0.1,
        \decay, 16,
        \amp, 1,
        \dur, 16,
    )
)
Ndef(\bass).clear;
*/
