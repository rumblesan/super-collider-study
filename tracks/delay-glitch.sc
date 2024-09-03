
(
Ndef(\ping, Pbind(
  \instrument, \ping,
  \decay, Pwrand([0.4, 1], [10, 2].normalizeSum, inf),
  \octave, 5,
  \root, -5,
  \scale, Scale.minor,
  \degree, Pwrand([0, 2, 7, -12, 10], [1, 3, 4, 0].normalizeSum, inf),
  \dur, Pseq([4, 4, 1, 0.25, 0.25, 0.5], inf),
  \ptrig, Pseq([1, 1, 0, 1, 1, 0, 0], inf),
  \pmod, Prand([1, 0, 0, 3, 7, 0], inf),
  \pdecay, Prand([0.05], inf),
  \distamp, 0,
  \amp, 1,
))
)

Ndef(\ping).quant = 4;
Ndef(\ping).clear;
Ndef(\ping).play;

Ndef(\clicker, Pbind(
  \instrument, \clikr,
  \dur, Pwrand([1, 0.5, 0.25], [1, 3, 19].normalizeSum, inf),
  \degree, Pwrand([0, 3, 4, -12], [10, 3, 3].normalizeSum, inf),
  \octave, 5,
  \harmonics, Pseq([200, 10, 10], inf),
  \amp, 1,
  \decay, Pwrand([0.1, 0.5, 1], [5, 5, 1].normalizeSum, inf),
  \sustain, Prand([0.3, 0.5, 0.3], inf),
)
)
Ndef(\clicker).quant = 4;
Ndef(\clicker).clear;


Ndef(\delaymix, {
  Mix.new([
    Ndef(\ping).ar(1),
    //Ndef(\clicker).ar(1) * -3.dbamp,
  ])
})
Ndef(\delaymix).scope
Ndef(\delaymix).clear

Ndef(\gldelay)[0] = \simpledelay;
Ndef(\gldelay) <<> Ndef(\ping)
Ndef(\gldelay).clear
Ndef(\gldelay).play

(
  Ndef(\gldelay)[1] = \pset -> Pbind(
    \drywet, 0.5,
    \delay, Pseq([0.4, 0.9, Prand([0.01, 0.1])], inf),
    \feedback, Pwrand([0.1, 0.4, 0.98], [2, 10, 0].normalizeSum, inf),
    \dur, 0.25,
  )
)
