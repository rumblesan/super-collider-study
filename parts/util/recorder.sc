~recorder = Recorder.new(s);
(
  var path = "./recordings/%.wav".format(Date.getDate.format("%Y%m%d%H%M"));
  ~recorder.record(path: path, numChannels: 2, bus: Ndef(\out).bus);
)

~recorder.stopRecording;
