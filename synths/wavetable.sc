// wavetable oscillator based synths

(
  SynthDef(\wavetableosc,{ |out=0, freq=100, gate=1, amp=1,
    attack=0.1, release=0.5,
    pitchMod=0,
    wavetableBufNum,
    wavetableWaves,
    offset=0|

    var env = Env.asr(attack, amp, release).ar(Done.freeSelf, gate: gate);
    var tablepos = wavetableBufNum + (offset.mod(1) * wavetableWaves);
    var snd = VOsc.ar(tablepos, freq * (1 + pitchMod), mul: env);
    Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
  }).add;

  SynthDef(\wavetableosc2D,{ |out=0, freq=100, gate=1, amp=1,
    attack=0.1, release=0.5,
    pitchMod=0,
    wavetableBufNum,
    wavetableWavesX, wavetableWavesY,
    offsetX=0, offsetY=0|

    var env = Env.asr(attack, amp, release).ar(Done.freeSelf, gate: gate);
    var x = offsetX.mod(1) * wavetableWavesX;
    var y = offsetY.mod(1) * wavetableWavesY * (wavetableWavesX - 1);
    var tablepos = wavetableBufNum + x + y;
    var snd = VOsc.ar(tablepos, freq * (1 + pitchMod), mul: env);
    Out.ar(out, Pan2.ar(snd, \pan.kr(0)));
  }).add;
)
