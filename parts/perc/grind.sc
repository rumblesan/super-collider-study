Npat(\grind,
  \loop, 16,
  \instrument, \buzz1,
  \octave, 2,
  \degree, 0,
  \ratio, 3.027,
  \wave, 0.7,
  \fold, 80,
  \dur, Pseq([2, Rest(2)], inf)
)


(
Ndef(\grind).filter(1, {|in, clip=0.95|
  (in * clip).clip * clip.min(1).reciprocal;
})
)

Ndef(\grind).set(\clip, 1.8)

Ndef(\grind).map(\wave, Ndef(\wavemod))
NpatControl(\wavemod,
  \mod,
  \value, Pseq([Pn(0.7, 2), 0.8, 0.3, Pn(0.5, 3), 0.6], inf),
  \slew, 1,
  \dur, 4
)

Ndef(\grind).map(\fold, Ndef(\foldmod))
NpatControl(\foldmod,
  \mod,
  \value, Pseq([80, 70, 60, 70], inf),
  \slew, 4,
  \dur, 4
)
