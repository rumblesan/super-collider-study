

Ndef(\kick,
    Pbind(
        \instrument, \modkick,
        \degree, 0,
        \octave, 3,
        \attack, 0.1,
        \decay, 1.5,
        \ramp, Pwrand([3, 16], [20, 1].normalizeSum, inf),
        \rampdecay, 0.01,
        \gain, Pseq([Pn(2, 4), 5, Pn(3, 4), 7], inf),
        \moddepth, Pwrand([0, 1], [20, 13].normalizeSum, inf),
        \moddecay, 0.5,
        \amp, 1,
        \dur, 2.5,
    )
)
Ndef(\kick).quant = 4;
Ndef(\kick).clear;

Ndef(\rhythmdelay1)[0] = \fourtapdelay

Ndef(\rhythmdelay1).set(
    \delay, 1.0,
    \timeScale, 1.0,
    \drywet, 1.0,
    \feedback, 0.8,
    \lopass, 10000,
    \dur, 2,
)

Ndef(\rhythmdelay1) <<> Ndef(\kick)

Ndef(\rhythmdelay1)[1] = \pset -> Pbind(
    \timeScale, Pseq([1, 1.3], inf),
    \feedback, Pwrand([0.8, 0.2, 1.1], [15, 3, 1].normalizeSum, inf),
    \dur, 4,
);
