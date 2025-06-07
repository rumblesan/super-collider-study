

Ndef(\kick,
    Pbind(
        \instrument, \modkick,
        \degree, Pseq([Pn(0, 3), -3, Pn(0, 8), 14 ], inf),
        //\degree, 0,
        \root, -5,
        \octave, Pwrand([4, 5, 6], [15, 0, 0].normalizeSum, inf),
        \attack, 0.1,
        \decay, 0.5,
        \ramp, Pwrand([3, 16], [20, 1].normalizeSum, inf),
        \rampdecay, 0.01,
        \gain, 18,
        \moddepth, Pwrand([0, 1, 3], [20, 13, 7].normalizeSum, inf),
        \moddecay, 0.05,
        \amp, 1,
        \dur, 3.5, // 3.5, 2.5
    )
)
Ndef(\kick).quant = 4;
Ndef(\kick).clear;

Ndef(\rhythmdelay1)[0] = \fourtapdelay

Ndef(\rhythmdelay1).set(
    \delay, 1.0,
    \timeScale, 1.0,
    \drywet, 1.0,
    \feedback, 0.0,
    \lopass, 10000,
    \dur, 2,
)

Ndef(\rhythmdelay1) <<> Ndef(\kick)
Ndef(\rhythmdelay1).clear

Ndef(\rhythmdelay1)[1] = \pset -> Pbind(
    \timeScale, Pwrand([
        Pseq([1, 1.3], 3),
        0.1
    ], [15, 3].normalizeSum, inf),
    \feedback, Pwrand([0.8, 0.2, 1.1], [15, 3, 1].normalizeSum, inf),
    \feedback, 0.3,
    \dur, 2,
);
