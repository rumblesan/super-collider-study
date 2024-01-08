"../Setup.sc".load;

p.clock.tempo = 160/60;

Ndef(\thumper,
    Pbind(
        \instrument, \thumper,
        \degree, Prand([0], inf),
        \root, -5,
        \octave, Pseq([3], inf),
        \wave,   Pseq([0.0], inf),
        \spread, Pseq([0], inf),
        \noise,  Prand([0], inf),
        \ndecay, Pseq([0], inf),
        \attack, Pseq([0], inf),
        \decay, Pseq([0.1], inf),
        \pmod, Pseq([0], inf),
        \mod, Pseq([0], inf),
        \modEnv, Pseq([0], inf),
        \mattack, Pseq([0.03], inf),
        \mdecay, Pseq([0.3], inf),
        \pcurve, Pseq([14.0], inf),
        \pattack, Pseq([0.001], inf),
        \pdecay, Pseq([0.01], inf),
        \gain, Pseq([1], inf),
        \clip, Pseq([0], inf),
        \fold, Pseq([0], inf),
        \amp, Pseq([1], inf),
        \timingOffset, Pseq([0, 1, 0, 1, 0, 1, 0, 1], inf) * 0.02,
        \dur, Pseq([1, r, r, r, 1, r, r, r], inf) / 2
    )
)
Ndef(\thumper).quant = 8;
Ndef(\thumper).clear;
Ndef(\thumper).play

Ndef(\kick,
    Pbind(
        \instrument, \bkick,
        \root, -5,
        \degree, 0,
        \octave, 3,
        \attack, 0.01,
        \decay, 1.0,
        \ramp, 5.0,
        \rampattack, 0.00,
        \rampdecay, 0.05,
        \amp, 1,
        \gain, 1.0,
        \dur, 2,
    )
)
Ndef(\kick).quant = 4;
Ndef(\kick).clear;
