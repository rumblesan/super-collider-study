

Ndef(\trmp, Pbind(
  \instrument, \fm2,
  \root, -5,
  \scale, Scale.minor,
  \octave, Pwrand([5, 4, 3], [20, 13, 5].normalizeSum, inf),
  [\degree, \dur], Pmetro(
    Pseq([7, 0, 4, 0, 5, 7, 0, 0], inf),
    Pseq([2, 4, 2, 4, 4, 2, 4, 4, 2], inf),
    ". -.* .*.",
    inf,
    0.25),
  \mod, 1,
  \mod1, Pwrand([1, 2, 5], [20, 5, 2].normalizeSum, inf) * 1.5,
  \attack, 0.01,
  \decay, 0.5,
  \amp, 1,
)
)
Ndef(\trmp).clear;

Ndef(\leaddly)[0] = \fourtapdelay

Ndef(\leaddly)[1] = \pset -> Pbind(
  \drywet, 0.5,
  \delay, Pwrand([0.5, 0.02], [20, 1].normalizeSum, inf),
  \feedback, 0.3,
  \dur, 0.25,
)
Ndef(\leaddly).clear;


Ndef(\leaddly) <<> Ndef(\trmp)
