
Ndef(\g2, Pbind(
  \instrument, \wavetableosc2D,
  \wavetableBufNum, d[\wavetables][\ph1].bufnum,
  \wavetableWavesX, d[\wavetables][\ph1].waves/8,
  \wavetableWavesY, d[\wavetables][\ph1].waves/8,
  \scale, Scale.minor,
  \root, -5,
  \offsetX, 0.2,

  \degree, 0,
  \attack, 0.01,
  \decay, 0.01,
  \legato, 0.1,

  \amp, 1,
  \dur, Pif(Pfunc({~events[\g1].dur > 0.25}), Pseq([
    Pn(0.5, 3), 1, Pn(0.5, 3), Rest(1),
    Prand([1, 2], 2),
    Prand([0.5, 0.5, 0.25, 1], 4), Pn(0.25, 4), Rest(1),
  ], inf), inf),
).collect({|evt| ~events[\g2] = evt; evt}))
Ndef(\g2).clear;
