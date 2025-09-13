// WIP

Ndef(\bass, PmonoArtic(
  \face,
  \scale, Scale.minor,
  \root, -5,
  \attack, 0.2,
  \decay, 0.5,
  \octave, 3,
  \detune, 0.00,
  \lfoMod, Pseq([0, 0.3, 0.1, 0, 0.9, 0.99, 0.1, 0.1], inf) * 3,
  \lfoRate, Pseq([1, 3, 1, 1, 3, 1, 1], inf) * 2,
  \cutoff, 500,
  \q, 0.5,
  \degree, Pseq([0], inf),
  \legato, Pseq([1, 1, 1, 0.5, 1, 0.5], inf),
  \amp, 1,
  \widthMod, 0.0,
  \widthModRate, 0.3,
  \fold, 0.8,
  \gain, 1,
  \dur, 2,
)
)


Ndef(\bass).quant = 16;
Ndef(\bass, PmonoArtic(
  \face,
  \scale, Scale.minor,
  \root, -5,
  \attack, 0.01,
  \decay, 0.01,
  \octave, 3,
  \detune, 0.00,
  \lfoMod, 0.1,
  \lfoRate, 0.3,
  \cutoff, 100,
  \q, 0.2,
  \degree, Pseq([0], inf),
  \legato, 0.5,
  \amp, 1,
  \widthMod, 0.0,
  \widthModRate, 0.3,
  \fold, 1,
  \gain, 2,
  \dur, 16,
)
)
Ndef(\bass).unmap(\lfoRate)
Ndef(\bass).map(\lfoRate, Ndef(\lfomod))

NpatControl(\lfomod,
  \mod,
  \value, Pseq([0, 1, 0.5, 1, 0, 0, 1, 1], inf) * 4,
  \slew, 0,
  \dur, 2
)
Ndef(\lfomod).quant = 4;

Ndef(\bass).clear;
