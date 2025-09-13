
(
SynthDef(\pad, {arg out, freq = 60, amp = 0.1, gate = 1, fmod = 1;
  var source, env, lpf, fosc, fnoise;
  fnoise = LFNoise0.ar(2).range(10, 50);
  source = Saw.ar([freq, freq/2, freq * 2, freq * 1.01, freq * 1.5], [1, 0.4, 0.2, 0.9, 0.3] );
  env = Env.asr(1, amp, 4).ar(Done.freeSelf, gate);
  fosc = SinOsc.ar(0.3).range(1, 1.2);
  lpf = LPF.ar(source, (freq * fosc * fmod) + fnoise, env);
  Out.ar(out, Splay.ar(lpf));
}).add;
);

(
  SynthDef(\bassTone, {arg out, freq = 60, amp = 0.7, gate = 1;
    var pwmod, osc, env, lpf;
    pwmod = SinOsc.ar(3).range(0.08, 0.3);
    osc = Pulse.ar([freq, freq * 1], [0.5 + pwmod, 0.4]);
    env = Env.asr(1, amp, 4).ar(Done.freeSelf, gate);
    lpf = LPF.ar(osc, freq * 8, env);
    Out.ar(out, Splay.ar(lpf));
  }).add;
);
