
s.plotTree;

"Setup.scd".load;

b = Buffer.readChannel(s, "./samples/voxloop.wav", channels: [0]);
b.numChannels;

(
  SynthDef(\grains, { arg out, gate=1, trate=10, rate=1, pos=3, glen=0.6;
    var trigger, venv, grains;
    trigger = Impulse.ar(trate);
    venv = Env.asr(1, 0.8, 4).kr(2, gate);
    grains = TGrains.ar(2, trigger, b, rate, pos, glen, 0, 1.0, 4);
    Out.ar(out, grains);
  }).add;
)


(
  ~grains1 = {
    var trate, rate, trigger;
    trate = 10;
    rate = 1.0;
    trigger = Impulse.ar(trate);
    TGrains.ar(2, trigger, b, rate, 3, 0.6, 0, 1.0, 4)
  };
)
~grains1.play;
~grains1.clear;


(
  ~grains2 = {
    var b, trate, dur, clk;
    b = Buffer.readChannel(s, "./samples/voxloop.wav", channels: [0]);
    trate = MouseY.kr(2,200,1);
    dur = 4 / trate;
    clk = Dust.kr(trate);
    TGrains2.ar(2, clk, b, 1.0, LFNoise2.kr(0.5).range(0, BufDur.kr(b)), dur, 0, TRand.kr(0.1, 0.2, clk), MouseX.kr(0.003, 0.01), 0.007, 4);
  };
)
~grains2.play;


(
  ~cloud1 = Pmono(
    \grains,
    \trate, 10,
    \dur, Pseq([5, Rest(5)], inf),
  )
)
~cloud1.play;
~cloud1.clear;

(
  ~cloud2 = Pbind(
    \instrument, \grains,
    \trate, 10,
    \dur, Pseq([1, Rest(1)], 2),
  );
)
~cloud2.play;
