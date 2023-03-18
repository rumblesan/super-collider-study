
(
  ~thumper = Pbind(
    \instrument, \thumper,
    \freq, 50,
    \wavetableBufNum, d[\wavetables][\ph1].bufnum,
    \wavetableWaves, d[\wavetables][\ph1].waves,
    \waveMod, Pseq([Pn(0.3, 3), 0.4, 0.3, 0.8], inf)/100,
    \spread, Pwrand([1.05, 1.5], [20, 0].normalizeSum, inf),
    \attack, 0.001,
    \decay, 3.0,
    \amp, -12.dbamp,
    \volAccent, Pseq([1, 0, 0, 1, 0], inf) * -6.dbamp,
    \pmod, 3,
    \pattack, 0.01,
    \pdecay, 0.2,
    \fold, 2.1,
    \dur, Pseq([
      Pseq([3, 3, 2]),
      Pseq([2, 2, 2, 2]),
    ], inf)

  )
)
~thumper.fadeTime = 0
~thumper.quant = 4;
~thumper.play;
~thumper.clear;

~thumper.unmap(\fold, ~foldmod)
(
  ~foldmod = Pcontrol(
    \mod,
    \value, Pseq([Pn(0, 3), 0.5, Pn(0,3), 1, Pn(0.25, 3), 1], inf) * 5,
    \slew, 0,
    \dur, Pseq([1, 1, 1, 4, 0.5, 0,5, 0.75, 0.5, 0.25, 1], inf)/2
  )
)
~foldmod.quant = 4;
~foldmod.clear
