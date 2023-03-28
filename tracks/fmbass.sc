
"Setup.scd".load;

Ndef(\lbass, Pbind(
  \instrument, \pm2,
  \root, -5,
  \octave, 3,
  \degree, 0,

  \attack, 0.1,
  \decay, 0.3,
  \level, 0.0,
  \release, 0.5,

  \pattack, 0.01,
  \pdecay, 0.03,
  \pdepth, Prand([3, 4, 0], inf),

  \mod, 3.1,
  \ratio, Pseq([Pn(1, 5), 1.2, Pn(1, 7), 1.233], inf) * 2,
  \attack1, 0.3,
  \decay1, 0.3,
  \level1, 0.0,
  \release1, 0.5,

  \amp, Pseq([1, 0.8, 0.8], inf),
  \legato, 0.8,
  \dur, 1/2,
))
Ndef(\lbass).quant = 8
Ndef(\lbass).play
Ndef(\lbass).clear
