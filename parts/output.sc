"Setup.scd".load;

Ndef(\mix,
  {
    Mix.new([
      ChannelStrip.ar(Silent.ar, -3.dbamp, 0),
      //ChannelStrip.ar(SinOsc.ar(32.midicps), ~faders[1].kr(1), 0),
      //ChannelStrip.ar(Ndef(\tc1).ar(1), ~faders[1].kr(1), 0),
    ])
  }
)


Ndef(\verb)[0] = \dirtverb;

Ndef(\verb)[1] = \pset -> Pbind(
  \drywet, 0.4,
  \hipass, 150,
  \lopass, 6000,
  \predelay, 0.06,
  \size, 0.3,
  \decay, 0.5,
  \diffusion, 0.5,
  \downsampling, 0,
  \gain, 1.1,
  \damping, 0.2,
  \feedbackHipass, 20,
  \width, 0.3,
  \dur, 0.5,
);


Ndef(\out, { \in.ar(0!2) * -0.dbamp }); Ndef(\out).play;

Ndef(\verb) <>> Ndef(\out)
Ndef(\mix) <>> Ndef(\verb)
