// NEEDS WORK

Ndef(\blips)[0] = Pbind(
  \instrument, \fm2perc,
  \root, -5,
  \octave, 4,
  \degree, Pseq([0], inf),
  [\degree, \dur], Pmetro(
    Pseq([0, 0, 0, 0, 0, 0, 0, 0], inf),
    Pseq([3, 3, 2, 4, 2, 4, 4, 2], inf),
    "-.-*.*.-",
    inf,
    0.25),
  \attack, 0.01,
  \attack1, 0.001,
  \release, Pwrand([0.05, 0.1, 0.3], [5, 0, 0].normalizeSum, inf),
  \envPMod, 3,
  \envModMod, 5,
  \mod, 3,
  \mod1, Pseq([Pn(1, 3), Pn(1.5, 2), Pn(1, 4), 2], inf),
  \modEnvAttack, 0.01,
  \modEnvRelease, Pwrand([0.01, 0.1, 0.3], [10, 3, 1].normalizeSum, inf),
  \ratio, Pwrand([1.75, 2.5, 0.5], [15, 4, 2].normalizeSum, inf),
  \legato, Pwrand([0.1, 0.3, 0.7], [20, 7, 2].normalizeSum, inf),
  \amp, Pseq([1.1, 1, 1, 1.1, 1.1, 1, 1.1, 1], inf),
)
Ndef(\blips).clear;
