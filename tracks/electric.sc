
Npat(\bass,
  \loop, 16,
  \instrument, \sinbass,
  \degree, 0,
  \octave, 3,
  \attack, 0.0,
  \decay, 0.05,
  \legato, Pseq([Pn(0.2, 3), 0.8, Pn(0.2, 2)], inf),
  \dur, Pseq([
    1.5, 2.5, 1, 1, 1, r, r
  ], inf),
)

// impulsefreq, pan, q
Npat(\blip,
  \instrument, \blip,
  \q, Pwrand([0.1, 0.2, 0.3], [10, 2, 1].normalizeSum, inf),
  \freq, 1500,
  \impulsefreq, Pseq([Pn(50, 2), 100, 15, Pn(50, 2)], inf),
  \gain, 50,
  \pan, Pseq([-1, 1, -1, 0, 1, 0, -1, 1, 1, 1], inf) * 0.5,
  \dur, Pseq([1, r, 1, 1,
    Prand([
      Pseq([1, r, 1.5, 0.5]),
      Pseq([2.5, r, 0.5]),
    ], 2),
    r, 1.5, r, r,
    r, 0.5, r], inf)
)


// degree, octave, distamp
Npat(\ping,
  \instrument, \ping,
  \degree, 0,
  \octave, Pwrand([5, 6], [10, 3].normalizeSum, inf),
  \attack, 0.0,
  \distamp, 0.1,
  \decay, Pseq([0.1, 0.1, 0.5, 0.1, 0.1, 0.2], inf),
  \dur, Pseq([1, r, 2, r, 2, 3, r], inf),
)


Npat(\kick,
  \loop, 16,
  \instrument, \elkick,
  \freq, 50,
  \attack, Pwrand([0.05, 0.3], [15, 1].normalizeSum, inf),
  \decay, Pseq([0.2, 0.2, 0.4, 0.2, 0.5, 0.2, 0.5], inf),
  \ramp, Pwrand([10, 20, 40], [5, 15, 2].normalizeSum, inf),
  \gain, Pseq([1.5, 1.5, 2, 1.5, 2.5, 1.5, 1.5, 2], inf),
  \dur, Pseq([
    3, 3, 2,
    Prand([
    Pseq([1, 1, 2, 2, 2]),
    Pseq([2, 2, Pr(5, 1), 2]),
    ], 2)
  ], inf) / 2,
)

// decay, colour(0.2 - 0.9), degree (0, [1,4,7], [1,4], [2,7],
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
  \octave, Pseq([Pn(6, 4), 7], inf),
  \colour, Pwhite(0.2, 0.4, inf),
  \decay, 2.5,
  \dur, Pseq([1, 1.5, 3, 3, 4.5, Rest(3)], inf),
)
