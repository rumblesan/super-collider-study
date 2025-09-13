(

  SynthDef(\fnoise, {
    arg out=0, gate=1,
    lpfreq=10000, hpfreq=150, q=1,
    gain=0.8,
    attack=0.01, decay=0.2, amp=0.8;
    var n = WhiteNoise.ar;
    var f = RHPF.ar(RLPF.ar(n, lpfreq, q), hpfreq, q);
    var g = (f * gain).clip2;
    var env = Env.asr(attack, amp, decay).ar(Done.freeSelf, gate: gate);
    var snd = g * env;
    Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
  }).add;

)
