"Setup.scd".load;

(
  ~chords = Pbind(
    \instrument, \splay,
    \bufnum, d[\pads][13],
    \amp, -12.dbamp,
    \rate, 1.0,
    \release, 0.1,
    \attack, 0.1,
    \start, Pseq([Pn(0, 4), Pn(0.2, 4), Pn(0, 6), 0.5, 0.9], inf),
    \legato, 0.5,
    //\dur, Pseq([2, 2, 0.5, 1, 2, 2, 0.5, 0.5, 0.5, 1], inf) / 2,
    \dur, Pseq([4], inf),
  )
)
~chords.quant = 4;
~chords.play;
~chords.stop;

~mix = {~chords}

p.clock.tempo = 170/60;
