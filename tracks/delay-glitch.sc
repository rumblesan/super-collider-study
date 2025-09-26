Npat(\ping,
  \loop, 16,
  \instrument, \ping,
  \decay, Pwrand([0.4, 1], [10, 0].normalizeSum, inf),
  \octave, 5,
  \degree, Pwrand([0, 2, 7, -12, 10], [1, 3, 4, 0].normalizeSum, inf),
  \ptrig, Pseq([1, 1, 0, 1, 1, 0, 0], inf),
  \pmod, Prand([1, 0, 0, 3, 7, 0], inf),
  \pdecay, 0.05,
  \distamp, 0,
  \dur, Pseq([4, 4, 1, 0.25, 0.25, 0.5], inf),
)


Npat(\clicker,
  \instrument, \clikr,
  \dur, Pwrand([1, 0.5, 0.25], [1, 3, 19].normalizeSum, inf),
  \degree, Pwrand([0, 3, 4, -12], [10, 3, 3].normalizeSum, inf),
  \octave, 5,
  \harmonics, Pseq([200, 10, 10], inf),
  \decay, Pwrand([0.1, 0.5, 1], [5, 5, 1].normalizeSum, inf),
  \sustain, Prand([0.3, 0.5, 0.3], inf),
)


Ndef(\delaymix, {
  Mix.new([
    ChannelStrip(Silent.ar, -0.dbamp, 0),
  ])
})

Ndef(\gldelay)[0] = \simpledelay;
Ndef(\gldelay) <<> Ndef(\delaymix)

(
  Ndef(\gldelay)[1] = \pset -> Pbind(
    \drywet, 0.5,
    \delay, 0.5,
    \feedback, 0.5,
    \dur, 0.25,
  )
)
