(
  "Setup.scd".load;
  m = ProxyMixer(p, 8);
)



(

  SynthDef(\hat, {arg out, hipass=200, decay=0.2, amp=0.8;
    var venv = Env.perc(0.0, decay, amp).kr(2);
    var osc = PinkNoise.ar(mul: venv);

    Out.ar(out, HPF.ar(osc, hipass));
  }).add;

)


(
  ~kickz = Pbind(
    \instrument, \bkick,
    \amp, 0.8,
    \decay, 1,
    \rdecay, 0.03,
    \pfreq, 100,
    \ramp, 0.2,
    \freq, 50,
    \dur, 1,
  )
)
~kickz.play;

(
  ~hatz = Pbind(
    \instrument, \hat,
    \amp, 0.7,
    \decay, 0.1,
    \hipass, 200,
    \dur, 1/4,
  )
)
~hatz.play;

b = Buffer.alloc(s, s.sampleRate * 20, 1);

(
  SynthDef(\record, { arg out, bufnum, input;
    RecordBuf.ar(input, bufnum);
  })
)

(

r = Routine({
  Synth(\record
);

)
