"Setup.sc".load;

p.clock.tempo = 150/60;

Ndef(\mix, {
  Mix.new([
    ChannelStrip.ar(Silent.ar, -0.dbamp, 0),
    //ChannelStrip.ar(SinOsc.ar(50), -0.dbamp, 0),
  ]);
})

Ndef(\verb).clear
Ndef(\verb)[0] = \dirtverb;
Ndef(\verb).set(
  \drywet, 0.3,
  \hipass, 160,
  \lopass, 8000,
  \predelay, 0.01,
  \size, 0.09,
  \decay, 0.16,
  \diffusion, 1,
  \downsampling, 0.9,
  \gain, 1.0,
  \damping, 0.3,
  \feedbackHiPass, 160,
  \width, 0.3,
)


Ndef(\out, { \in.ar(0!2) * -3.dbamp }); Ndef(\out).play;

Ndef(\verb) <>> Ndef(\out)
Ndef(\mix) <>> Ndef(\verb)

Ndef(\out).proxyspace.clean(Ndef(\out))

s.prepareForRecord(
  path: "./recordings/%.wav".format(Date.getDate.format("%Y%m%d%H%M"),
  numChannels: 2
);

s.record(bus: Ndef(\out).bus);

s.stopRecording;

CmdPeriod.run
