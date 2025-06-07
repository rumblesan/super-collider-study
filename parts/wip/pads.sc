
(
  SynthDef(\move7, {

    var freq = \freq.kr(220);
    var phaseosc = SinOsc.ar(freq * \phaseoscratio.kr(1) );

    var modosc = LFTri.ar(freq * \modoscratio.kr(1), (phaseosc + 1) * \phasemod.kr(0.1));

    var detune = \detune.kr(0.01);
    var snd = Mix.new([
      LFTri.ar((freq * (1 + detune)) * ((modosc * \mod.kr(0.1)) + 1)),
      LFTri.ar((freq) * ((modosc * \mod.kr(0.1)) + 1)),
      LFTri.ar((freq * (1 + detune)) * ((modosc * \mod.kr(0.1)) + 1))
    ]);

    var venv = Env.asr(\attack.kr(1), \amp.kr(1.0), \decay.kr(3)).kr(Done.freeSelf, \gate.kr(1.0));

    snd = BPF.ar(snd, \ffreq.kr(440), \resonance.kr(0.7));

    Out.ar(\out.kr(0), snd * venv);
  }).add;
)

Ndef(\pads,
  Pbind(
    \instrument, \move7,
    \root, -7,
    \degree, 0,
    \octave, 4,
    \attack, 1,
    \decay, 4,
    \legato, 0.7,
    \amp, 1,
    \dur, 16,
  )
)
Ndef(\pads).clear;

/*

  a = Synth(\move7);

  a.set(\detune, 0.001)

  a.set(\gate, 0);

  a = Synth(\move7,[
    \mod, 2.8,
    \modoscratio, 1.0,
    \resonance, 3,
    \detune, 0.031,
    \phasemod, 3,
    \phaseoscratio, 2.0,
  ]);
  a.set(\gate, 0);

*/
