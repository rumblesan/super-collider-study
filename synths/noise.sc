(

  SynthDef(\fnoise, {
    arg out=0, gate=1,
    lpfreq=10000, hpfreq=150, q=1,
    gain=0.8,
    attack=0.01, decay=0.2, amp=0.8;
    var n = WhiteNoise.ar;
    var f = RHPF.ar(RLPF.ar(n, lpfreq, q), hpfreq, q);
    var g = (f * gain).clip2;
    var env = Env.asr(attack, amp, decay).kr(2, gate: gate);
    Out.ar(out, g * env);
  }).add;

)

/*
(
  ~nperc = Pbind(
    \instrument, \fnoise,
    \attack, 0.01,
    \decay, 0.1,
    \hpfreq, 1500,
    \gain, 0.7,
    \legato, 0.05,
    \amp, 0.7,
    \dur, 2,
  )
)
~nperc.quant = 4
~nperc.play
~nperc.stop
*/
