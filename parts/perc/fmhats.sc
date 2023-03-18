

(
  ~hats = Pbind(
    \instrument, \fmhat,
    \freq, 800,
    \ratio, 8.726,
    \pitchEnvDepth, 0.3,
    \pitchEnvDecay, 0.05,
    \modDepth, Pwhite(5.5, 9, inf),
    \bend, 0,
    \modEnvAttack, 0.01,
    \attack, 0.001,
    \decay, Pwhite(0.1, 0.4, inf),
    \legato, Pwrand([0.05, 0.3], [10, 3].normalizeSum, inf),
    \noiseLevel, 0.0,
    \noiseMod, 3.5,
    \hpcutoff, 8000,
    \amp, f.proxy[2],
    \dur, Pseq([1, Rest(1), Pn(1, 1), 1, 1, Rest(3), 1, 1, Rest(2)], inf) / 2,
  )
)
~hats.quant = 4;
~hats.play;
~hats.stop;
~hats.clear
