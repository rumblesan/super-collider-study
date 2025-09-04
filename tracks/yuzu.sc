
NpatLoop(\hat, 28,
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

NpatLoop(\buzz, 24,
    \instrument, 'buzz2',
    \root, -3,
    \degree, 0,
    \octave, 4,
    \amp, 0.8,
    \gain, 3,
    \sampduration, 0.3,
    \sampenvattack, 0.5,
    \sampenvdecay, 4,
    \sampenvdepth, 0.79,
    \bits, Pseq([Pn(4, 4), Pn(3, 3), Pn(2, 3), Pn(3, 2)], inf),
    \samplerate, 0.8,
    \decay, Pwhite(0.01, 0.2),
    \sustain, Pseq([0.3, Pn(0.1, 3), 0.3, Pn(0.2, 4), 0.3, Pn(0.1, 2)], inf),
    \dur, Pseq([
      Pn(1, 5), 2, Pn(1, 3), 2, Pn(1, 4)
    ], inf)/4
  )
)
Ndef(\buzz).quant = 4;


NpatLoop(\blip, 16,
  \instrument, \pblip,
  \degree, 0,
  \octave, Pwrand([7, 5], [10, 1].normalizeSum, inf),
  \attack, 0.01,
  \decay, Pwhite(0.05, 0.15, inf),
  \dur, Pseq([2, 2, 3, 3, 4, Pn(0.5, 4), 2, 3, 3], inf) / 2,
)
Ndef(\blip).quant = 4;



NpatLoop(\thrum, 16,
  \instrument, 'buzz1',
  \degree, 0,
  \octave, 3,
  \attack, 0.1,
  \decay, 0.5,
  \amp, 1,
  \dur, Pseq([1, r], inf) * 4,
)
Ndef(\thrum).quant = 4;



NpatLoop(\clap, 12,
  \instrument, \digiclap,
  \feedback, 0.5,
  \degree, 0,
  \filterFreq, 1000,
  //\attack, 0.01,
  \decay, 0.2,
  \density, 11,
  //\freq, 1500,
  //\q, 0.9,
  \gain, 11,
  \amp, 1,
  \dur, Pseq([Rest(2), 2, Rest(2), 2, 3, Pn(0.25, 4)], inf),
)
Ndef(\clap).quant = 8;

NpatLoop(\kick, 18,
  \instrument, \modkick,
  \amp, 1,
  \decay, 0.5,
  \rampdecay, 0.05,
  \moddecay, 0.08,
  \pfreq, 300,
  \ramp, 10,
  \moddepth, 1.2,
  \ratio, 6,
  \freq, 50,
  \gain, 10,
  \dur, Pseq([3.5, 2.5, 1, 1.5, 3.5, 0.5], inf)
)
Ndef(\kick).quant = 8;
