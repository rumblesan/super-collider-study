
Ndef(\thumper,
    Pbind(
        \instrument, \thumper,
        \degree, 0,
        \octave, Pseq([2, 2, 3, 2, 3], inf),
        \wavetableBufNum, d[\wavetables][\ph1].bufnum,
        \wavetableWaves, d[\wavetables][\ph1].waves,
        \waveMod, 0.0,
        \spread, 1.03,
        \attack, 0.001,
        \decay, 2.0,

        \pmod, 10,
        \pattack, 0.01,
        \pdecay, Pwrand([0.1, 0.3], [7, 2].normalizeSum, inf),
        \fold, 0.5,

        \amp, 1,
        \dur, Pseq([4, 2, 4, 4,], inf) / 2,
    )
)
Ndef(\thumper).quant = 8;
Ndef(\thumper).clear;

Ndef(\thumper).map(\fold, Ndef(\foldmod));

Ndef(\foldmod,
    Pcontrol(
        \mod,
        \value, Pseq([1, 2, 1, 5, 1, 1], inf)*3,
        \slew, 0.5,
        \dur, Pseq([1, 1, 1, 2, 1], inf)
    )
)
Ndef(\foldmod).quant = 4;

Ndef(\thumper).map(\waveMod, Ndef(\waveenv))

Ndef(\waveenv,
    Pcontrol(
        \percenv,
        \attack, 0.01,
        \decay, Pwhite(0.1, 0.2, inf)/5,
        \curve, -4,
        \value, 0.8,
        \offset, Pseq([Pn(0, 2), 0.3, 0.31, Pn(0, 3), 0.311], inf) / 6,
        //\offset, 0,
        \dur, Pseq([3, 2, 2, 1, 3, 1, 1, 2], inf)/2,
    )
)
Ndef(\waveenv).quant = 4;

Ndef(\kick,
    Pbind(
        \instrument, \bkick,
        \degree, -2,
        \octave, 3,
        \attack, 0.01,
        \decay, 1.0,
        \ramp, 5.0,
        \rampattack, 0.00,
        \rampdecay, 0.05,
        \amp, 1,
        \gain, 9.0,
        \dur, 2,
    )
)
Ndef(\kick).quant = 4;
Ndef(\kick).clear;


Ndef(\lead,
    Pbind(
        \instrument, \wavetableosc2D,
        \degree, Pseq([
            Pn(-2, 5),
            Pn(0, 7),
            Pn(-2, 3),
            Pn(4, 8),
        ], inf),
        \octave, Pwrand([4, 3], [10, 3].normalizeSum, inf),

        \wavetableBufNum, d[\wavetables][\ph1].bufnum,
        \wavetableWavesX, d[\wavetables][\ph1].waves/8,
        \wavetableWavesY, d[\wavetables][\ph1].waves/8,
        \offsetX, 0.5,
        \offsetY, 0.0,
        \attack, 0.01,
        \release, Pwhite(0.4, 0.6, inf),

        \amp, 1,
        \dur, Pseq([1, 2, 2], inf) / 4,
    )
)
Ndef(\lead).quant = 4;
Ndef(\lead).clear;

Ndef(\lead).map(\offsetY, Ndef(\ymod))
Ndef(\ymod,
    Pcontrol(
        \mod,
        \value, Pseq([0.1, 0.15, 0.075, 0.1, 1.1], inf),
        \slew, 0.05,
        \dur, Pseq([1, 2, 1, 1, 4, 2, 1], inf)/2,
    )
)
Ndef(\ymod).quant = 4;


Ndef(\gldelay)[0] = \simpledelay;
Ndef(\gldelay) <<> Ndef(\lead)

(
  Ndef(\gldelay)[1] = \pset -> Pbind(
    \drywet, 1.0,
    \delay, Pseq([0.4,
      //Prand([0.9, 0.4]),
      Prand([0.01, 0.1])], inf),
    \delay, Pseq([0.4, 0.9], inf),
    //\delay, 0.25,
    //\feedback, Pwrand([0.1, 0.4, 0.98], [2, 10, 1].normalizeSum, inf),
    //\lopass, 3000,
    \feedback, 0.5,
    \dur, 0.25,
  )
)

Ndef(\gldelay).clear
