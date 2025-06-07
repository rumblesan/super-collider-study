
(
  SynthDef(\buzz1, {
    var freq = \freq.kr(200);

    var venv = Env.asr(\attack.kr(0.01), \amp.kr(0.8), \release.kr(0.01)).kr(Done.freeSelf, \gate.kr(1));

    var snd = LinSelectX.ar(\wave.kr(0.0), [
      SinOsc.ar(freq), BlitB3Tri.ar(freq), Saw.ar(freq), Pulse.ar(freq)
    ]);
    snd = (snd * \fold.kr(1.3)).fold2;
    snd = (snd * \clip.kr(1.3)).clip2;
    snd = (snd.amclip(SinOsc.ar(freq * \ratio.ar(3))));

    Out.ar(\out.kr(0), snd * venv);
  },
    variants: (
      low:  [attack: 0.0, release: 0.03, clip: 1, fold: 1, ratio: 2],
      mid: [attack: 0.0, release: 0.03, clip: 7, fold: 1.3, ratio: 7],
      high: [attack: 0.0, release: 0.03, clip: 17, fold: 1.3, ratio: 17],
      thin: [attack: 0.0, release: 0.03, clip: 21, fold: 31, ratio: 1],
      laser: [attack: 0.0, release: 0.03, clip: 1, fold: 7, wave: 0.97],
    )
  ).add;


  SynthDef(\buzz2, {
    var freq = \freq.kr(200);

    var venv = Env.asr(\attack.kr(0.01), \amp.kr(0.8), \release.kr(0.01)).kr(Done.freeSelf, \gate.kr(1));
    var sampenv = Env.linen(\sampenvattack.kr(0.01), \sampenvduration.kr(0.1), \sampenvdecay.kr(0.01), \sampenvdepth.kr(1)).kr();
    var snd = SinOsc.ar(freq);
    snd = Decimator.ar(snd, (\samplerate.kr(1.0) * (1 - sampenv)) * 44100, \bits.kr(24));
    snd = (snd * \gain.kr(1.3)).clip2;

    Out.ar(\out.kr(0), snd * venv);
  }).add;


(
  SynthDef(\pblip, {
    var snd = LFPulse.ar(\freq.kr(220), 0, \width.kr(0.5));
    var venv = Env.perc(\attack.kr(0.0), \decay.kr(0.05)).kr(Done.freeSelf);
    venv = venv * (1 + \penv.kr(1));
    Out.ar(\out.kr(0), snd * venv * \amp.kr(1.0));
  }).add;
)

)
