
Ndef(\dclap, Pbind(
  \instrument, \digiclap,
  \feedback, Pwrand([0.5, 2, 7], [10, 3, 1].normalizeSum, inf),
  \degree, Pseq([0, 0, 2, 0, 0, 0, 2], inf),
  \filterFreq, 1000,
  //\attack, 0.01,
  \decay, 0.008,
  \density, 11,
  //\freq, 1500,
  //\q, 0.9,
  \gain, 11,
  \amp, 1,
  \dur, 2,
)
)
Ndef(\dclap).clear;
