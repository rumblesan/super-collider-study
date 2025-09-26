
Ndef(\delaymix, {
  Mix.new([
    ChannelStrip(Silent.ar, -3.dbamp, 0),
  ])
})

Ndef(\gldelay)[0] = \simpledelay;
Ndef(\gldelay) <<> Ndef(\delaymix)

(
  Ndef(\gldelay)[1] = \pset -> Pbind(
    \drywet, 1.0,
    \delay, 0.25,
    \feedback, 0.75,
    \dur, 0.25,
  )
)
