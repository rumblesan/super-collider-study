
(

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


)
