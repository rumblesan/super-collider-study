p.clock.tempo = 160/60;


Ndef(\kick, Pbind(
  \instrument, \bkick,
  \freq, 50,
  \attack, Pwrand([0.1, 0.9], [10, 1].normalizeSum, inf)
  \decay, 0.1,
  \ramp, 25,
  \rampattack, 0.0,
  \rampdecay, 0.035,
  \noisedecay, 0.01,
  \gain, 2,

  \amp, 1,
  \dur, Pseq([
    3, 3, 2, 1,1,1, 3, 2
  ], inf) / 2,
)
)
Ndef(\kick).clear;


(
Ndef(\mkalead, Pbind(
  \instrument, \fm3filter,

  \scale, Scale.minor,
  \octave, 5,
  \root, -5,
  \degree, Pseq([0, 0, 0, 0, 0, 0, 0, 0], inf),

  \amp, 1,

  \attack, 0.01,
  \release, 0.08,
  \legato, Pwrand([0.01, 0.2, 0.5], [20, 0, 3].normalizeSum, inf),
  //\legato, 0.2,
  \mod, Pseq([1.5], inf),

  \mod1, 8,
  \ratio1, 2.0,
  \attack1, 0.01,
  \decay1, 0.1,

  \mod2, 0,
  //\ratio2, Pwrand([2.52, 3.57], [10, 1].normalizeSum, inf),
  \attack2, 0.01,
  \decay2, 0.1,
  \dur, Pn(
    Pfinval(16,
      Pseq([Rest(1), Pn(1/3, 3), Rest(0.5), Prand([Pn(1/3, 3), 1])], inf) * 4
    ), inf
  )
))
)

(
Ndef(\mkapad, Pbind(
  \instrument, \fm3filter,

  \scale, Scale.minor,
  \octave, 4,
  \root, -5,
  \degree, Pseq([
    [0, 3, 5],
    [0, 3, 5] + 3,
    [0, 3, 5] + 5,
  ], inf),

  \amp, 1,

  \attack, 1,
  \release, 4,
  \mod, Pseq([1], inf),

  \mod1, 1,
  \ratio1, 1.0,
  \attack1, 1,
  \decay1, 3.0,
  \cutoff, 1000,

  \mod2, 0,
  //\ratio2, Pwrand([2.52, 3.57], [10, 1].normalizeSum, inf),
  \attack2, 0.01,
  \decay2, 0.1,
  \dur, Pseq([6, 7, 4, 4, 7], inf) * 3
))
)

Ndef(\mkalead).clear
