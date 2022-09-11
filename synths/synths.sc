
(
SynthDef("mySine1", {Out.ar(0, SinOsc.ar(770, 0, 0.1))}).add;

SynthDef("PhaseVox",
  {|out=0, buf, rate=1, gate=1, start=0, end=1, pan=0, amp = 0.5|

  var sig, pos, frames, env;
  rate = rate * BufRateScale.kr(buf);
  frames = BufFrames.kr(buf);

  pos = Phasor.ar(
    rate: rate,
    start: start * frames,
    end: end * frames,
  );

  env = EnvGen.ar(Env.asr(0, 1, 0), gate, doneAction:2);

  sig = Pan2.ar(BufRd.ar(2, buf,
    phase: pos,
    interpolation: 4,
    loop: 0,
  ), pan);
  Out.ar(out, sig * env * amp);
}).add;

SynthDef(\bplay,
    {arg out = 0, buf = 0, rate = 1, amp = 0.5, pan = 0, pos = 0, rel=15;
        var sig,env ;
        sig = Pan2.ar(PlayBuf.ar(2,buf,BufRateScale.ir(buf) * rate,1,BufDur.kr(buf)*pos*44100,doneAction:2),pan);
        env = EnvGen.ar(Env.linen(0.0,rel,0),doneAction:2);
        sig = sig * env;
        sig = sig * amp;
        Out.ar(out,sig);
}).add;

)
