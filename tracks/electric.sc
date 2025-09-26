
// legato
Npat(\bass,
  \loop, 16,
  \instrument, \sinbass,
  \degree, 0,
  \octave, 3,
  \attack, 0.0,
  \decay, 0.05,
  \legato, 0.2,
  \dur, Pseq([
    1.5, 2.5, 1, 1, 1, r, r
  ], inf),
)

// impulsefreq, pan, q
Npat(\blip,
  \instrument, \blip,
  \q, 0.1,
  \freq, 1500,
  \impulsefreq, 15,
  \legato, 0.5,
  \gain, 50,
  \pan, 0,
  \dur, Pseq([
    1, r, 1, 1,
    r, 1.5, r, r,
  ], inf)
)


// degree, decay, octave (5-6), distamp
Npat(\ping,
  \instrument, \ping,
  \degree, 0,
  \octave, 5,
  \attack, 0.0,
  \distamp, 0.1,
  \decay, 0.1,
  \dur, Pseq([1, r, 2, r, 2, 3, r], inf),
)


// decay, attack, ramp
Npat(\kick,
  \loop, 16,
  \instrument, \elkick,
  \freq, 50,
  \attack, 0.05,
  \decay, 0.2,
  \ramp, 20,
  \gain, 1.5,
  \dur, Pseq([
    4,
  ], inf) / 2,
)

// decay, colour(0.2 - 0.9), degree (0, [1,4,7], [1,4], [2,7],
Npat(\pluck,
  \instrument, \karpluspluck,
  \degree, Pseq([
    Pn(0, 3),
  ], inf),
  \octave, 6,
  \colour, 0.2,
  \decay, 2.5,
  \dur, Pseq([Rest(3), 3.5, 6, 3, 6.5, Rest(3)], inf),
)
