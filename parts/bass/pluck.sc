Npat(\bass,
  \loop, 32,
  \instrument, \karpluspluck,
  \feedback, 0.95,
  \fdiff, 1.3,
  \gain, 10.0,
  \decay, 4.0,
  \colour, 0.02,
  \damping, Pwrand([0.9, 0.6], [10, 3].normalizeSum, inf),
  \scale, Scale.minor,
  \octave, 3,
  \degree, Pseq([Pn(0, 2), Prand([0, 2, 5], 2), Prand([0, 2, 4], 2)], inf),
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
