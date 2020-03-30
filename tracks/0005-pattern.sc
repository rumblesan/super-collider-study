
"Setup.scd".load;

(
SynthDef(\bz, {arg out=0, freq=220, gate=1;
  var osc, env, freqenv;
  env = Env.adsr(0.01, 0.1, 0.5, 0.5).kr(2, gate);
  freqenv = Env.pairs([[0, 0], [0.001, 1], [0.3, 0.3], [4, 0]], \lin).kr(gate: gate);
  osc = Pulse.ar([freq, freq * 1.001, freq * 1.5] * env, [0.5, 0.7, 0.4], freqenv);
  Out.ar(out, Mix.ar(osc).dup);
}).add;
)

p.clock.tempo = 180 / 60;

(
  ~bz = Pbind(
    \instrument, \bz,
    \octave, 3,
    #[\degree, \dur], Pixi("0  0  0 ", inf),
  )
)
~bz.play;
