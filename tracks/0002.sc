
"Setup.scd".load;

~fxBus = Bus.audio(s, 2);

~fx = {Out.ar(0, In.ar(~fxBus, 2))}

(
  SynthDef(\ceed, {arg freq = 220, fmod = 1, q = 1, gate = 1, attack = 0.1, release = 0.6;
    var osc, env, lpf;
    osc = Saw.ar(freq * 1.11, 4);
    env = Env.asr(attack, 1, release).kr(2, gate);
    lpf = RLPF.ar(Splay.ar(osc), freq * fmod, q, env);
    Out.ar(~fxBus, lpf);
  }).add;
)

(
Pdef(\sql,
  Pbind(
    \instrument, \ceed,
    \degree, Pseq([2], inf),
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
