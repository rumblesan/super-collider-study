
"Setup.scd".load;

~trate = { VarSaw.ar(10, 0, 0.5).range(10, 11) }

~env = { EnvGen.ar(Env([0.0, 0.5, 0.0, 1.0, 0.9, 0.0], [0.05, 0.1, 0.01, 1.0, 1.5], -4), Dust.ar(1)) }

~pos = { VarSaw.ar(0.3, 0, 0.2).range(4, 5) }

~rate = { SinOsc.ar(5).range(1, 1.1) }

~cloud = { TGrains.ar(2, Impulse.ar(~trate), d[\vox][0], ~rate, ~pos + ~env, 0.2, 0, 1.0, 0.1, 1, 4) };

~cloud.play;
~cloud.stop;
