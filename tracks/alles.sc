// percussion and bass is solid but lead needs work
// could probably do with a clap/snare of some sort

Npat(\bass,
  \instrument, \subbass,
  \degree, 0,
  \octave, 3,
  \attack, 0.1,
  \release, 0.1,
  \filterOffset, 8,
  \detune, 0.01,
  \gain, 5,
  \dur, Pseq([4, Rest(4)], inf),
)

NpatControl(\basspw,
  \mod,
  \value, Pseq([4, 2, 2, 3], inf) / 8,
  \slew, 1,
  \dur, 1,
)

Ndef(\bass).map(\pulsewidth, Ndef(\basspw))



Npat(\kick,
  \instrument, \bkick,
  \freq, 50,
  \attack, 0.1,
  \decay, 0.3,
  \ramp, 25,
  \rampdecay, 0.035,
  \noisedecay, Pseq([Pn(0.01, 3), 0.1, Pn(0.01, 4), 0.1], inf),
  \gain, 2,
  \dur, Pseq([1, r, r, 1, r, 1, r, r, r, 1, r, r, r, 1, 1, r], inf) / 4,
)



// degree, bits, decay, duration, legato
Npat(\perc,
  \instrument, \buzz2,
  \degree, Pseq([
    Pn(-3, 16),
    //Pn(-1, 12),
    //Pn(0, 18),
    //Pn(-1, 10),
  ], inf),
  \attack, 0.01,
  \legato, Pwhite(0.01, 0.04, inf),
  \decay, 0.001,
  \bits, 6,
  \gain, 21,
  \dur, Pseq([1,1,
    Prand([1, 2]),
    2,1,1,2,2,1,1,1], inf) / 4
)




// Needs work
Ndef(\lead, PmonoArtic(
  \tb,
  \amp, 1,
  \octave, 4,
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
) <> Pn(Pfindur(32, Pacid(
    \degree, Pseq([6, 5, 3, 0, 7, 3, -3, 0], inf),
    \pulses, Pseq([2, 1, 4, 2, 2, 1, 1, 2], inf),
    \types, Pseq(".. -  ..", inf),
    \repeats, inf,
    \dur, 1/2)), inf)
)
Ndef(\lead).quant = 4;
Ndef(\lead).clear;
