
"Setup.scd".load;

(
  ~pluck1 = Pbind(
    \instrument, \karpluspluck,
    \feedback, 0.989,
    \fdiff, Pwrand([0.5, 1.0, 1.5], [3, 10, 5].normalizeSum, inf),
    \gain, 1.2,
    \decay, 2.0,
    \colour, Pwhite(0.25, 0.4, inf),
    \damping, Pexprand(0.35,0.55, inf),
    \amp, 1.0,
    \scale, Scale.major,
    \root, -3,
    \octave, 5,
    \degree, Pseq(
      [
        0, 4, 7,
        0, 4, 7,
        Prand([[4, 9], 4, 0], 2),
      ],
      inf),
    \dur, Pseq([
      1,1,0.5, 0.5,
      Prand([1, Pseq([0.5, 0.5])], 1),
      2,
      Pwrand([Pn(0.25, 4), 1, 2], [2, 5, 5].normalizeSum, inf),
    ], inf),
  )
)
~pluck1.quant = 4

~pluck2 = Pbind(
    \instrument, \karpluspluck,
    \feedback, 0.989,
    \fdiff, Pwrand([0.5, 1.0, 2.5], [3, 10, 5].normalizeSum, inf),
    \gain, 1.2,
    \decay, 2.0,
    \colour, Pwhite(0.15, 0.3, inf),
    \damping, Pexprand(0.75,0.85, inf),
    \amp, 0.7,
    \scale, Scale.major,
    \root, -3,
    \octave, 3,
    \degree, Pwrand([0, 4, 7], [5, 3, 1].normalizeSum, inf),
    \dur, Pseq([Rest(1), 4, 4, 2], inf)
)
~pluck2.quant = 4;


~mix = { (~pluck1 * 0.8) + (~pluck2 * 0.5) }
~mix.stop;

~delay[0] = \simpledelay;
(
~delay.set(\drywet, 0.4);
~delay.set(\delay, 0.1);
~delay.set(\feedback, 0.5);
)

~delay[1] = \pset -> Pbind(
  \delay, Prand([0.3, 0.5, 1, 0.001], inf),
  \dur, 0.25,
)




~verb = \verb;
(
  ~verb.set(\diffusion, 0.9);
  ~verb.set(\drywet, 0.35);
  ~verb.set(\size, 0.9);
  ~verb.set(\lopass, 6500);
  ~verb.set(\damping, 0.4);
  ~verb.set(\downsampling, 0.8);
)


~out = { \in.ar(0!2) }; ~out.play;

~mix <>> ~delay <>> ~verb <>> ~out
