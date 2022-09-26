
"Setup.scd".load;
s.plotTree

~mix = {Silent.ar}
~mix.play
~mix.stop

~verb[0] = \verb;
~verb.fadeTime = 4;

~verb.set(\drywet, 0.1)
~verb.set(\size, 0.2)
~verb.set(\damp, 0.6)


(
  ~verb[1] = \pset -> Pbind(
    \size, Pwrand([0.1, 0.9, 1.7], [15, 5, 1].normalizeSum, inf),
    \dur, Pseq([0.5, 0.5, 0.25], inf),
    \damp, Pseq([0.6], inf),
    \drywet, Prand([0.1, 0.1, 0.9], inf),
  )
)

~out = { \in.ar(0!2) }; ~out.play;

~verb <>> ~out
~mix <>> ~verb
