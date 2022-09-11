(
  "Setup.scd".load;
  m = ProxyMixer(p, 8);
)

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
    \freq, 440,
    \dur, Pseq([2, 0.5, 0.5, 1, 1, 4, 0.5, 2], inf),
  )
)
~tune.play;
~tune.stop;
~tune.clear;

b = Buffer.alloc(s, s.sampleRate * 20, 1);
b.bufnum;
b.numChannels;

(
  SynthDef(\record, { arg out, bufnum, input;
    RecordBuf.ar(input, bufnum);
  }).play(s, [\out, 0, \bufnum, b, \input, ~tune]);
)


(
  ~playb = SynthDef(\playback, { arg out, bufnum;
    var playbuf;
    playbuf = PlayBuf.ar(1, bufnum);
    FreeSelfWhenDone.kr(playbuf);
    Out.ar(out, playbuf);
  }).play(s, [\out, 0, \bufnum, b]);
)

~playb.free;

(
~rec = {
  RecordBuf.ar(~tune, b);
}
)
~rec.clear;

(
~play = {
  PlayBuf.ar(1, b);
}
)
~play.play;
~play.clear;
