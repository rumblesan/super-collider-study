
Ndef(\fourtap)[0] = \fourtapdelay

Ndef(\fourtap).set(
    \delay, 1.0,
    \timeScale, 1.0,
    \drywet, 1.0,
    \feedback, 0.6,
    \lopass, 10000,
    \dur, 2,
)

Ndef(\fourtap) <<> Ndef(\pluck)

Ndef(\fourtap)[1] = \pset -> Pbind(
    \delay, Pseq([1.0, (1/3) / 10, 1.0, 1, 2, 1/4, 0.01], inf),
    //\feedback, Pwrand([0.8, 0.2, 1.1], [15, 3, 1].normalizeSum, inf),
    //\feedback, 0.3,
    \dur, 1.5,
);

Ndef(\fourtap).clear
