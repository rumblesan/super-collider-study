// Promising but needs work
// most of the fm synths aren't actually doing fm because mod and mod1 default to 0

Npat(\arp1,
    \instrument, \fm2,
    \decay, 0.1,
    \ratio, 2.5,
    \mod, 1,
    \mod1, 1,
    \octave, 5,
    \degree, Pseq([7, 5, 4, 3, 2], inf),
    \legato, 0.2,
    \dur, 1/2,
)

Npat(\arp2,
    \instrument, \fm2,
    \release, 0.2,
    \ratio, 3.01,
    \octave, 5,
    \degree, Pseq([7, Prand([5, 3]), 9, 3, 2, 0, 2], inf),
    \legato, 0.3,
    \dur, Pseq([0.5, 1, 0.5, 1.5], inf),
)

Npat(\arp3,
    \instrument, \fm2,
    \release, 0.2,
    \ratio, Pseq([2.5, 2.0, 2.5, 3], inf) + 0.03,
    \octave, Pwrand([6, 7], [10, 1].normalizeSum, inf),
    \degree, Pseq([3, Prand([6, 2]), 7, 9], inf),
    \legato, 0.4,
    \dur, Pseq([1, 2, 0.5, 0.5, 1, 0.5], inf),
)

Npat(\arpbass,
    \instrument, \fm2,
    \ratio, 0.5,
    \attack, 1.5,
    \modEnvAttack, 1.5,
    \decay, 1.5,
    \modEnvDecay, 1.5,
    \release, 4,
    \octave, 4,
    \degree, Prand([0, -2, 3], inf),
    \legato, 0.7,
    \dur, 9,
)
