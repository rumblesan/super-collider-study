
"Setup.scd".load;
m = ProxyMixer(p, 8);
s.plotTree


(
SynthDef(\msynth, {arg out=0, freq=220, gate=1, t_penv=1, moddepth=1, amp=1;
  var osc, env, penv;
  env = Env.adsr(0.01, 0.3, amp, 0.5).kr(2, gate: gate);
  penv = Decay2.kr(t_penv, 0.01, 0.05) * moddepth;
  osc = SinOsc.ar(freq * (1 + penv));
  Out.ar(out, osc * env);
}).add;
)

(
  ~out = Pbind(
    \instrument, \msynth,
    \degree, Prand([0], inf),
    \scale, Scale.harmonicMinor,
    \moddepth, 0,
    \penv, 0,
    \amp, 1,
    \dur, 2,
  )
)
~out.play;
~out.clear;

(
  ~out = Pmono(
    \msynth,
    \degree, Prand([0], inf),
    \scale, Scale.harmonicMinor,
    \moddepth, 0,
    \sustain, Pseq([0.1, 1.1], inf),
    \amp, 1,
    \dur, 2,
  )
)
~out.play;
~out.clear;

(
  ~out = PmonoArtic(
    \msynth,
    \degree, Prand([0], inf),
    \scale, Scale.harmonicMinor,
    \moddepth, 0,
    \sustain, Pseq([0.1, 1.1], inf),
    //\amp, 1,
    \dur, 1,
  )
)
~out.play;
~out.clear;
