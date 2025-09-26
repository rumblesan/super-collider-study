// clip, fold, gain, mod, wave
Npat(\thumper,
    \loop, 24,
    \instrument, \thumper,
    \degree, 0,
    \octave, 3,
    \wave, 0,
    \spread, 0,
    \noise,  0,
    \attack, 0,
    \decay, 0.2,
    \mod, 0,
    \modEnv, 8.2,
    \mattack, 0.03,
    \mdecay, 2.8,
    \pmod, 1.1,
    \pcurve, 14,
    \pattack, 0.001,
    \pdecay, 0.008,
    \gain, 8.5,
    \clip, 0,
    \fold, 1,
    \dur, Pseq([
        1.5, r, 1.5, 1,
    ], inf)
)

// map spread

Npat(\kick,
    \instrument, \bkick,
    \freq, 50,
    \attack, 0.01,
    \decay, 1.0,
    \ramp, 5.0,
    \rampattack, 0.01,
    \rampdecay, 0.05,
    \gain, 1.0,
    \dur, 2,
)
