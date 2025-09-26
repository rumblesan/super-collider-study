
// ratio1 (0.5 + small), release, attack1(0-2), level1(0.5-1), gain
// mod, degree
Npat(\tc2,
  \loop, 24,
  \instrument, \fm3filter,
  \octave, 4,
  \degree, 0,
  \ratio1, 0.5,
  \mod, 1,
  \mod1, 1,
  \attack, 0.0,
  \release, 0.07,
  \attack1, 0,
  \release1, 1.0,
  \level1, 0.5,
  \legato, 0.5,
  \resonance, 0.3,
  \gain, 1,
  \cutoff, 1300,
  \dur, Pseq([1.5, 0.5, 0.5, 1, 1.5, 2, 2.5, 0.5, 0.5, 2, 1.5], inf)
)

(
Ndef(\tc2).filter(1, {|in, clip=0.95|
  (in * clip).clip * clip.min(1).reciprocal;
})
)

//map clip (1-9)
