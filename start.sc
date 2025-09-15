"Setup.sc".load;

Ndef(\mix, {|samplerate = 1.0, bits = 24|
  Decimator.ar(Mix.new([
    ChannelStrip(Silent.ar),
  ]), samplerate * SampleRate.ir, bits)
})

Ndef(\verb)[0] = \dirtverb;
Ndef(\verb).set(
  \drywet, 0.2,
  \size, 0.35,
  \decay, 0.13,
  \damping, 0.9,
)


Ndef(\out, MainOut.compressor); Ndef(\out).play;

Ndef(\mix) <>> Ndef(\verb)
Ndef(\verb) <>> Ndef(\out)

Ndef(\out).proxyspace.clean(Ndef(\out))
