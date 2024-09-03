"Setup.sc".load;

p.clock.tempo = 160/60;

Ndef(\mix, {|samplerate = 1.0, bits = 16|
  var mix = Mix.new([
    ChannelStrip.ar(Silent.ar, -0.dbamp, 0),
  ]);
  Decimator.ar(mix, samplerate * 44100, bits)
})

Ndef(\mix).set(\samplerate, 1)
Ndef(\mix).set(\bits, 24)
Ndef(\mix).unmap(\bits, Ndef(\mixbits))
Ndef(\mix).unmap(\samplerate, Ndef(\mixsamps))

Ndef(\mixsamps,
  Pcontrol(\mod,
    \value, Pseq([Pn(0.5, 3), 0.3, 0.1, Pn(0.8, 3), 0.2], inf),
    \slew, Pseq([0.0], inf),
    \dur, Pseq([2, 2], inf) / 2
  )
)
Ndef(\mixsamps).quant = 4;

Ndef(\mixbits,
  Pcontrol(\mod,
    \value, Pseq([Pn(24, 3), 4, Pn(20, 6)], inf),
    \slew, Pseq([0], inf),
    \dur, Pseq([1], inf) / 2,
  )
)
Ndef(\mixbits).quant = 4;

Ndef(\verb).clear
Ndef(\verb)[0] = \dirtverb;
Ndef(\verb).set(
  \drywet, 0.25,
  \hipass, 160,
  \lopass, 12000,
  \predelay, 0.02,
  \size, 0.29,
  \decay, 0.60,
  \diffusion, 0.2,
  \downsampling, 0.1,
  \gain, 1.0,
  \damping, 0.5,
  \feedbackHiPass, 160,
  \width, 0.3,
)

Ndef(\verb)[1] = \pset -> Pbind(
  //\drywet, Pseq([0], inf),
  //\downsampling, Pseq([Pn(0, 2), 0.8, Pn(0, 3), 0.9], inf),
  //\width, Pseq([0.3], inf),
  //\gain, Pseq([Pn(1.0, 5), 1.5, Pn(1.4, 3)], inf),
  //\size, Pseq([0.4, 0.9, 0.5, 0.9, Pn(0.4, 6)], inf),
  //\damping, Pseq([0.3], inf)
  \dur, 0.5,
);


Ndef(\out, { \in.ar(0!2) * 0.dbamp }); Ndef(\out).play;

Ndef(\verb) <>> Ndef(\out)
Ndef(\mix) <>> Ndef(\verb)

Ndef(\out).proxyspace.clean(Ndef(\out))
