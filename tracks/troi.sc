

NpatLoop(\bass, 16,
  \instrument, \buzz1,
  \degree, -5,
  \octave, 3,
  \attack, 0.1,
  \decay, 0.5,
  \duration, 8,
  \foldgain, 1,
  \amp, 1,
  \dur, 16,
)
Ndef(\bass).quant = 16;

Ndef(\bass).map(\clipgain, Ndef(\clippinglow))
Ndef(\bass).unmap(\clipgain)
Ndef(\bass).map(\foldgain, Ndef(\foldmod))
Ndef(\bass).unmap(\foldgain)

Ndef(\clippinglow,
  Pcontrol(
    \mod,
    \value, Pseq([1.1, 1.5, 1.1, Pwhite(1, 3, 3), 1.1, 2], inf) * 3,
    \slew, 0,
    \dur, Pseq([4, 2, 4, 3, 1], inf) / 2,
  )
)
Ndef(\clippinglow).quant = 4;


NpatLoop(\kick, 16,
  \instrument, \bkick,
  \freq, 50,
  \attack, 0.0,
  \decay, 0.8,
  \ramp, 16,
  \rampdecay, 0.01,
  \noiseattack, 0.01,
  \noisedecay, 0.01,
  \noise, 0,
  \gain, 5,
  \amp, 1,
  \dur, Pseq([3, 3, 2, 2, 2, 3, 3], inf),
)
)

Ndef(\kick).quant = 4;

Ndef(\foldmod,
  Pcontrol(
    \mod,
    \value, Pseq([1, 2, 5], inf),
    \slew, 0,
    \dur, Pseq([4, 2, 7], inf) / 2,
  )
)
Ndef(\foldmod).quant = 4;
Ndef(\foldmod).clear

Ndef(\foldmod,
  Pcontrol(
    \envfollower,
    \gain, 1,
    \value, 1,
    \offset, 2,
    \attack, 0.01,
    \decay, 0.07,
  )
)
Ndef(\foldmod) <<> Ndef(\kick);

Ndef(\foldmod).scope
Ndef(\foldmod).clear


NpatLoop(\perc, 16,
  \instrument, \pblip,
  \octave, 4,
  \attack, 0.01,
  //\attack, Pwrand([0.01, 0.2, 0.3], [12, 2, 1].normalizeSum, inf),
  \width, Pseq([0.09, 0.01, 0.2], inf),
  //\width, 0.1,
  \decay, Pwhite(0.15, 0.2, inf),
  \amp, 1,
  \dur, Pseq([1, 2, 1, 1, 1, 3, 2, 1, 4, 2, 2, 1], inf)/4,
)

Ndef(\lead, PmonoArtic(
  \tb,
  \amp, 1,
  \octave, 5,
  \degree, Pseq([1, 3, 1, 3, 1], inf),
  \degree, Pseq([0, 3, 0, 3, 0], inf),
  \root, -5,
  \scale, Scale.minor,
  \legato, Pseq([0.8, 0.9, 0.8, 1.1, 0.8], inf),
  \wave, 0.1,
  \gain, 1.3,
  \cutoff, 200,
  \hipass, 100,
  \envmod, 3.3,
  \resonance, 0.8,
  \attack, 0.01,
  \decay, 0.7,
  \level, 0.6,
  \release, 0.7,
  \fattack, 0.02,
  \fdecay, 0.3,
  \flevel, 0.6,
  \frelease, 0.5,
  \freqlag, 0.3,
  \dur, Pwrand([0.5, Rest(0.5), 1, Rest(1)], [5, 4, 3, 1].normalizeSum, inf),
)
)
Ndef(\lead).quant = 4;
Ndef(\lead).clear;
