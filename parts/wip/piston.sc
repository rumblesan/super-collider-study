
Ndef(\piston, Pn(Pfindur(16, PmonoArtic(
  \wavetableosc2D,
  \wavetableBufNum, d[\wavetables][\ph1].bufnum,
  \wavetableWavesX, d[\wavetables][\ph1].waves/8,
  \wavetableWavesY, d[\wavetables][\ph1].waves/8,
  \scale, Scale.minor,
  \root, -5,
  \octave, 3,
  \degree, [-0.002, 0, 0.002],
  \offsetX, Pseq([Pn(0, 3), 0.1, Pn(0, 5), 0.07], inf),
  \offsetY, Pwrand([0, 0.3, 0.7], [10, 3, 1].normalizeSum, inf),
  \legato, 0.5,
  \attack, 0.01,
  \release, 0.3,
  \pitchMod, 0,
  \amp, 1,
  \dur, Pseq([2], inf),
)), inf)
)
