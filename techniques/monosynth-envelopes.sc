
"Setup.scd".load;
m = ProxyMixer(p, 8);
s.plotTree


(
SynthDef(\msynth, {arg out=0, freq=220, gate=1, t_penv=0, moddepth=10;
  var osc, env, penv;
  env = Env.adsr(0.01, 0.3, gate, 0.5).kr(2, gate: gate);
  penv = Decay2.kr(t_penv, 0.01, 0.05) * moddepth;
  osc = SinOsc.ar(freq * (1 + penv));
  Out.ar(out, osc * env);
}).add;
)


(
  ~msql = PmonoArtic(
    \msynth,
    \degree, Prand([0, 0, 0, 3, 5], inf),
    \scale, Scale.harmonicMinor,
    \moddepth, Prand([10, 1, 1, 0, 3], inf),
    \sustain, Prand([0.1, 0.2, 0.1, 1.1], inf),
    \penv, 1,
    \dur, Prand([0.5, 1], inf),
  )
)
~msql.play;
~msql.clear;
