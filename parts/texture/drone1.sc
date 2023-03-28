
"Setup.scd".load;

Ndef(\cort, Pbind(
  \instrument, \sawpad,
  \octave, Pwrand([3, 4], [10, 3].normalizeSum, inf),
  \scale, Scale.minor,
  \root, -5,
  \detune, 0.009,
  \degree, Pseq([
    [0, 3, 5, 7]
  ], inf),
  \legato, 0.7,
  \res, Pseq([2], inf),
  \foffset, 0,
  \attack, 3,
  \release, 4,
  \amp, 1,
  \dur, 16,
))
Ndef(\cort).quant = 16;
Ndef(\cort).clear;

Ndef(\higher, Pbind(
  \instrument, \sawpad,
  \octave, Pwrand([4, 5, 6], [10, 4, 2].normalizeSum, inf),
  \scale, Scale.minor,
  \root, -5,
  \detune, 0.0003,
  \degree, Pseq([
    0, Prand([5, 3]), [0, 7],
    2, 6, 8
  ], inf),
  \legato, 0.7,
  \res, Pseq([2, 4], inf),
  \foffset, 3,
  \fattack, 5,
  \attack, 3,
  \release, 4,
  \amp, 1,
  \dur, Pwrand([10, 4], [11, 2].normalizeSum, inf),
))
Ndef(\higher).quant = 10;
Ndef(\higher).clear;

Ndef(\higher).map(\pulsewidth, Ndef(\higherWidthMod))

Ndef(\higherWidthMod, Pcontrol(
  \mod,
  \value, Pseq([0.1, 0.7, 0.3, 0.8], inf),
  \slew, 1,
  \dur, Pseq([4, 1, 1, 6, 7, 1, 1, 4], inf)
))
Ndef(\higherWidthMod).quant = 4;
Ndef(\higherWidthMod).clear;
