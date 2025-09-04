
Ndef(\delaymix, {
  Mix.new([
    //ChannelStrip(Ndef(\blip).ar(1), -6.dbamp, 0),
    ChannelStrip(Ndef(\rim).ar(1), -6.dbamp, 0, 400),
  ])
})

Ndef(\gldelay)[0] = \simpledelay;
Ndef(\gldelay).quant = 4
Ndef(\gldelay) <<> Ndef(\delaymix)
Ndef(\gldelay).clear

(
  Ndef(\gldelay)[1] = \pset -> Pbind(
    \drywet, 0.5,
    \delay, Pseq([0.4, 0.9, Prand([0.01, 0.1])], inf),
    //\delay, Pseq([0.75, 0.5, Prand([0.01, 0.2])], inf),
    //\delay, 0.25,
    \feedback, Pwrand([0.1, 0.4, 0.98], [2, 10, 0].normalizeSum, inf),
    //\feedback, 0.75,
    \dur, 0.25,
  )
)
