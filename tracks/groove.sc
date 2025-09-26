//

Npat(\kick,
  \loop, 16,
  \instrument, \bkick,
  \freq, 50,
  \attack, 0.01,
  \decay, 1.4,
  \ramp, 25,
  \rampdecay, 0.05,
  \noisedecay, 0.1,
  \gain, 8,

  \amp, 1,
  \dur, Pseq([3,
    Prand([3, 2, 1], 1),
    Pr(4, 1),
    0.5, 2.5,
    Prand([3, Pn(1, 3)]),
    2], inf),
  //\dur, 2,
)

// feels iffy still
Npat(\perc,
  \loop, 24,
  \instrument, \buzz2,
  \degree, Pseq([
    Pn(-3, 16),
    Pn(-1, 12),
    Pn(0, 18),
    Pn(-1, 10),
  ], inf),
  \attack, Pwrand([0.01, 0.1], [10, 1].normalizeSum, inf),
  \decay, 0.001,
  \decay, Pwhite(0.0001, 0.2, inf),
  \noise, Pseq([
    Pn(0, 3), 3, Pn(0.2, 3), 2, Pn(0, 7)
  ], inf),
  \noise, 0,
  \bits, Pwrand([6, 3, 2], [10, 7, 3].normalizeSum, inf),
  \bits, 5,
  \gain, 21,
  \legato, 0.5,
  \amp, 1,
  \dur, Pseq([1,3,
    Prand([1, 2, 3]),
    2,3,1,2,3,1,3,1], inf) / 4
)
