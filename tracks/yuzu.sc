
Npat(\hat,
  \loop, 28,
  \instrument, 'buzz1',
  \wave, 0.0,
  \root, -3,
  \degree, 0,
  \octave, Pseq([Pn(3, 7), Pn(5, 5), Pn(3, 5), Pn(9, 2)], inf),
  \clip, 7,
  \fold, Pstep(
    [7, 12, 7, 20, 9],
    [3.5, 1, 5, 2, 4.5],
    inf
  ),

  \amp, 0.8,
  \release, 0.25,

  \sustain, Pwhite(0.02, 0.1, inf),
  \dur, Pseq([1], inf) / 4,
)
Ndef(\hat).quant = 4;

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
    \bits, Pseq([Pn(4, 4), Pn(3, 3), Pn(2, 3), Pn(3, 2)], inf),
    \samplerate, 0.1,
    \decay, Pwhite(0.01, 0.2),
    \sustain, Pseq([0.3, Pn(0.1, 3), 0.3, Pn(0.2, 4), 0.3, Pn(0.1, 2)], inf),
    \dur, Pseq([
      Pn(1, 5), 5, 7, Pn(1, 3), 3, 2, 2, 7,
    ], inf)/4
  )
)
Ndef(\buzz).quant = 4;

Ndef(\buzz).unmap(\samplerate, Ndef(\gainmod))

NpatControl(\gainmod,
  \mod,
  \value, Pseq([3, 3, 5, 3, 2, 7, Pn(10, 5)], inf),
  \slew, 1,
  \dur, 1
)

Npat(\blip,
  \loop, 16,
  \instrument, \pblip,
  \degree, Pseq([Pn(0, 4), Prand([0, 2, 5], 2)], inf),
  \octave, Pwrand([7, 5], [10, 1].normalizeSum, inf),
  \attack, 0.01,
  \decay, Pwhite(0.05, 0.15, inf),
  \dur, Pshuf([2, 2, Pr(9, 4), 3, 4, Pr(4, 1), 2, 3, 3], inf) / 2,
)
Ndef(\blip).quant = 4;



Npat(\thrum,
  \loop, 16,
  \instrument, 'buzz2',
  \degree, 0,
  \octave, 3,
  \attack, 0.1,
  \decay, 0.5,
  \amp, 1,
  \dur, Pseq([4, Rest(4)], inf),
)
Ndef(\thrum).quant = 4;



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
  \loop, 18,
  \instrument, \modkick,
  \decay, 0.3,
  \rampdecay, 0.05,
  \moddecay, 0.10,
  \ramp, 10,
  \moddepth, 18.2,
  \modNoise, 0.3,
  \ratio, 2.7,
  \eqPeak, 100,
  \eqGain, 20,
  \freq, 50,
  \gain, 9,
  \dur, Pseq([3.5, 2.5, 1, 1.5, 3.5, 0.5], inf)
)
Ndef(\kick).quant = 8;
