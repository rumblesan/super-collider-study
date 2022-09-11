(
  SynthDef(\modkick, {arg out, freq=50, decay=0.2, ramp=0.3, rdecay=0.2, mod=0.5, modfreq=200, mdecay=0.2, amp=0.1;
    var attack = 0.01;
    var dist = 8;


    var menv = Env.perc(0.0, mdecay, mod * freq).kr(0);

    var mosc = SinOsc.ar(modfreq, mul: menv);

    var venv = Env.perc(attack, decay, amp).kr(2);
    var osc = SinOsc.ar(freq + mosc , mul: venv);

    Out.ar(out, osc.clip2(1/(dist + 1)) * (dist + 1));
  }).add;

  SynthDef(\bkick, {arg out, freq=50, pfreq=50, decay=0.2, ramp=0.3, rdecay=0.2, amp=0.1;
    var attack = 0.01;

    var dist = 4;

    var venv = Env.perc(attack, decay, amp).kr(2);

    var penv = Env.perc(0.0, rdecay, pfreq * ramp).kr(0);

    var osc = SinOsc.ar(freq + (penv * freq), mul: venv);

    Out.ar(out, osc);
  }).add;

  SynthDef(\rim, {arg out, freq=50, hipass=200, decay=0.2, click=0.3, amp=0.8;
    var cenv = Env.perc(0.0, 1.8, click).kr();
    var venv = Env.perc(0.0, decay, amp).kr(2);
    var osc = SinOscFB.ar(freq, feedback: cenv, mul: venv);

    Out.ar(out, HPF.ar(osc, hipass));
  }).add;

  SynthDef(\clikr, {arg out, decay=1, freq=200, ffreq=200, amp=0.8;
    var venv = Env.perc(0.0, decay, 1).kr();
    var osc = Blip.ar(freq, 200, venv * amp);
    var filt = RLPF.ar(osc, ffreq, 1);

    Out.ar(out, filt);
  }).add;
)
