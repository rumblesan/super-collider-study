Npat(\rim,
  \loop, 16,
  \instrument, \rim,
  \degree, 3,
  \decay, Pseq([Pn(0.02, 3), 0.1, Pwhite(0.02, 0.09, 3), 0.1, Pn(0.02, 3)], inf),
  \click, Pseq([Pn(5, 3), Pn(10, 5), Pn(3, 5)], inf),
  \pan, Pwhite(-0.4, 0.4, inf),
  \dur, Pseq([
    2, 2, Pr(4, 3), 3, 1, Pr(3, 2), 3, 1, 3,
  ], inf) / 4,
)
