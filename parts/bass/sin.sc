Npat(\bass,
  \loop, 16,
  \instrument, \subbass,
  \degree, 0,
  \octave, 3,
  \attack, 0.0,
  \decay, 0.0,
  \legato, 0.5,
  \pulsewidth, 0.45,
  \detune, 0.02,
  \gain, 3.0,
  \noise, 0.3,
  \dur, Pseq([4, Rest(4), 4, Rest(4)], inf),
)
Ndef(\bass).quant = 8;
Ndef(\bass).clear;
