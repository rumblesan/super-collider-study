
Ndef(\thumper, Pbind(
  \instrument, \thumper,
  \root, -5,
  \octave, 3,
  \degree, 0,
  \wavetableBufNum, d[\wavetables][\ph1].bufnum,
  \wavetableWaves, d[\wavetables][\ph1].waves,
  //\waveMod, Pseq([Pn(0.3, 3), 0.4, 0.3, 0.8], inf)/100,
  \waveMod, 0.0,
  \spread, Pwrand([1.05, 1.5], [20, 4].normalizeSum, inf),
  \attack, 0.001,
  \decay, 3.0,
  \amp, 1,
  \volAccent, Pseq([1, 0, 0, 1, 0], inf) * -6.dbamp,
  \pmod, 3,
  \pattack, 0.01,
  \pdecay, 0.2,
  \fold, 0.01,
  \dur, Pseq([
    Pseq([3, 3, 2]),
    Pseq([2, 2, 2, 2]),
  ], inf)

))

Ndef(\thumper).quant = 4;
Ndef(\thumper).clear;

Ndef(\thumper).map(\fold, Ndef(\thumperfold))
Ndef(\thumperfold, Pcontrol(
  \mod,
  \value, Pseq([Pn(0, 3), 0.5, Pn(0,3), 1, Pn(0.25, 3), 1], inf) * 5,
  \slew, 0,
  \dur, Pseq([1, 1, 1, 4, 0.5, 0,5, 0.75, 0.5, 0.25, 1], inf)/2
))
Ndef(\thumperfold).quant = 4;
Ndef(\thumperfold).clear
