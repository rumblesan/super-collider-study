
Npat(\dclap,
  \loop, 16,
  \instrument, \digiclap,
  \feedback, 0.5,
  \degree, 0,
  \filterFreq, 1000,
  //\attack, 0.01,
  \decay, 0.008,
  \density, 11,
  //\freq, 1500,
  //\q, 0.9,
  \gain, 11,
  \amp, 1,
  \dur, Pseq([Rest(2), 4, Rest(4), 2], inf)
)
Ndef(\dclap).quant = 8;
Ndef(\dclap).clear;
