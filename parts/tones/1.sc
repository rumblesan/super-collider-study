Ndef(\tone1,
  Pbind(
    \instrument, \fm2,
    \degree, 0,
    \root, -5,
    \octave, 3,
    \mod, 2,
    \mod1, 5,
    \ratio, 2.1,
    \attack, 0.0,
    \decay, 0.0,
    \legato, 0.8,

    \amp, 1,
    \dur, 4,
  )
)
Ndef(\tone1).clear;
Ndef(\tone1).quant = 4;
Ndef(\tone1).fadeTime = 0.1;
Ndef(\tone1).graph
