
p.clock.tempo = 160/60;

(
Ndef(\tc2)[0] = Pbind(
  \instrument, \fm3filter,
  \octave, Pwrand([4, 3], [10, 2].normalizeSum, inf),
  \degree, 3,
  \ratio1, 0.5,
  \mod, Pseq([1], inf),
  \mod1, 1,
  \attack, 0.0,
  \release, 0.0,
  \attack1, 0.0,
  \release1, 1.0,
  \amp, Pseq([1, 0.7, 0.7, 1, 0.7], inf),
  \level1, Pseq([0.5, 0.5, 1, 0.5, 1, 1], inf),
  \legato, Pwhite(0.8, 0.95, inf) / 2,
  \resonance, 0.3,
  \timingOffset, (Pseq([0, 0.02, 0, 0], inf) + Pwhite(0, 0.01, inf)),
  \gain, Pseq([1], inf),
  \cutoff, 1300,
  \dur, Pseq([1, 2, 1, 1/2, 1/2, 2], inf) / 2
)
)

(
Ndef(\tc2).filter(1, {|in, clip=0.95|
  (in * clip).clip * clip.min(1).reciprocal;
})
)

Ndef(\tc2).map(\clip, Ndef(\tc2clip))

Ndef(\tc2clip, Pcontrol(
  \mod,
  \value, Pseq([5, 4, 5, 0.1], inf),
  \slew, 0.01,
  \dur, 1/2
)
)

Ndef(\tc2clip).quant = 4;

Ndef(\tc2clip).free
Ndef(\tc2).free
