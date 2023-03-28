
"Setup.scd".load;

Ndef(\piston, Pn(Pfindur(16, PmonoArtic(
  \wavetableosc2D,
  \wavetableBufNum, d[\wavetables][\ph1].bufnum,
  \wavetableWavesX, d[\wavetables][\ph1].waves/8,
  \wavetableWavesY, d[\wavetables][\ph1].waves/8,
  \scale, Scale.minor,
  \root, -5,
  \octave, 3,
  [\degree, \dur], Pmetro(
    Pseq([0, 0, 7, 0, 7, 0], inf),
    Pseq([2,2,4,2,1,1], inf),
    "-*--..* ",
    inf,
    0.25),
  \offsetX, Pseq([Pn(0, 3), 0.1, Pn(0, 5), 0.07], inf),
  \offsetY, Pwrand([0, 0.3, 0.7], [10, 3, 1].normalizeSum, inf),
  \legato, Pwrand([0.8, 1], [7, 1].normalizeSum, inf),
  \attack, 0.01,
  \release, 0.01,
  \pitchMod, 0,
  \amp, 1,
)), inf))

Ndef(\piston).quant = 4
Ndef(\piston).clear

Ndef(\piston).map(\pitchMod, Ndef(\pistonKick))

Ndef(\pistonKick, Pcontrol(
  \percenv,
  \attack, 0.01,
  \decay, 0.03,
  \curve, -8,
  \value, 5,
  \offset, 0,
  \dur, Pseq([3, 3, 2, Rest(4)], inf),
))
Ndef(\pistonKick).quant = 4;
