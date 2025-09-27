(

SynthDef(\grainplayer, {
    var rate = \rate.ar(10);
    var trigger = Impulse.ar(rate);
    var dur = (1.0 / rate) * \durmod.ar(1.0);
    var bufnum = \buffer.kr;
    var centerPos = \pos.ar(0.1) * BufFrames.kr(bufnum) * BufSampleRate.kr(bufnum);
    var venv = Env.asr(\attack.kr(0.01), \amp.kr(0.8), \release.kr(0.01)).ar(Done.freeSelf, \gate.kr(1));
    var snd = TGrains.ar(
      2,
      trigger,
      bufnum,
      \pitch.ar(1.0),
      centerPos,
      dur,
      \pan.ar(0.0),
      1,
      4
    );
    snd = snd * venv;
    Out.ar(\out.kr(0), snd);
  };
).add;

)
