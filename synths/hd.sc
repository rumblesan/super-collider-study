(
  SynthDef(\hd, {arg out, freq=50, gate=1, amp=1, pan=0,
    mod=0, fold=0, bend=0,
    attack=0.1, decay=0.5, level=1, release=0.5,
    ratio1=1, mod1=0, modBend1=0,
    attack1=0.1, decay1=0.5, level1=1, release1=0.5,
    outputMix1=0,
    ratio2=1, mod2=0, modBend2=0,
    attack2=0.1, decay2=0.5, level2=1, release2=0.5,
    outputMix2=0,
    foldDepth1=0, foldDepth2=0,
    mod21=0
    ;
    var venv = Env.adsr(attack, decay, level, release).kr(2, gate);
    var mod1Env = Env.adsr(attack1, decay1, level1, release1).kr(gate: gate);
    var mod2Env = Env.adsr(attack2, decay2, level2, release2).kr(gate: gate);

    var modulator2 = SinOsc.ar(freq * ratio2 * (1 + modBend2), mul: mod2Env);
    var modulator1 = SinOsc.ar(
      (freq * ratio1 * (1 + modBend1)) +
      (freq * mod21 * modulator2 * mod), mul: mod1Env
    );

    var carrier = SinOsc.ar(
      (
        freq +
        (modulator1 * mod1 * mod * freq) +
        (modulator2 * mod2 * mod * freq)
      ) * (1 + bend), mul: venv);

    var mix = Mix.new([
      carrier,
      modulator1 * outputMix1,
      modulator2 * outputMix2,
    ]);

    var folded = Fold.ar(
      mix *
      (1 +
        (
          (foldDepth1 + 1) * mod *
          (foldDepth2 + 1) * mod
        )
      ) * (1 + fold),
      -1.0, 1.0);

    Out.ar(out, Pan2.ar(folded, pan));
  }).add;
)
