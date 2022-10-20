"Setup.scd".load;

d[\vox].numChannels

(
  ~kick1 = Pbind(
    \instrument, \bplay,
    \buf, d[\bd][0],
    \dur, 1,
  );
)
~kick1.play;
~kick1.stop;

(
  ~snare1 = Pbind(
    \instrument, \bplay,
    \buf, d[\sn][0],
    \dur, 2,
  );
)
~snare1.play;
~snare1.stop;

(
  ~vox = Pbind(
    \instrument, \PhaseVox,
    \buf, d[\vox][0],
    \rate, 0.2,
    \dur, 3,
    \start, Pseq([0, 0.42, 0.57, 0.1], inf),
  );
)
~vox.play;
~vox.stop;


p.clear;
