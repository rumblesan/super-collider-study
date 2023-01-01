(

SynthDef(\monoplay,
    {arg out = 0, buf = 0, rate = 1, amp = 0.5, pan = 0, pos = 0, rel=15;
        var sig,env ;
        sig = Pan2.ar(PlayBuf.ar(1,buf,BufRateScale.ir(buf) * rate,1,BufDur.kr(buf)*pos*44100,doneAction:2),pan);
        env = EnvGen.ar(Env.linen(0.0,rel,0),doneAction:2);
        sig = sig * env;
        sig = sig * amp;
        Out.ar(out,sig);
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

SynthDef(\splay, {arg out=0, bufnum, rate=1, gate=1,
        start=0, attack=0.0, release=0.5, amp=0.5;
        var sig = PlayBuf.ar(1, bufnum, BufRateScale.ir(bufnum) * rate, 1, BufDur.kr(bufnum) * start * 44100);
        var env = Env.asr(attack, amp, release).kr(gate: gate, doneAction:2);
        Out.ar(out, sig * env);
}).add;

)
