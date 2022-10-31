
~mix = {Silent.ar}

~verb[0] = \verb;
~verb.fadeTime = 4;

(
  ~verb[1] = \pset -> Pbind(
    \drywet, 0.2,
    \hipass, 50,
    \lopass, 6000,
    \predelay, 0.06,
    \size, 0.2,
    \decay, 0.8,
    \diffusion, 0.3,
    \downsampling, 0.0,
    \gain, 1.0,
    \damp, 0.6,
    \width, 0.2,
    \dur, 0.5,
  )
)

~out = { \in.ar(0!2) }; ~out.play;

~verb <>> ~out
~mix <>> ~verb
