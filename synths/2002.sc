
(
  SynthDef(\buzz1, {
    var freq = \freq.kr(200);

    var venv = Env.linen(\attack.kr(0.01), \duration.kr(0.1), \decay.kr(0.01)).kr(Done.freeSelf);
    var snd = SinOsc.ar(freq);
    snd = (snd * \foldgain.kr(1.3)).fold2;
    snd = (snd * \clipgain.kr(1.3)).clip2;
    snd = (snd.amclip(SinOsc.ar(freq * \ratio.ar(3)) * \amgain.kr(1.3)));

    Out.ar(\out.kr(0), snd * venv);
  },
    variants: (high: [freq: 50, attack: 0.1, decay: 0.03, clipgain: 7, ratio: 7])
  ).add;


/*
  s.queryAllNodes
  Synth(\buzz1, [\freq, 50, \attack, 0.1, \decay, 0.03, \clipgain, 7, \ratio, 7])

  Synth(\buzz1, [\freq, 50, \attack, 0.1, \decay, 0.03, \amgain, 7, \ratio, 3])

  Synth(\buzz1, [\freq, 50, \attack, 0.1, \decay, 1.3, \amgain, 2, \ratio, 4, \foldgain, 3])

  Synth('buzz1.high')
*/

  SynthDef(\buzz2, {
    var freq = \freq.kr(200);

    var venv = Env.linen(\attack.kr(0.01), \duration.kr(0.1), \decay.kr(0.01)).kr(Done.freeSelf);
    var sampenv = Env.linen(\sampattack.kr(0.01), \sampduration.kr(0.1), \sampdecay.kr(0.01)).kr();
    var snd = SinOsc.ar(freq);
    snd = Decimator.ar(snd, \samplerate.kr(1.0) * 44100 * sampenv, \bits.kr(24));
    snd = (snd * \gain.kr(1.3)).clip2;

    Out.ar(\out.kr(0), snd * venv);
  }).add;


/*

  Synth(\buzz2, [\freq, 50, \bits, 5, \decay, 0.3, \duration, 0.6, \samplerate, 0.1, \sampattack, 0, \sampduration, 0.3])

  Synth(\buzz2, [\freq, 50, \bits, 18, \decay, 0.3, \duration, 0.6, \samplerate, 0.1, \sampattack, 0, \gain, 9, \sampduration, 0.3, \sampdecay, 0.1])

*/

(
  SynthDef(\pblip, {
    var snd = LFPulse.ar(\freq.kr(220), 0, \width.kr(0.5));
    var venv = Env.perc(\attack.kr(0.0), \decay.kr(0.05)).kr(Done.freeSelf);
    Out.ar(\out.kr(0), snd * venv * \amp.kr(1.0));
  }).add;
)

)
