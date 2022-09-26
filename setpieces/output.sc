
"Setup.scd".load;
s.plotTree

(
  SynthDef(\outputreverb, {
    arg out=0, in, drywet=0.2, size=0.5, damp=0.5, gain=1.2;
    var verb = FreeVerb2.ar(in, in, mix: 1, room: size, damp: damp);
    var fade = in!2 * (1-drywet) + (verb * drywet);
    Out.ar(out, fade);
  }, [0, \ar]).add
)

~mix = {~piston * 0.5}

~verb[0] = \outputreverb;
~verb.play;
~verb.fadeTime = 4;
~mix <>> ~verb

~verb.set(\drywet, 0.1)
~verb.set(\size, 0.9)
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
