(

  SynthDef(\pm2, {
    arg out=0, gate=1, freq=200, amp=0.8,
    attack=0.1, decay=0.1, level=1, release=0.5,
    pattack=0.01, pdecay=0.1, pdepth=0,
    bend=0,
    mod=0,
    ratio=1.0,
    attack1=0.1, decay1=0.01, level1=0.1, release1=0.5;
    var venv = Env.adsr(attack, decay, level, release).ar(Done.freeSelf, gate);
    var penv = Env.perc(pattack, pdecay, pdepth).ar(gate: gate, levelBias: 1);
    var menv = Env.adsr(attack1, decay1, level1, release1).ar(gate: gate);

    var modOsc = SinOsc.ar(freq * ratio, mul: menv);
    var snd = SinOsc.ar(freq * penv * (1+bend), phase: modOsc * mod, mul: venv * amp);

    Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
  }).add;

)
