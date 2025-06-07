Ndef(\kick, Pbind(
  \instrument, \bkick,
  \freq, 50,
  \attack, 0.1,
  \decay, 0.3,
  \ramp, 25,
  \rampdecay, 0.035,
  \noisedecay, 0.01,
  \gain, 2,

  \amp, 1,
  \dur, Pseq([3, 3, 2,
    0.5, 2.5,
    Prand([3, Pn(1, 3)]),
    2], inf),
  //\dur, 2,
)
)
Ndef(\kick).quant = 4;
Ndef(\kick).clear;


Ndef(\perc,
  Pbind(
    \instrument, \buzz2,
    \degree, Pseq([
      Pn(-3, 16),
      //Pn(-1, 12),
      //Pn(0, 18),
      //Pn(-1, 10),
    ], inf),
    \attack, 0.01,
    \decay, 0.001,
    \bits, Pwrand([6, 3, 2], [10, 7, 3].normalizeSum, inf),
    \gain, 21,
    \duration, Pwrand([0.01, 0.1], [15, 2].normalizeSum, inf),
    \amp, 1,
    \dur, Pseq([1,1,
      Prand([1, 2]),
      2,1,1,2,2,1,1,1], inf) / 4
  )
)

Ndef(\perc).quant = 4;
Ndef(\perc).clear
