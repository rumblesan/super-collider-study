(
  SynthDef(\abrade, {
    var freq = \freq.kr(50) * (1 + \bend.kr(0));
    var modOsc = SinOsc.ar(freq * 15);
    var snd = SinOsc.ar(freq, modOsc * \mod.kr(0.01));
    var ring = (Pulse.ar(freq) + 1.0) / 2.0;
    var highs = WhiteNoise.ar * ring;
    var env = Env.asr(
      \attack.kr(0.1),
      1,
      \release.kr(0.5)
    ).kr(Done.freeSelf, \gate.kr(1));
    var mix = ((snd + highs) * \gain.kr(0.5)).tanh;
    var clipped = (mix * \clip.kr(0.5)).clip2;
    Out.ar(\out.kr(0), clipped * env * \amp.kr(1.0));
  }).add;
)

Ndef(\grow, Pbind(
    \instrument, \abrade,
    \freq, Pwrand([50, 75, 100, 25], [20, 3, 0, 4].normalizeSum, inf),
    \attack, 0.01,
    \decay, 0.08,
    \legato, 0.009,
    \mod, 0.5,
    \gain, Pseq([8, 3, 8, 5, 8, 10, 8, 8, 5], inf) + 2,
    \clip, Pseq([8, 3, 5, 8, 10, 8, 8, 5], inf),
    \amp, 1,
    \dur, Pseq([4, 2, 1, 1, 0.5, 0.5, 1], inf),
  )
)
Ndef(\grow).clear;

Ndef(\grow).map(\mod, Ndef(\fm))

Ndef(\fm,
  Pcontrol(
    \mod,
    \value, Pseq([0.1, Pn(0.9, 1), Pn(0.3, 5)], inf),
    \slew, 0,
    \dur, Pseq([1, 1, 8, 1, 1, 3, 1, 2], inf) / 2,
    \dur, 0.25,
  )
)
Ndef(\fm).quant = 4;

Ndef(\grow).map(\bend, Ndef(\blip))
Ndef(\blip,
  Pcontrol(
    \percenv,
    \attack, 0.01,
    \decay, 0.4,
    \curve, -10,
    \value, 0,
    \offset, 0,
    \dur, Prand([
      Pseq([3, 3, 2]) / 2,
      Pseq([2]) / 2,
    ], inf)
  )
)
Ndef(\blip).quant = 4;
