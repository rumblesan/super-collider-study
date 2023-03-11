"Setup.scd".load;

(
~mix = {
  Mix.new([
    Pan2.ar(Silent.ar, 0),
  ])
}
)

~verb[0] = \verb;

~verb[1] = \pset -> Pbind(
  \drywet, 0.2,
  \hipass, 50,
  \lopass, 6000,
  \predelay, 0.06,
  \size, 0.2,
  \decay, 0.8,
  \diffusion, 0.3,
  \downsampling, 0.0,
  \gain, 1.0,
  \damp, 0.6,
  \width, 0.2,
  \dur, 0.5,
);

~out = { \in.ar(0!2) * -0.dbamp }; ~out.play;

~verb <>> ~out;
~mix <>> ~verb;



r = Recorder.new(s);
(
  var path = "./recordings/%.wav".format(Date.getDate.format("%Y%m%d%H%M"));
  r.record(path: path, numChannels: 2, bus: ~out.bus);
)

r.stopRecording;
