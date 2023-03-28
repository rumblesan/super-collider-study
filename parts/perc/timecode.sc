
p.clock.tempo = 160/60;

Ndef(\tc1)[0] = Pbind(
  \instrument, \fm3filter,
  \octave, 4,
  \degree, -3,
  \ratio1, 1.5,
  \mod, Pseq([
    Pseq([0.2, 0.2, 0.7]),
    Pseq([0.2, 0.7], 2),
  ], inf),
  \mod1, 8,
  \attack, 0.01,
  \release, 0.01,
  \amp, Pseq([
    Pseq([1, 0.8, 0.8],2),
    Pseq([1, 0.8],2),
  ], inf
  ),
  \legato, 0.8,
  \resonance, 0.3,
  \gain, Pseq([
    Pseq([3, 1.5, 1.5], 2),
    Pseq([3, 1.5], 2),
  ], inf),
  \cutoff, 300,
  \dur, Pseq([1], inf) / 3
)

(
Ndef(\tc1).filter(1, {|in, clip=0.95|
  (in * clip).clip * clip.min(1).reciprocal;
})
)

Ndef(\tc1).map(\clip, Ndef(\tc1clip))

Ndef(\tc1clip, Pcontrol(
  \mod,
  \value, Pseq([1, 1.8, 1, 1, 1.3, 5], inf),
  \slew, 0,
  \dur, 1/2
))

Ndef(\tc1clip).quant = 4;

Ndef(\tc1).free
