"Setup.sc".load;

Ndef(\mix, {
  Mix.new([
    ChannelStrip.ar(Silent.ar, -3.dbamp, 0),
    ChannelStrip.ar({SinOsc.ar(50)}, -3.dbamp, 0),
  ])
})

Ndef(\verb)[0] = \dirtverb;

Ndef(\verb).set(
  \drywet, 0.3,
  \hipass, 10,
  \lopass, 18000,
  \predelay, 0.07,
  \size, 0.002,
  \decay, 0.55,
  \diffusion, 0.19,
  \downsampling, 0.0,
  \gain, 1.2,
  \damping, 0.5,
  \feedbackHiPass, 60,
  \width, 0.6,
)

Ndef(\verb)[1] = \pset -> Pbind(
  //\downsampling, Pwrand([0, 0.8, 0.9, 0.97], [10, 0, 2, 3].normalizeSum, inf),
  //\drywet, Pwrand([0.2, 0.5, 0.9], [10, 2, 1].normalizeSum, inf),
  //\gain, Pwrand([1, 1.5, 4], [10, 2, 5].normalizeSum, inf),
  //\dur, Pwrand([2, 1, 0.5], [3, 7, 1].normalizeSum, inf) / 2,
  //\size, Pseq([0.4, Pn(0.8, 5), 0.01, 0.9, 0.1, Pn(0.7, 6), 0.01], inf),
  //\damping, Pwrand([0.8, 0.2, 0.91], [15, 4, 2].normalizeSum, inf),
  \dur, 0.5,
);


Ndef(\out, { \in.ar(0!2) * -3.dbamp }); Ndef(\out).play;

Ndef(\verb) <>> Ndef(\out)
Ndef(\mix) <>> Ndef(\verb)


p
p.clock.tempo = 170/60;

Ndef(\out).proxyspace.clean(Ndef(\out))
