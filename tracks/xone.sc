

(
  SynthDef(\trz, {

    var noise = Mix.new(10.collect({|i| Impulse.ar(220 + (i * \spread.kr(20))) }));
    var modosc = SinOsc.ar(\freq.kr(200));
    var snd = GlitchBPF.ar(noise, \ffreq.kr(220) * ((modosc * \moddepth.kr(0.2)) + 1), 0.9);
    var venv = Env.perc(\attack.kr(0.01), \decay.kr(0.3)).kr(Done.freeSelf);
    snd = snd * venv;
    snd = (snd * \gain.kr(3)).tanh;

    Out.ar(\out.kr(0), snd * \amp.kr(1.0));
  }).add;
)

~faders.scale(9, 100, 2000)

Ndef(\xone1,
  Pbind(
    \instrument, \trz,
    \degree, 0,
    \octave, 6,
    \moddepth, 0.5,
    \ffreq, 1200,
    \spread, Pshuf([1,1,1,1,1,2,10,1,1,1,1,7,1], inf),
    \attack, 0.01,
    \decay, Pwrand([0.03, 0.05], [10, 1].normalizeSum, inf) / 3,
    \gain, 10,
    \amp, 1,
    \dur, Pshuf([1,1,1,2,1,1,1,2,1,], inf) / 4,
  )
)
Ndef(\xone1).clear;
