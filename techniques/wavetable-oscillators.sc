
"Setup.scd".load;
s.plotTree


// Wavetables are loaded in the setup file and can be found
// in the d[\wavetables] dictionary

(
  SynthDef(\wavetableosc,{ |
    out=0, freq=100, gate=1,
    attack=0.1, release=0.5, amp=1,
    wavetableBufNum,
    wavetableWaves,
    offset=0|
    var env, tablepos;

    env = Env.asr(attack, amp, release).kr(2, gate: gate);
    tablepos = wavetableBufNum + (offset.mod(1) * wavetableWaves);
    Out.ar(out, VOsc.ar(tablepos, freq, mul: env));
  }).add
)

~offsetX = {MouseX.kr(0,1)}
~offsetY = {MouseY.kr(0,1)}

(
  ~t = Pbind(
    \instrument, \wavetableosc,
    \freq, 50,
    \wavetableBufNum, (d[\wavetables][\ph1].bufnum,
    \wavetableWaves, d[\wavetables][\ph1].waves,
    \offset, ~offsetX,
    \amp, 1,
    \dur, 3,
  )
)
~t.play;
~t.clear;

{VOsc.ar(d[\wavetables][\ph1][0].bufnum, 100)}.plot

d[\wavetables][\ph1][0].plot

(
  SynthDef(\wavetableosc2D,{ |
    out=0, freq=100, gate=1,
    attack=0.1, release=0.5, amp=1,
    wavetableBufNum,
    wavetableWavesX, wavetableWavesY,
    offsetX=0, offsetY=0|
    var env, x, y,  tablepos;

    env = Env.asr(attack, amp, release).kr(2, gate: gate);
    x = offsetX.mod(1) * wavetableWavesX;
    y = offsetY.mod(1) * wavetableWavesY * (wavetableWavesX - 1);
    tablepos = wavetableBufNum + x + y;
    Out.ar(out, VOsc.ar(tablepos, freq, mul: env));
  }).add
)

(
  ~t = Pbind(
    \instrument, \wavetableosc2D,
    \freq, 50,
    \wavetableBufNum, d[\wavetables][\ph1].bufnum,
    \wavetableWavesX, d[\wavetables][\ph1].waves/8,
    \wavetableWavesY, d[\wavetables][\ph1].waves/8,
    \offsetX, ~offsetX,
    \offsetY, ~offsetY,
    \amp, 1,
    \dur, 2,
  )
)
~t.play;
