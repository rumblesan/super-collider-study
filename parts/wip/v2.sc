
Ndef(\v2).quant = 4;
Ndef(\v2, Pbind(
  \instrument, \hd,
  \scale, Scale.minor,
  \octave, 4,
  \root, -5,

  \attack, 0.01,
  \decay, 0.2,
  \level, 1,
  \attack1, 0.001,
  \decay1, 0.02,
  \level1, 0,
  \ratio1, 0.75,
  \legato, Pseq([0.1, 0.1, 0.8, 0.1, 0.8, 0.5, 0.5], inf),
  \mod, 1,
  \mod1, 0.3,
  \mod2, 1,
  \attack2, 0.001,
  \decay2, 0.2,
  \level2, 0.5,
  \ratio2, 0.5,
  \amp, 1,
  \fold, Pwrand([0, 0.07, 0.3], [15, 3, 1].normalizeSum, inf) + 0.8,
  \pan, Pseq([
    -1, 1,
    -1, Pwhite(0.2, 1, 1),
    1, Pwhite(-0.2, -1, 1),
    1], inf),
  \dur, Pseq([3, 3, 1, 1, 3, 3, 1, 1, 0.5, 0.5, 0.5], inf),
)
)


Ndef(\extenv, Pcontrol(
  \percenv,
  \attack, 0.01,
  \decay, 0.1,
  \curve, -12,
  \value, 2,
  \offset, 0,
  \dur,Pseq([1, 2, 3, 4], inf)
)
)
Ndef(\extenv).quant = 4;

Ndef(\v2).map(\bend, Ndef(\extenv))

Ndef(\v2).clear
