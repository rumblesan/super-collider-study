
"Setup.scd".load;

Ndef(\piston, Pn(Pfindur(16, PmonoArtic(
  \wavetableosc2D,
  \wavetableBufNum, d[\wavetables][\ph1].bufnum,
  \wavetableWavesX, d[\wavetables][\ph1].waves/8,
  \wavetableWavesY, d[\wavetables][\ph1].waves/8,
  \scale, Scale.minor,
  \root, -5,
  \octave, Pwrand([4, 3], [10, 2].normalizeSum, inf),
  \degree, 0,
  \offsetX, Pseq([Pn(0, 3), 0.1, Pn(0, 5), 0.07], inf),
  \offsetY, Pwrand([0, 0.3, 0.7], [10, 3, 1].normalizeSum, inf),
  \legato, 0.18,
  \attack, 0.01,
  \release, Pwhite(0.05, 0.15, inf),
  \pitchMod, 0,
  \amp, 1,
  \dur, Pseq([1, 1, 3, 1, 1, 1, 2], inf) / 4,
)), inf)
)

Ndef(\piston).quant = 4
Ndef(\piston).clear

Ndef(\piston).map(\pitchMod, Ndef(\pistonKick))

Ndef(\pistonKick, Pcontrol(
  \percenv,
  \attack, 0.01,
  \decay, 0.03,
  \curve, -8,
  \value, 15,
  \offset, 0,
  \dur, Pseq([3, 3, 2, Rest(4)], inf),
)
)
Ndef(\pistonKick).quant = 4;
Ndef(\pistonKick).clear
