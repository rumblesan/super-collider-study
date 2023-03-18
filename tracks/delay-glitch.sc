
"Setup.scd".load;


Ndef(\ping, Pbind(
  \instrument, \ping,
  \decay, Pwrand([0.1, 0.5, 2], [0, 1, 0].normalizeSum, inf),
  \octave, 5,
  \root, -5,
  \scale, Scale.minor,
  \degree, Pwrand([0, 7, -14, 10], [1, 1, 1, 1].normalizeSum, inf),
  \dur, Pseq([3, 1, 1], inf),
  \ptrig, Pseq([0, 1, 1, 0, 0, 1], inf),
  \pmod, Prand([0, 1, 0, 1, 0, 0, 0], inf),
  \pdecay, Prand([0.05], inf),
  \amp, 1,
))
Ndef(\ping).quant = 4;
Ndef(\ping).clear;

Ndef(\clicker, Pbind(
  \instrument, \clikr,
  \dur, Pwrand([1, 0.5, 0.25], [0, 3, 19].normalizeSum, inf),
  \degree, Pwrand([0, 5, 7, -14], [20, 3, 10].normalizeSum, inf),
  \octave, 5,
  \harmonics, Pseq([200, 10, 10], inf),
  \amp, 1,
  \decay, Pwrand([0.1, 0.5, 1], [15, 5, 1].normalizeSum, inf),
  \sustain, Prand([0.1, 0.5, 0.03], inf),
))
Ndef(\clicker).quant = 4;
Ndef(\clicker).clear;


Ndef(\delaymix, {
  Mix.new([
    Ndef(\ping).ar(1),
    Ndef(\clicker).ar(1) * -6.dbamp,
  ])
})
Ndef(\delaymix).scope

Ndef(\gldelay)[0] = \simpledelay;
Ndef(\gldelay) <<> Ndef(\delaymix)

(
  Ndef(\gldelay)[1] = \pset -> Pbind(
    \drywet, 1.0,
    \delay, Pseq([0.4,
      Prand([0.9, 0.4]),
      Prand([0.01, 0.1])], inf),
    //\delay, Pseq([0.4, 0.9], inf),
    //\delay, 0.25,
    \feedback, Pwrand([0.1, 0.4, 0.98], [2, 10, 1].normalizeSum, inf),
    \lopass, 3000,
    //\feedback, 0.5,
    \dur, 0.25,
  )
)
