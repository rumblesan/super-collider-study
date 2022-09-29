// wavetable oscillator based synths

(
  SynthDef(\wavetableosc,{ |out=0, freq=100, gate=1, amp=1,
    attack=0.1, release=0.5,
    pitchMod=0,
    wavetableBufNum,
    wavetableWaves,
    offset=0|
    var env, tablepos;

    env = Env.asr(attack, amp, release).kr(2, gate: gate);
    tablepos = wavetableBufNum + (offset.mod(1) * wavetableWaves);
    Out.ar(out, VOsc.ar(tablepos, freq * (1 + pitchMod), mul: env));
  }).add;

  SynthDef(\wavetableosc2D,{ |out=0, freq=100, gate=1, amp=1,
    attack=0.1, release=0.5,
    pitchMod=0,
    wavetableBufNum,
    wavetableWavesX, wavetableWavesY,
    offsetX=0, offsetY=0|
    var env, x, y,  tablepos;

    env = Env.asr(attack, amp, release).kr(2, gate: gate);
    x = offsetX.mod(1) * wavetableWavesX;
    y = offsetY.mod(1) * wavetableWavesY * (wavetableWavesX - 1);
    tablepos = wavetableBufNum + x + y;
    Out.ar(out, VOsc.ar(tablepos, (freq * (1 + pitchMod)), mul: env));
  }).add;
)
