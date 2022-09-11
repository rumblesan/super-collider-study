
"Setup.scd".load;

m = ProxyMixer(p, 8);

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
)

(
  ~synth = Pbind(
    \instrument, \pad,
    \note, Pseq([1, 3, 8, 6 -5, 5], inf),
    \dur, Pseq([1, 1, 2, 1, 2], inf) / 4,
    \fmod, Pseq([1, 1.3, 2, 2.1, 1.6], inf),
    \amp, 2,
  )
)
~synth.play;
~synth.stop;

(
  ~main = {
    var verbsend = (~synth * 1);
    var mainsend = (~synth * 0);
    var verb = FreeVerb.ar(verbsend, mix: 1, room: 0.8, damp: 0.2);
    var out = mainsend + verb;
    out;
  }
)
~main.play;
