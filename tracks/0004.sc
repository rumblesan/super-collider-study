
s.boot;

s.plotTree;

b = Buffer.readChannel(s, "../samples/shouldntbehere.wav", channels: [0]);

(
  SynthDef(\grains, { arg gate=1, trate=10, rate=1, pos=3, glen=0.6;
    var trigger, venv;
    trigger = Impulse.ar(trate);
    venv = Env.asr(1, 0.8, 4).kr(2, gate);
    TGrains.ar(2, trigger, b, rate, pos, glen, 0, 1.0, 4);
  }).add;
)


(
  {
    var trate, rate, trigger;
    trate = 10;
    rate = 1.0;
    trigger = Impulse.ar(trate);
    TGrains.ar(2, trigger, b, rate, 3, 0.6, 0, 1.0, 4)
  }.play;
)


b.numChannels;

(
{
  var b, trate, dur, clk;
  b = Buffer.readChannel(s, "./shouldntbehere.wav", channels: [0]);
  trate = MouseY.kr(2,200,1);
  dur = 4 / trate;
  clk = Dust.kr(trate);
  TGrains2.ar(2, clk, b, 1.0, LFNoise2.kr(0.5).range(0, BufDur.kr(b)), dur, 0, TRand.kr(0.1, 0.2, clk), MouseX.kr(0.003, 0.01), 0.007, 4);
}.play;
)

(
  Pdef(\cloud,
    Pbind(
      \instrument, \grains,
      \trate, 10,
      \dur, Pseq([5, Rest(5)], inf),
    )
  )
)
Pdef(\cloud).play;

Pbind(
  \instrument, \grains,
  \trate, 10,
  \dur, Pseq([1, Rest(1)], 2),
).play;
