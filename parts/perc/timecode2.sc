
p.clock.tempo = 160/60;

NpatLoop(\tc2, 24,
  \instrument, \fm3filter,
  \octave, 4,
  \degree, 0,
  \ratio1, 0.5 + Pseq([Pn(0, 2), 0.02, Pn(0, 3), 0.01], inf),
  \mod, Pseq([1], inf),
  \mod1, 1,
  \attack, 0.0,
  \release, Pwhite(0.0, 0.07, inf),
  \attack1, Pwrand([0, 1, 2], [15, 3, 1].normalizeSum, inf),
  \release1, 1.0,
  \level1, Pseq([0.5, 0.5, 1, 0.5, 1, 1], inf),
  \legato, 0.5,
  \resonance, 0.3,
  \timingOffset, Pseq([0, 0.04, 0, 0.04], inf),
  \gain, Pseq([1], inf),
  \cutoff, 1300,
  \dur, Pseq([1.5, 0.5, 0.5, 1, 1.5, 2, 2.5, 0.5, 0.5, 2, 1.5], inf)
)

(
Ndef(\tc2).filter(1, {|in, clip=0.95|
  (in * clip).clip * clip.min(1).reciprocal;
})
)

Ndef(\tc2).map(\clip, Ndef(\tc2clip))

Ndef(\tc2clip, Pcontrol(
  \mod,
  \value, Pseq([5, 4, 5, 0.1, 9, 9, 5, 5, 9], inf),
  \slew, 0.01,
  \dur, 1/2
)
)

Ndef(\tc2clip).quant = 4;

Ndef(\tc2clip).free
Ndef(\tc2).free
