// needs work

// octave, fold, clip, release, sustain
Npat(\hat,
  \loop, 28,
  \instrument, 'buzz1',
  \wave, 0.0,
  \root, -3,
  \degree, 0,
  \octave, 3,
  \clip, 7,
  \fold, Pseq([Pn(10, 3), 50, Pn(20, 4), 10], inf),
  \release, 0.05,
  \sustain, Pwhite(0.02, 0.07, inf),
  \dur, Pseq([Pn(1, 3), 2, Pn(1, 2), 2, 1, 2], inf) / 4,
)

// bits, decay, sustain, map samplerate
Npat(\buzz,
    \loop, 24,
    \instrument, 'buzz2',
    \degree, 0,
    \octave, 4,
    \gain, 5,
    \sampduration, 0.3,
    \sampenvattack, 0.5,
    \sampenvdecay, 4,
    \sampenvdepth, 0.79,
    \bits, 4,
    \samplerate, 0.1,
    \decay, 0.01,
    \sustain, 0.1,
    \dur, Pseq([
      2, 2, 1, 2, 5, 2, 1, 2
    ], inf)/4
  )
)

Ndef(\buzz).map(\samplerate, Ndef(\srmod))

NpatControl(\srmod,
  \mod,
  \value, Pseq([Pn(0.1, 3), 0.5, Pn(0.02, 3), Pn(0.1, 3)], inf),
  \slew, 1,
  \dur, 1
)

// degree, octave, decay
Npat(\blip,
  \loop, 16,
  \instrument, \pblip,
  \degree, Pseq([Pn(0, 4), Prand([0, 2, 5], 2)], inf),
  \octave, Pwrand([7, 5], [10, 1].normalizeSum, inf),
  \attack, 0.01,
  \decay, Pwhite(0.05, 0.15, inf),
  \dur, Pshuf([2, 2, Pr(9, 4), 3, 4, Pr(4, 1), 2, 3, 3], inf) / 2,
)



Npat(\thrum,
  \loop, 16,
  \instrument, 'buzz2',
  \degree, 0,
  \octave, 3,
  \attack, 0.1,
  \decay, 0.5,
  \gain, 3,
  \samplerate, Pseq([Pn(1, 2), 0.3, Pn(1, 3), 0.2], inf),
  \dur, Pseq([Rest(1), 2, 2, Rest(3)], inf),
)



Npat(\clap,
  \loop, 12,
  \instrument, \digiclap,
  \feedback, 0.5,
  \degree, 0,
  \filterFreq, 1000,
  //\attack, 0.01,
  \decay, 0.2,
  \density, 11,
  //\freq, 1500,
  //\q, 0.9,
  \pan, Pseq([1, 1, -1, 0, 1, -1, -1, 0], inf) * 0.5,
  \gain, 11,
  \amp, 1,
  \dur, Pseq([Rest(2), 2, Rest(2), 2, 3, Pn(0.25, 4)], inf),
)
Ndef(\clap).quant = 8;

Npat(\kick,
  \loop, 16,
  \instrument, \modkick,
  \freq, 50,
  \decay, 0.5,
  \rampdecay, 0.05,
  \ramp, 2,
  \modDepth, 3.2,
  //\modNoise, 0.3,
  //\ratio, 2.7,
  //\eqPeak, 100,
  //\eqGain, 20,
  \dur, Pseq([3.5, 2.5, 1, 1.5, 3.5, 0.5], inf)
)
Ndef(\kick).quant = 8;
