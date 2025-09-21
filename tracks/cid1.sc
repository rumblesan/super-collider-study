
p.clock.tempo = 160/60;

(
  SynthDef(\pad1, {
    var freq = \freq.kr(220);
    var detune = \detune.kr(0.01);
    var snd = Mix.new([
      Pulse.ar(freq * (1 + detune)),
      Pulse.ar(freq),
      Pulse.ar(freq * (1 - detune)),
    ]) * 0.3;

    var venv = Env.asr(\attack.kr(1), \amp.kr(1.0), \decay.kr(3)).kr(Done.freeSelf, \gate.kr(1.0));

    snd = RLPF.ar(snd, freq * \foffset.kr(1.1), 0.5);
    Out.ar(\out.kr(0), snd * venv);
  }).add;
)

Ndef(\pads, Pbind(
  \instrument, \pad1,
  \root, -5,
  \octave, 5,
  \scale, Scale.minor,
  \degree, Pseq([
    [-7, 0, 3, 7],
    [-7, 0, 3, 7] - 1,
    [-7, 2, 4, 9] + 2,
  ], inf),
  \detune, 0.02,
  \foffset, 4,
  \dur, 16,
)
)
Ndef(\pads).clear;
Ndef(\pads).scope
Ndef(\pads).quant = 16;

Ndef(\kick, Pbind(
  \instrument, \bkick,
  \freq, 50,
  \attack, 0.05,
  \decay, Pwrand([0.5, 2], [10, 1].normalizeSum, inf),
  \ramp, 16,
  \rampattack, 0.0,
  \rampdecay, 0.035,
  \noiseattack, 0.01,
  \noisedecay, Pseq([Pn(0.01, 4), 0.5, 0.01, 0.5, Pn(0.02, 3)], inf),
  \noise, 0.3,
  \gain, 5,
  \amp, 1,
  //\dur, Pseq([1.5, 0.5, 2.5, 0.5, 1, 0.5, 0.5, 2], inf),
  \dur, Pseq([0.5, 0.5, 3, 1, 2], inf),
)
)

Ndef(\clap, Pbind(
  \instrument, \clap,
  \attack, 0.01,
  \decay, 0.5,
  \clapdecay, 0.1,
  \filterfreq, 1000,
  \amp, 1,
  \dur, Pseq([5, 3, 5, 2, 5], inf),
)
)
Ndef(\clap).clear;

Ndef(\bz,
  Pbind(
    \instrument, \buzz2,
    \octave, 4,
    \degree, Pseq([Pn(0, 4), 7, 2, Pn(0, 3), 7, 2], inf),
    \attack, 0.1,
    \decay, 0.2,
    \bits, Pseq([5, 3, 5, 8, 5, 2, 1, 2], inf),
    \duration, 0.01,
    \samplerate, Pwrand([0.1, 0.7, 0.01], [10, 2, 1].normalizeSum, inf),
    \sampattack, 0,
    \sampduration, 0.3,
    \amp, 1,
    \dur, 0.5,
  )
)
Ndef(\bz).clear;

// not sure about this bit
Ndef(\fn,
  Pbind(
    \instrument, \clikr,
    \degree, 0,
    \attack, 0.0,
    \decay, 0.05,

    \amp, 1,
    \dur, Pseq([1, 1, 2, 1, 1, 3, 1, 1], inf) / 2,
  )
)
Ndef(\fn).clear;
