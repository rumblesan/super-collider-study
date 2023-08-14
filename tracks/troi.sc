

Ndef(\bass,
  Pbind(
    \instrument, \buzz1,
    \degree, -5,
    \octave, Pwrand([3, 2, 4], [10, 0, 1].normalizeSum, inf),
    \attack, 0.1,
    \decay, 0.5,
    \duration, 7,
    \foldgain, 1,
    \amp, 1,
    \dur, 16,
  )
)
Ndef(\bass).clear;
Ndef(\bass).quant = 8;

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


Ndef(\kick, Pbind(
  \instrument, \bkick,
  \freq, 50,
  \attack, 0.0,
  \decay, 1,
  \ramp, 16,
  \rampdecay, 0.01,
  \noiseattack, 0.01,
  \noisedecay, Pseq([Pn(0.01, 4), 0.5, 0.01, 0.5, Pn(0.02, 3)], inf),
  //\noise, 0.3,
  \gain, 5,
  \amp, 1,
  \dur, Pseq([1, 1.5, 3.5, 1, 5], inf),
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


Ndef(\perc,
  Pbind(
    \instrument, \pblip,
    \degree, -5,
    \octave, 4,
    //\attack, 0.01,
    \attack, Pwrand([0.01, 0.2, 0.3], [12, 2, 1].normalizeSum, inf),
    \width, Pseq([0.09, 0.01, 0.2], inf),
    \decay, Pwhite(0.05, 0.1, inf) / 2,
    \amp, 1,
    \dur, Pseq([1, 2, 1, 1, 1, 3, 2, 1, 4, 2, 2, 1], inf)/4,
  )
)
Ndef(\perc).clear;

Ndef(\lead, PmonoArtic(
  \tb,
  \amp, 1,
  \octave, 5,
  \degree, Pseq([1, 3, 1, 3, 1], inf),
  \root, -5,
  \scale, Scale.minor,
  \legato, Pseq([0.8, 0.9, 0.8, 1.1, 0.8], inf),
  \wave, 0.9,
  \gain, 1.3,
  \cutoff, 200,
  \hipass, 1,
  \envmod, 3.3,
  \resonance, 0.7,
  \attack, 0.01,
  \decay, 0.7,
  \level, 0.6,
  \release, 0.7,
  \fattack, 0.02,
  \fdecay, 0.3,
  \flevel, 0.6,
  \frelease, 0.5,
  \freqlag, 0.3,
  \dur, 1,
)
)
Ndef(\acid).quant = 4;
Ndef(\acid).clear;
