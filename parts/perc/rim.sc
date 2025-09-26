// decay 0.02-0.1, click, pan, dur
Npat(\rim,
  \loop, 16,
  \instrument, \rim,
  \degree, 3,
  \decay, Pseq([Pn(0.02, 3), 0.04], inf),
  \click, 5,
  \pan, Pwhite(-0.4, 0.4, inf),
  \dur, Pseq([
    2, 2, Prand([Rest(2), 2], 2)
  ], inf) / 4,
)
