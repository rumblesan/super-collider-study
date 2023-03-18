
(
  SynthDef(\fm3filter, {arg out, freq=50, gate=1, amp=0.8,
    mod=0, bend=0,
    attack=0.1, decay=0.5, level=1, release=0.5,
    ratio1=1, mod1=0, modBend1=0,
    attack1=0.1, decay1=0.5, level1=1, release1=0.5,
    output1Mix=0,
    ratio2=1, mod2=0, modBend2=0,
    attack2=0.1, decay2=0.5, level2=1, release2=0.5,
    output2Mix=0,
    mod21=0,
    cutoff=1000, resonance=0.5, gain=1.0
    ;
    var venv, modulator2, mod2Env, modulator1, mod1Env, carrier, mix, filter;
    venv = Env.adsr(attack, decay, level, release).kr(2, gate);
    mod2Env = Env.adsr(attack2, decay2, level2, release2).kr(gate: gate);
    mod1Env = Env.adsr(attack1, decay1, level1, release1).kr(gate: gate);

    modulator2 = SinOsc.ar(freq * ratio2 * (1 + modBend2), mul: mod2Env);
    modulator1 = SinOsc.ar(
      (freq * ratio1 * (1 + modBend1)) +
      (freq * mod21 * modulator2 * mod), mul: mod1Env
    );

    carrier = SinOsc.ar(
      (
        freq +
        (modulator1 * mod1 * mod * freq) +
        (modulator2 * mod2 * mod * freq)
      ) * (1 + bend));

    mix = Mix.new([
      carrier,
      modulator1 * output1Mix,
      modulator2 * output2Mix,
    ]);
    filter = BMoog.ar(mix * gain, cutoff, resonance) * venv * amp;
    Out.ar(out, filter!2);
  }).add;
)
