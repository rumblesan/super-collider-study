
(
SynthDef(\pad, {arg out, freq = 60, amp = 0.1, gate = 1, fmod = 1;
  var source, env, lpf, fosc, fnoise;
  fnoise = LFNoise0.kr(2).range(10, 50);
  source = Saw.ar([freq, freq/2, freq * 2, freq * 1.01, freq * 1.5], [1, 0.4, 0.2, 0.9, 0.3] );
  env = Env.asr(1, amp, 4).kr(2, gate);
  fosc = SinOsc.kr(0.3).range(1, 1.2);
  lpf = LPF.ar(source, (freq * fosc * fmod) + fnoise, env);
  Out.ar(out, Splay.ar(lpf));
}).add;
);

//(
  //~pad = Pbind(
    //\instrument, \pad,
    //\note, Pseq([1, 3, 8, 6 -5, 5], inf),
    //\dur, Pseq([1, 1, 2, 1, 2], inf) / 4,
    //\fmod, Pseq([1, 1.3, 2, 2.1, 1.6], inf),
    //\amp, 2,
  //)
//)
//~pad.play;
//~pad.clear;

(
  SynthDef(\bassTone, {arg out, freq = 60, amp = 0.7, gate = 1;
    var pwmod, osc, env, lpf;
    pwmod = SinOsc.kr(3).range(0.08, 0.3);
    osc = Pulse.ar([freq, freq * 1], [0.5 + pwmod, 0.4]);
    env = Env.asr(1, amp, 4).kr(2, gate);
    lpf = LPF.ar(osc, freq * 8, env);
    Out.ar(out, Splay.ar(lpf));
  }).add;
);


//(
  //~bassseq = Pbind(
    //\instrument, \bassTone,
    //\note, Pseq([1], inf),
    //\ctranspose, -24,
    //\dur, Pseq([0.5, Rest(0.5)], inf),
    //\amp, 1,
  //)
//)
//~bassseq.play;
//~bassseq.clear;
