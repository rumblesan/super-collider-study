"Setup.scd".load;

(
d = Dictionary();
d.put(\k, Buffer.read(s, "./samples/kick1.wav"));
d.put(\s, Buffer.read(s, "./samples/snare1.wav"));
d.put(\v, Buffer.read(s, "./samples/voxloop.wav"));
)

d[\v].numChannels

(
  ~kick1 = Pbind(
    \instrument, \bplay,
    \buf, d[\k],
    \dur, 1,
  );
)
~kick1.play;
~kick1.stop;

(
  ~snare1 = Pbind(
    \instrument, \bplay,
    \buf, d[\s],
    \dur, 2,
  );
)
~snare1.play;
~snare1.stop;

(
  ~vox = Pbind(
    \instrument, \PhaseVox,
    \buf, d[\v],
    \rate, 0.2,
    \dur, 3,
    \start, Pseq([0, 0.42, 0.57, 0.1], inf),
  );
)
~vox.play;
~vox.stop;


p.clear;
