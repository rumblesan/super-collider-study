"Setup.scd".load;
s.plotTree

(
  SynthDef(\ceed, {arg out, freq = 220, fmod = 1, q = 1, gate = 1, attack = 0.1, release = 0.6;
    var osc, env, lpf;
    osc = Saw.ar(freq * 1.11, 4);
    env = Env.asr(attack, 1, release).kr(2, gate);
    lpf = RLPF.ar(Splay.ar(osc), freq * fmod, q, env);
    Out.ar(out, lpf);
  }).add;
)

(
  ~tune = Pbind(
    \instrument, \ceed,
    \scale, Scale.minor,
    \degree, Pseq([0, 2, 0, 2, 0, 8, 6, 1], inf),
    \octave, 5,
    \dur, 0.5,
  )
)
~tune.quant = 4
~tune.play;
~tune.stop;
~tune.clear;

b = Buffer.alloc(s, s.sampleRate * 20, 1);
b.bufnum;
b.numChannels;
b.plot

(
  SynthDef(\record, { |in, bufnum, time|
    var t = time;
    var env = EnvGen.kr(Env.linen(0, t, 0), doneAction: 2);
    RecordBuf.ar(in, bufnum);
  }, [\ar, \ir, \ir]).add;
)

Synth.new(\record, [\in, ~tune, \bufnum, b, \time, 10], s)

(
  SynthDef(\playback, { |out=0, bufnum, time|
    var playbuf = PlayBuf.ar(1, bufnum);
    var env = EnvGen.kr(Env.linen(0, time, 0), doneAction: 2);
    Out.ar(out, playbuf);
  }).add;
)

~playb = \playback
~playb.set(\bufnum, b)
~playb.set(\time, 1)

~out = {~playb}
~out.play
