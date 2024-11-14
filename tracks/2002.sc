
Ndef(\bass,
  Pbind(
    \instrument, \sinbass,
    \root, -5,
    \octave, Pseq([5, 3, 3, 3, 3], inf),
    \degree, 0,
    \attack, 0.001,
    \decay, 0.005,

    \amp, 1,
    \dur, Pseq([6, Rest(2)], inf),
  )
)
Ndef(\bass).clear;
Ndef(\bass).map(\gain, Ndef(\bassgain))

Ndef(\bassgain,
  Pcontrol(
    \mod,
    \value, Pseq([Pn(1, 3), 1.5, Pn(1.2, 7), 2.1, Pn(1.8, 2)], inf),
    \slew, 0.5,
    \dur, 1.5 / 2
  )
)
Ndef(\bassgain).quant = 4;
Ndef(\bassgain).clear
