
p.clock.tempo = 160/60;

Npat(\tc1,
  \instrument, \fm3filter,
  \octave, 6,
  \ratio1, Pseq([Pn(1.7, 4), Pn(3.6, 4), Pn(1, 2), Pn(6, 6)], inf),
  \mod, Pseq([Pn(0.2, 5), Pn(3.4, 3), Pn(0.2, 2), Pn(1.7, 1)], inf) * 3,
  \mod1, 8,
  \attack, 0.00,
  \release, Pwhite(0.2, 0.55, inf),
  \legato, 0.3,
  \resonance, 0.3,
  \gain, Pseq([3], inf),
  \cutoff, 200,
  \dur, Pseq([
    1, 1, Pn(0.5, 3), 1, 0.5, 1, Pn(0.5, 4)
  ], inf) / 4
)

(
Ndef(\tc1).filter(1, {|in, clip=0.95|
  (in * clip).clip * clip.min(1).reciprocal;
})
)

Ndef(\tc1).map(\clip, Ndef(\tc1clip))
Ndef(\tc1).unmap(\clip)

Ndef(\tc1clip, Pcontrol(
  \mod,
  \value, Pseq([1, 1.8, 1, 1, 1.3, 5], inf),
  \slew, 0,
  \dur, 1/2
)
)

Ndef(\tc1clip).quant = 4;

Ndef(\tc1).free
