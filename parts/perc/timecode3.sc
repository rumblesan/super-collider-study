
(
Ndef(\tc3)[0] = Pbind(
  \instrument, \fm3,
  \attack, 0,
  \decay, 0.5,
  \mod, 0.3,
  \ratio1, Pwrand([1, 1.5, 0.752], [13, 3, 1].normalizeSum, inf),
  \modDepth1, 0.5,
  \ratio2, 0.5,
  \modDepth2, 1.25,
  \legato, 0.8,
  \amp, Pseq([1, 0.8, 0.8, 1, 0.8, 1], inf),
) <> Pacid(
  \degree, Pseq([
    Pn(Pseq([0, 2, 5]), 3),
    Pn(Pseq([0, 2, 6, 0, 2, 7]), 1),
  ], inf),
  \pulses, Pseq([2, 2, 1, 1, 2], inf),
  \types, Pseq("..-.*.", inf),
  \dur, 1/2
  )
)
Ndef(\tc3).clear;

(
Ndef(\tc3).filter(1, {|in, gain=1.1, clip=1.05|
  CrossoverDistortion.ar(((in * gain).tanh), clip, clip) * (clip.reciprocal);
})
)

Ndef(\tc3).map(\gain, Ndef(\gmod))
Ndef(\gmod, Pcontrol(
  \mod,
  \value, Pseq([1.1, Pn(1, 3), Pn(2, 2), 1.1, 1.0, 1.7], inf),
  \slew, 0.1,
  \dur, 1/3,
)
)
Ndef(\gmod).quant = 4;

Ndef(\tc3).map(\clip, Ndef(\cmod))
Ndef(\cmod, Pcontrol(
  \mod,
  \value, Pseq([0.1, Pn(1, 3), Pn(0.2, 2), 0.1, 1.0, 0.7, 0.1], inf),
  \slew, 0.1,
  \dur, 0.5,
)
)
Ndef(\cmod).quant = 4;
