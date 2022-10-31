
"Setup.scd".load;

(
  ~piston = Pbind(
    \instrument, \wavetableosc2D,
    \wavetableBufNum, d[\wavetables][\ph1].bufnum,
    \wavetableWavesX, d[\wavetables][\ph1].waves/8,
    \wavetableWavesY, d[\wavetables][\ph1].waves/8,
    \scale, Scale.minor,
    \octave, Pwrand([4, 3, 5], [20, 3, 2].normalizeSum, inf),
    [\degree, \dur], Pmetro(
      Pseq([
        7,
        Prand([1, 2, 6]),
        4,
        Pwrand([9, 0, 13], [15, 3, 2].normalizeSum),
        3,0
      ], inf),
      Pseq([2,2,1,Prand([2, Pseq([1, 1]), ]),2,1], inf),
      ".*.-**",
      inf,
      0.25),
    \offsetX, Pseq([0, Pwhite(0.01, 0.05), 0.1, Prand([0.05, 0.07, 0.1]), 0]),
    \offsetY, Pseq([Pn(0,5), Pn(0.05, 2), Pn(0.07, 4), 0], inf),
    \attack, Pwrand([0.01, 0.3], [20, 1].normalizeSum, inf),
    \pitchMod, 0,
    \release, Pwhite(0.2, 0.4) + 0.2,
    \amp, 1,
  )
)
~piston.quant = 4

~piston.play
~piston.stop
