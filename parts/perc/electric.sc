
Ndef(\bass,
  Pbind(
    \instrument, \sinbass,
    \degree, 0,
    \root, -5,
    \octave, 3,
    \attack, 0.1,
    \decay, 0.5,
    \amp, 1,
    \dur, Pseq([3, 3, 2, 4, 5, 3], inf),
  )
)
Ndef(\bass).clear;

Ndef(\bass).map(\gain, Ndef(\bassdist));
Ndef(\bassdist,
  Pcontrol(
    \mod,
    \value, Pseq([1, 1.2, 1, 1.6, 1, 1, 1.5, 1, 1], inf),
    \slew, 0.3,
    \dur, 1
  )
)
Ndef(\bassdist).quant = 4;

Ndef(\blip,
  Pbind(
    \instrument, \blip,
    \degree, 0,
    \decay, 0.5,
    \q, Pwhite(0.1, 0.5, inf),
    \freq, 500,
    \impulsefreq, Pseq([Pn(15, 7), 50, Pn(10, 2), Pn(0, 5), 50], inf),
    \gain, 50,
    \pan, Pseq([-1, 1, 0, 0, -1, 0, -1, 1, 1, -1, 0, 1], inf) * 0.3,
    \amp, 1,
    \dur, (Pseq([1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1], inf)) + 0.25,
  )
)
Ndef(\blip).clear;


Ndef(\ping,
  Pbind(
    \instrument, \ping,
    \degree, Prand([
      Pn(0, 7),
      Pseq([4, 0, 0, 3], 1),
      Pn(0, 7),
      Pn(6, 3),
    ], inf),
    \scale, Scale.minor,
    \octave, Pseq([Pn(5, 5), Pn(6, 2), 5, 4, 5, 5], inf),
    \root, -5,
    \attack, 0.0,
    \distamp, Pseq([Pn(0.1, 7), 0.3, Pn(0.02, 5), 0.9], inf),
    \decay, 0.75,
    \amp, 1,
    \dur, Pseq([
      3, 3, 1,
      Prand([
        Pseq([2, 4, 2], 2),
        Pseq([3, 3, 2], 2),
      ], inf)
    ], inf) + 0.5,
  )
)
Ndef(\ping).clear;


Ndef(\kick,
  Pbind(
    \instrument, \elkick,
    \freq, 50,
    \octave, 1,
    \attack, 0.1,
    \decay, 0.8,
    \ramp, 30,
    \gain, 1.4,

    \amp, 1,
    \dur, Pseq([3, 3, 2, 2, 2], inf),
  )
)
Ndef(\kick).clear;

Ndef(\pluck,
  Pbind(
    \instrument, \karpluspluck,
    \degree, Pseq([
      Pn(0, 3),
      [0, 4],
      [1, 4, 7],
      Pn(0, 3),
      [1, 4],
      Pn(0, 3),
      [2, 7],
      Pn(6, 3),
    ], inf),
    \scale, Scale.minor,
    \octave, 6,
    \colour, Pwrand([0.2, 0.5, 0.9], [12, 3, 1].normalizeSum, inf),
    \root, -5,
    \decay, 2.5,
    \amp, 1,
    \dur, Pseq([1, 1.5, 6, 1, 7.5, 3], inf),
  )
)
Ndef(\pluck).clear;
