
Npat(\cort,
  \instrument, \sawpad,
  \octave, Pwrand([3, 4], [10, 3].normalizeSum, inf),
  \octave, 5,
  \detune, 0.009,
  \degree, Pseq([
    [0, 3, 5, 7]
  ], inf),
  \legato, 0.7,
  \res, Pseq([2], inf),
  \foffset, 0,
  \attack, 3,
  \release, 4,
  \dur, 16,
  \pan, Pseq([1, -1, 1, -1], inf) * 0.3
)
Ndef(\cort).quant = 16;

Npat(\higher,
  \instrument, \sawpad,
  \octave, Pwrand([4, 5, 6], [10, 4, 2].normalizeSum, inf),
  \scale, Scale.minor,
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
  \dur, Pwrand([10, 4], [11, 2].normalizeSum, inf),
  \pan, Pseq([1, -1, 1, -1, -1, 1, -1, 1, 1], inf) * 0.8
)
Ndef(\higher).quant = 10;

// map higher pulsewidth
