
~faders.scale(9, 0.5, 4)
~faders.scale(10, 100, 800)
~faders.scale(11, 0.01, 0.95)

Ndef(\acid, PmonoArtic(
  \tb,
  \amp, 1,
  \octave, Pwrand([3, 4], [1, 5].normalizeSum, inf),
  \root, -5,
  \scale, Scale.minor,
  \legato, Pwrand([0.8, 1], [5, 2].normalizeSum, inf),
  \wave, 0.1,
  \gain, 1.8,
  \cutoff, ~faders.proxyAt(10),
  \hipass, 100,
  \envmod, ~faders.proxyAt(9),
  \resonance, ~faders.proxyAt(11),
  \attack, 0.01,
  \decay, 0.1,
  \level, 0.8,
  \release, 0.5,
  \fattack, 0.02,
  \fdecay, 0.3,
  \flevel, 0.6,
  \frelease, 0.5,
  \freqlag, Pif( Pkey(\legato) > 0.8, 1.0, 0.1),
) <> Pn(Pfindur(16, Pacid(
    \degree, Pseq([0, 3, 7, 3, 7, 0, 0, 0], inf),
    \pulses, Pseq([2, 1, 4, 2, 4, 1, 1, 2], inf),
    \types, Pseq(".-*-.* .", inf),
    \repeats, inf,
    \dur, 1/2)), inf)
)
Ndef(\acid).quant = 4;
Ndef(\acid).clear;

Ndef(\acid).map(\wave, Ndef(\wavemod))
Ndef(\wavemod, Pcontrol(
  \mod,
  \value, Pseq([0.1, 0.5, 0.1, 0.7, 0.8, 0.2, 0.1, 0.9], inf),
  \slew, 1,
  \dur, 0.5,
)
)
Ndef(\wavemod).quant = 4;
