

(
  ~hats = Pbind(
    \instrument, \fmhat,
    \freq, 800,
    \ratio, 8.726,
    \pitchEnvDepth, 0.3,
    \pitchEnvDecay, 0.05,
    \modDepth, 3.5,
    \bend, 0,
    \modEnvAttack, 0.001,
    \attack, 0.001,
    \decay, 0.1,
    \legato, 0.005,
    \noiseLevel, 0,
    \noiseMod, 0.1,
    \hpcutoff, 1000,
    \amp, f.proxy[1],
    \dur, 1,
  )
)
~hats.play;
~hats.stop;
