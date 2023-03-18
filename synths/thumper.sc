
/*
  "Setup.scd".load;
  s.plotTree
*/


(
SynthDef(\thumper, {arg out=0, freq=50, amp=0.1,
  wavetableBufNum, wavetableWaves=64,
  waveMod=0, spread=0,
  attack=0.01, decay=0.5,
  pattack=0.01, pdecay=0.5, pmod=0,
  volAccent=0,
  fold=0;


  var penv, venv, oscCount, tablepos, oscs,
  snd, folded;

  penv = Env.perc(pattack, pdecay, pmod).kr(0);
  venv = Env.perc(attack, decay, amp + volAccent).kr(2);

  tablepos = wavetableBufNum + (waveMod * wavetableWaves);
  oscCount = 6;
  oscs = oscCount.collect({|i|
    var f = freq * (1 + (i * spread)) * (1 + penv);
    var g = 1/(i+1);
    VOsc.ar(tablepos, f, mul: g)
  });

  snd = Mix.new(oscs) * 0.5;
  folded = Fold.ar(snd * (fold + 1), -1, 1);
  Out.ar(out, folded * venv);
}).add;
)

// Wavetables are loaded in the setup file and can be found
// in the d[\wavetables] dictionary

/*
(
  ~thumper = Pbind(
    \instrument, \thumper,
    \freq, 50,
    \wavetableBufNum, d[\wavetables][\ph1].bufnum,
    \wavetableWaves, d[\wavetables][\ph1].waves,
    \waveMod, 0.78,
    \spread, 1.01,
    \attack, 0.001,
    \decay, 2.0,
    \amp, 1,
    \pmod, 10,
    \pattack, 0.01,
    \pdecay, 0.1,
    \fold, 0.5,
    \dur, 4,
  )
)
~thumper.play;
~thumper.clear;
*/
