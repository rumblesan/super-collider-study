
"Setup.scd".load;

(
  SynthDef(\ceed, {arg out, freq = 220, fmod = 1, q = 1, gate = 1, attack = 0.1, release = 0.6;
    var osc, env, lpf;
    osc = Saw.ar(freq * 1.11, 4);
    env = Env.asr(attack, 1, release).kr(2, gate);
    lpf = RLPF.ar(Splay.ar(osc), freq * fmod, q, env);
    Out.ar(out, lpf);
  }).add;
)

(
Pdef(\sql,
  Pbind(
    \instrument, \ceed,
    \degree, Prand([2, 1, 1, 2, 2, 5, 5, 5, 1], inf),
    \scale, Scale.harmonicMinor,
    \dur, 0.5,
    \q, 1,
    \fmod, 1,
    \attack, Pseq([0.1], inf),
    \release, Pseq([0.1], inf),
    \ctranspose, -12,
  )
)
)

Pdef(\sql).play;
Pdef(\sql).stop;
