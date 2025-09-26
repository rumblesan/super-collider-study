// damping, colour?, degree (0, 2, 4, 5)
Npat(\bass,
  \loop, 32,
  \instrument, \karpluspluck,
  \feedback, 0.95,
  \fdiff, 1.3,
  \gain, 10.0,
  \decay, 4.0,
  \colour, 0.02,
  \damping, 0.9,
  \octave, 3,
  \degree, 0,
  \dur, Pseq([4, 4, 2, 4, 2], inf),
)

(
Ndef(\bass).filter(
  1, {|in, downsampling=0|
  SmoothDecimator.ar(in, SampleRate.ir * (1 - downsampling));
})
)

Ndef(\bass).map(\downsampling, Ndef(\plsamp))

// map downsampling, with some slewing
