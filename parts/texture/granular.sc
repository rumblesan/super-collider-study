
Npat(\grains,
  \instrument, \grainplayer,
  \buffer, d[\milkwood][3],
  \amp, 1.0,
  \legato, 1,
  \dur, 3,
)
Ndef(\grains).clear

Ndef(\grains).map(\pos, Ndef(\posmod))
NpatControl(\posmod,
  \mod,
  \value, Pseq([Pn(0, 2), 0.1, 0, 0.2], inf),
  \slew, Pseq([Pn(0, 3), 0.5, Pn(0, 2), 0.3], inf),
  \dur, 1
)
