NpatLoop(\bass, 16,
  \instrument, \subbass,
  \degree, 0,
  \octave, 3,
  \attack, 0.0,
  \decay, 0.0,
  \legato, 0.5,
  \pulsewidth, 0.45,
  \detune, Pseq([Pn(0.01, 3), 0.03, Pn(0.03, 5), 0.02], inf),
  \gain, 16.3,
  \dur, Pseq([1.5,1,1.5, 3.5, 2.5, 1, 1, 2], inf) / 2,
)
Ndef(\bass).quant = 8;
Ndef(\bass).clear;
