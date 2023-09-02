Ndef(\bass,
    Pbind(
        \instrument, \subbass,
        \degree, Pseq([-3, -2, 0, 1], inf),
        //\degree, Pseq([-3], inf),
        \octave, 3,
        \attack, 1,
        \release, 4,
        \filterOffset, 8,
        \detune, 0.03,
        \gain, 5,
        \amp, 1,
        \dur, 16,
    )
)
Ndef(\bass).clear;
Ndef(\bass).quant = 16;

Ndef(\basspw,
    Pcontrol(
        \mod,
        \value, Pseq([4, 2, 2, 3], inf) / 8,
        \slew, 1,
        \dur, Pseq([4, 2, 2, 1, 1, 4], inf)
    )
)
Ndef(\basspw).quant = 4;

Ndef(\bass).map(\pulsewidth, Ndef(\basspw))



Ndef(\kick, Pbind(
  \instrument, \bkick,
  \freq, 50,
  \attack, 0.1,
  \decay, Pseq([0.5, 0.5, 0.01, 0.01, 0.5, 0.9, 0.01, 0.01], inf),
  \decay, 0.3,
  \ramp, 25,
  \rampattack, 0.0,
  \rampdecay, 0.035,
  \noisedecay, 0.01,
  \gain, 2,

  \amp, 1,
  \dur, Pseq([3, 3, 2,
    0.5, 2.5,
    Prand([3, Pn(1, 3)]),
    2], inf),
  //\dur, 2,
)
)
Ndef(\kick).quant = 4;
Ndef(\kick).clear;



Ndef(\perc,
  Pbind(
    \instrument, \buzz2,
    \degree, Pseq([
      Pn(-3, 16),
      //Pn(-1, 12),
      //Pn(0, 18),
      //Pn(-1, 10),
    ], inf),
    \attack, 0.01,
    \decay, 0.001,
    \bits, Pwrand([6, 3, 2], [10, 7, 3].normalizeSum, inf),
    \gain, 21,
    \duration, Pwrand([0.01, 0.1], [15, 2].normalizeSum, inf),
    \amp, 1,
    \dur, Pseq([1,1,
      Prand([1, 2]),
      2,1,1,2,2,1,1,1], inf) / 4
  )
)
Ndef(\perc).clear;




Ndef(\lead, PmonoArtic(
  \tb,
  \amp, 1,
  \octave, Pwrand([4, 5], [1, 5].normalizeSum, inf),
  \root, -5,
  \scale, Scale.minor,
  \legato, Pwrand([0.8, 1], [5, 2].normalizeSum, inf),
  \wave, 0.1,
  \gain, 0.5,
  \cutoff, 700,
  \hipass, 1,
  \envmod, 2.5,
  \resonance, 0.5,
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
    \degree, Pseq([6, 5, 3, 0, 7, 3, -3, 0], inf),
    \pulses, Pseq([2, 1, 4, 2, 2, 1, 1, 2], inf),
    \types, Pseq(".. -  ..", inf),
    \repeats, inf,
    \dur, 1/2)), inf)
)
Ndef(\lead).quant = 4;
Ndef(\lead).clear;
