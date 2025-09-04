(

  SynthDef(\fm2, {arg out=0, gate=1, freq=220, amp=0.8,
    mod=0, bend=0,
    attack=0.1, decay=0.5, level=1, release=0.5,
    ratio1=1, mod1=0, modBend1=0,
    attack1=0.1, decay1=0.5, level1=1, release1=0.5,
    output1Mix=0,
    mod21=0
    ;
    var venv = Env.adsr(attack, decay, level, release).kr(2, gate);
    var mod1Env = Env.adsr(attack1, decay1, level1, release1).kr(gate: gate);

    var modulator1 = SinOsc.ar(
      (freq * ratio1 * (1 + modBend1)), mul: mod1Env
    );

    var carrier = SinOsc.ar(
      (
        freq +
        (modulator1 * mod1 * mod * freq)
      ) * (1 + bend));

    var snd = carrier * venv * amp;
    Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
  }).add;

  SynthDef(\fm2perc, {arg out=0, gate=1, freq=220, amp=0.8,
    mod=0, bend=0,
    envPMod=0, envModMod=0,
    attack=0.1, decay=0.5, level=1, release=0.5,
    ratio1=1, mod1=0, modBend1=0,
    attack1=0.1, decay1=0.5, level1=1, release1=0.5,
    modEnvAttack=0.1, modEnvDecay=0.5, modEnvLevel=1, modEnvRelease=0.5,
    output1Mix=0,
    mod21=0
    ;
    var venv = Env.adsr(attack, decay, level, release).kr(2, gate);
    var mod1Env = Env.adsr(attack1, decay1, level1, release1).kr(gate: gate);
    var pEnv = Env.adsr(modEnvAttack, modEnvDecay, modEnvLevel, modEnvRelease).kr(gate: gate);

    var modulator1 = SinOsc.ar(
      (freq * ratio1 * (1 + modBend1) * (1 + (pEnv * envModMod))), mul: mod1Env
    );

    var carrier = SinOsc.ar(
      freq * (1 + bend) * (1 + (pEnv * envPMod)),
      (modulator1 * mod1 * mod)
    );

    var snd = carrier * venv * amp;
    Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
  }).add;

  SynthDef(\fm3, {arg out, freq=50, gate=1, amp=0.8,
    mod=0, bend=0,
    attack=0.1, decay=0.5, level=1, release=0.5,
    ratio1=1, mod1=0, modBend1=0,
    attack1=0.1, decay1=0.5, level1=1, release1=0.5,
    output1Mix=0,
    ratio2=1, mod2=0, modBend2=0,
    attack2=0.1, decay2=0.5, level2=1, release2=0.5,
    output2Mix=0,
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
      ) * (1 + bend));

    var mix = Mix.new([
      carrier,
      modulator1 * output1Mix,
      modulator2 * output2Mix,
    ]);
    var snd = carrier * venv * amp;
    Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
  }).add;

  SynthDef(\fm4, {arg out, freq=50, gate=1, amp=0.8,
    mod=0, bend=0,
    attack=0.1, decay=0.5, level=1, release=0.5,
    ratio1=1, mod1=0, modBend1=0,
    outputMix=1,
    attack1=0.1, decay1=0.5, level1=1, release1=0.5,
    output1Mix=0,
    ratio2=1, mod2=0, modBend2=0,
    attack2=0.1, decay2=0.5, level2=1, release2=0.5,
    output2Mix=0,
    mod21=0,
    ratio3=1, mod3=0, modBend3=0,
    attack3=0.1, decay3=0.5, level3=1, release3=0.5,
    output3Mix=0,
    mod31=0
    ;
    var venv = Env.adsr(attack, decay, level, release).kr(2, gate);
    var mod3Env = Env.adsr(attack3, decay3, level3, release3).kr(gate: gate);
    var mod2Env = Env.adsr(attack2, decay2, level2, release2).kr(gate: gate);
    var mod1Env = Env.adsr(attack1, decay1, level2, release1).kr(gate: gate);

    var modulator3 = SinOsc.ar(freq * ratio3 + (freq * modBend3), mul: mod3Env);
    var modulator2 = SinOsc.ar(freq * ratio2 + (freq * modBend2), mul: mod2Env);
    var modulator1 = SinOsc.ar(
      (freq * ratio1 + (freq * modBend1)) +
      (freq * mod21 * modulator2 * mod) +
      (freq * mod31 * modulator3 * mod),
      mul: mod1Env
    );

    var carrier = SinOsc.ar(
      freq +
      (modulator1 * mod1 * mod * freq) +
      (modulator2 * mod2 * mod * freq) +
      (modulator3 * mod3 * mod * freq) +
      (freq * bend)
    );

    var snd = carrier * venv * amp;

    Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
  }, variants: (
    organ: [
      mod: 1, attack: 0.2, decay: 0.5, level: 1.0, release: 2.5,
      ratio1: 5, mod1: 1, attack1: 0.2, decay1: 0.5, level1: 0.75, release1: 2.5,
      ratio2: 7, mod2: 2, attack2: 0.2, decay2: 0.7, level2: 0.75, release1: 2.5, output2Mix: 1,
      ratio3: 5.75, mod3: 10, attack3: 0.02, decay3: 0.1, level3: 0.0, release3: 0, output3Mix: 0.2,
    ]
  )).add;

)
