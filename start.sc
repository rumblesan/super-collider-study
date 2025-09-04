"Setup.sc".load;

p.clock.tempo = 160/60;

Ndef(\mix, {|samplerate = 1.0, bits = 24|
  Decimator.ar(Mix.new([
    ChannelStrip(Silent.ar, -0.dbamp, 0),
  ]), samplerate * 44100, bits)
})

Ndef(\verb)[0] = \dirtverb;
Ndef(\verb).set(
  \drywet, 0.6,
  \size, 0.35,
  \decay, 0.33,
  \damping, 0.2,
  \downsampling, 0.0,
  \gain, 1.0,
)


Ndef(\verb)[1] = \pset -> Pbind(
  \dur, 5,
);


Ndef(\out, { BHiPass4.ar( \in.ar(0!2) * 0.dbamp, 40) }); Ndef(\out).play;

Ndef(\verb) <>> Ndef(\out)
Ndef(\mix) <>> Ndef(\verb)

s.meter

Ndef(\out).proxyspace.clean(Ndef(\out))
Ndef(\verb).clear
