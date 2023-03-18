

Ndef(\p1, Pbind(
  \instrument, \fm3,
  \scale, Scale.minor,
  \root, 7,
  \octave, 4,
  \degree, Pseq([0, 2, 4, 0, 5, 8, 7, 0, 0], inf),
  \amp, f.fader(3),
  \attack, 0.001,
  \decay, 0,
  \legato, Pwhite(0.03, 0.2, inf),
  \mod, 2.3,
  \mod1, 1,
  \attack1, 0,
  \decay1, 0.1,
  \ratio1, 2.49,
  \mod2, 8.75,
  \attack1, 0,
  \decay1, 1,
  \ratio2, 2.51,
  \dur, Pn(
    Pfinval(16,
      Pseq([
        2,1,1,
        1, 2,
        Prand([1, 2, 4]),
        1, 1,
        Prand([2, 4])], inf) / 4,
    ), inf
  )
))

(
  Ndef(\p1).filter(1, {|in, gain=1.3|
    (in * gain).clip;
  })
)
Ndef(\p1).set(\gain, 3)

Ndef(\p1).map(\mod1, Ndef(\m1env))
Ndef(\m1env, Pcontrol(
  \percenv,
  \attack, 0.01, // *
  \decay, 0.8,
  \curve, -8,
  \value, 14,
  \offset, 0,
  \dur, Pseq([3, 3, 3, 2, 2], inf)
))

Ndef(\m1env).quant = 4;
