
Ndef(\higher, PmonoArtic(
  \tb,
  \amp, 1,
  \octave, Pwrand([4, 5], [2, 3].normalizeSum, inf),
  \root, -5,
  \scale, Scale.minor,
  \legato, Pseq([0.8, 0.8, 1, 0.8, 0.8, 1, 0.8], inf),
  \wave, 0.9,
  \gain, 0.5,
  \cutoff, 300,
  \envmod, 1.5,
  \resonance, 0.7,
  \attack, 0.01,
  \decay, 0.1,
  \level, 0.6,
  \release, 0.5,
  \fattack, 0.02,
  \fdecay, 0.3,
  \flevel, 0.6,
  \frelease, 0.5,
  \freqlag, Pif( Pkey(\legato) > 0.8, 1.0, 0.1),
) <> Pn(Pfindur(16, Pacid(
    \degree, Pseq([0, 0, 7, 0, 7, 0, 0, 0], inf),
    \pulses, Pseq([2, 2, 2, 2, 4, 2, 4, 2], inf),
    \types, Pseq(". -.- * ", inf),
    \repeats, inf,
    \dur, 1/2)), inf)
)
Ndef(\higher).quant = 4;
Ndef(\higher).clear
