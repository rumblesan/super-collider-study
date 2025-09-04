

NpatLoop(\hats, 16,
  \instrument, \fmhat,
  \freq, Pwrand([800, 1200], [10, 1].normalizeSum, inf),
  \ratio, 8.726,
  \pitchEnvDepth, 0.3,
  \pitchEnvDecay, 0.05,
  \modDepth, Pwhite(5.5, 9, inf),
  \bend, 0,
  \modEnvAttack, 0.01,
  \attack, 0.001,
  \decay, Pseq([Pn(0.1, 3), 0.4, Pn(0.1, 2), 0.4], inf),
  \legato, Pwrand([0.05, 0.3], [10, 3].normalizeSum, inf),
  \noiseLevel, Pseq([Pn(0, 2), 0.5, 0, 0.5, 0.9], inf),
  \noiseMod, 8.5,
  \hpcutoff, 8000,
  \dur, Pseq([
    0.5, 0.5, Rest(1),
    Pn(0.5, 2), 1, 1,
    Prand([
      Pn(1/8, 4),
      Pseq([Pn(0.5, 3), 1, 0.5], 1)
    ], 2),
    1, 1, Rest(2)
  ], inf) / 2,
)
Ndef(\hats).quant = 4;
