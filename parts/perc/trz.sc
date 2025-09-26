// spread, moddepth, octave
Npat(\trz,
  \instrument, \trz,
  \degree, 0,
  \octave, 4,
  \moddepth, 0.5,
  \ffreq, 1200,
  \spread, 1,
  \attack, 0.01,
  \decay, Pwhite(0.03, 0.08, inf),
  \gain, 10,
  \amp, 1,
  \dur, Pshuf([1,1,1,2,1,1,1,2,1,], inf) / 4,
)
