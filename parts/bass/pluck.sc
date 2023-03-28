Ndef(\pluck)[0] = Pbind(
  \instrument, \karpluspluck,
  \feedback, 0.95,
  \fdiff, 1.5,
  \amp, 1,
  \gain, 10.0,
  \decay, 6.0,
  \colour, Pwrand([0.3, 0.5], [10, 3].normalizeSum, inf),
  \damping, 0.9,
  \scale, Scale.minor,
  \root, -5,
  \octave, 3,
  \degree, Pseq([0], inf),
  \dur, Pseq([5, 5, 3, 5, 3], inf),
)

(Ndef(\pluck).filter(1, {|in, downsampling=0|
  SmoothDecimator.ar(in, SampleRate.ir * (1 - downsampling));
}))

Ndef(\pluck).map(\downsampling, Ndef(\plsamp))

Ndef(\plsamp, Pcontrol(
  \mod,
  \value, Pseq([Pn(0, 2), Prand([0, 0.9, 0.99], 2), Pn(0, 2), 0.9, Pn(0, 3), 0.9, Pn(0, 2), 0.96], inf),
  \slew, 0,
  \dur, 0.5,
))
Ndef(\plsamp).quant = 4;
