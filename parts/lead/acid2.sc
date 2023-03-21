
Ndef(\higher, PmonoArtic(
  \tb,
  \amp, 1,
  \octave, Pwrand([4, 5], [2, 3].normalizeSum, inf),
  \root, -5,
  \scale, Scale.minor,
  \legato, Pseq([0.8, 0.8, 1, 0.8, 0.8, 1, 0.8], inf),
  \wave, 0.1,
  \gain, 0.8,
  \cutoff, 200,
  \envmod, 0.5,
  \resonance, 0.3,
  \attack, 0.1,
  \decay, 0.1,
  \level, 0.6,
  \release, 0.5,
  \fattack, 0.2,
  \fdecay, 0.3,
  \flevel, 0.6,
  \frelease, 0.5,
  \freqlag, Pif( Pkey(\legato) > 0.8, 1.0, 0.1),
) <> Pn(Pfindur(16, Pacid(
    Pseq([0, 0, 7, 0, 7, 0, 0, 0], inf),
    Pseq([2, 2, 2, 2, 4, 2, 4, 2], inf),
    ". -.- * ",
    inf,
    1/4)), inf)
)
Ndef(\higher).quant = 4;
