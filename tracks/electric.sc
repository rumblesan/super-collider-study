
Npat(\bass,
  \instrument, \sinbass,
  \degree, 0,
  \octave, 3,
  \attack, 0.0,
  \decay, 0.05,
  \legato, 0.8,
  \dur, Pseq([
    4, 1, 1, 3, 1,
  ], inf),
)
Ndef(\bass).quant = 4;

Npat(\blip,
  \loop, 16,
  \instrument, \blip,
  \degree, 0,
  \decay, 0.5,
  \q, Pwhite(0.1, 0.5, inf),
  \freq, 1500,
  \impulsefreq, Pseq([Pn(15, 7), 50, Pn(10, 2), Pn(0, 5), 50], inf) * 2,
  \gain, 50,
  \pan, Pseq([-1, 1, 0, 0, -1, 0, -1, 1, 1, -1, 0, 1], inf) * 0.3,
  \amp, Pseq([1, 1, 0.8, 1, 0.8], inf),
  \dur, (Pseq([1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1], inf)) + 0.25,
)
Ndef(\blip).quant = 8;
Ndef(\blip).clear;


Npat(\ping,
  \loop, 16,
  \instrument, \ping,
  \degree, Prand([
    Pn(0, 7),
    Pseq([4, 0, 0, 3], 1),
    Pn(0, 7),
    Pn(6, 3),
  ], inf),
  \octave, Pseq([Pn(5, 5), Pn(6, 2), 5, 4, 5, 5], inf),
  \root, -5,
  \attack, 0.0,
  \distamp, Pseq([Pn(0.1, 7), 0.3, Pn(0.02, 5), 0.9], inf),
  \decay, 0.75,
  \dur, Pseq([
    3, 3, 1,
    Prand([
      Pseq([2, 4, 2], 2),
      Pseq([3, 3, 2], 2),
    ], inf)
  ], inf) + 0.5,
)
Ndef(\ping).clear;


Npat(\kick,
  \loop, 28,
  \instrument, \elkick,
  \freq, 50,
  \octave, 1,
  \attack, 0.05,
  \decay, 0.2,
  \ramp, 20,
  \gain, 1.4,

  \dur, Pseq([
    2, 1, 2.5, 0.5, 3, 2, Pn(0.5, 3), 1.5, 3, 1
  ], inf) / 2,
)
Ndef(\kick).quant = 4;
Ndef(\kick).clear;

Npat(\pluck,
  \instrument, \karpluspluck,
  \degree, Pseq([
    Pn(0, 3),
    [0, 4],
    [1, 4, 7],
    Pn(0, 3),
    [1, 4],
    Pn(0, 3),
    [2, 7],
    Pn(6, 3),
  ], inf),
  \octave, 6,
  \colour, Pwrand([0.2, 0.5, 0.9], [12, 3, 1].normalizeSum, inf),
  //\colour, 0.2,
  \decay, 2.5,
  \dur, Pseq([1, 1.5, 6, 3, 7.5, Rest(3)], inf),
)
Ndef(\pluck).clear;
