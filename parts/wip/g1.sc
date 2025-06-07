
Ndef(\g1, Pbind(
  \instrument, \wavetableosc2D,
  \wavetableBufNum, d[\wavetables][\ph1].bufnum,
  \wavetableWavesX, d[\wavetables][\ph1].waves/8,
  \wavetableWavesY, d[\wavetables][\ph1].waves/8,
  \scale, Scale.minor,
  \root, -5,

  \degree, Pwrand([0, -3, 5], [10, 7, 2].normalizeSum, inf),
  \attack, 0.01,
  \decay, 0.01,
  \legato, 0.1,

  \amp, 1,
  \dur, Pseq([
    Pn(0.5, 3), 1, Pn(0.5, 3), Rest(1),
    Prand([Rest(1), 2], 2),
    Prand([0.5, 0.5, 0.25, 1], 4), Pn(0.25, 4), Rest(1),
  ], inf),
)
)
Ndef(\g1).clear;
