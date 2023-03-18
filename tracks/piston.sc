
"Setup.scd".load;

(
  ~piston = Pn(Pfindur(16, Pbind(
    \instrument, \wavetableosc2D,
    \wavetableBufNum, d[\wavetables][\ph1].bufnum,
    \wavetableWavesX, d[\wavetables][\ph1].waves/8,
    \wavetableWavesY, d[\wavetables][\ph1].waves/8,
    \scale, Scale.minor,
    \root, 31,
    \octave, Pwrand([0, 0, 2], [20, 0, 0].normalizeSum, inf),
    [\degree, \dur], Pmetro(
      Pseq([1, 1, 1, 1, 1, 1, 1, 1], inf),
      Pseq([4,4,4,4,1,1], inf),
      "- - ..  ",
      inf,
      0.25),
    \offsetX, f.proxy[5],
    \offsetY, f.proxy[6],
    \legato, 0.8,
    \attack, 0.01,
    \release, 0.01,
    \pitchMod, 0,
    \amp, -12.dbamp,
  )), inf)
)
~piston.quant = 4
~piston.play
~piston.stop
~piston.clear

~piston.map(\pitchMod, ~pistonKick)

(
  ~pistonKick = Pcontrol(
    \percenv,
    \attack, 0.01,
    \decay, 0.03,
    \curve, -4,
    \value, 8,
    \offset, 0,
    \dur, Pseq([3, 3, 2], inf),
  )
)
~pistonKick.quant = 4;
