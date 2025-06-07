
"Setup.scd".load;


Ndef(\arp1).quant = 4;
Ndef(\arp1, Pbind(
    \instrument, \fm2,
    \release, 0.5,
    \amp, 1,
    \scale, Scale.minor,
    \ratio, 2,
    \root, -3,
    \octave, 5,
    \degree, Pseq([7, 5, 4, 3, 2], inf),
    \dur, 1/2,
)
)
Ndef(\arp1).mold(1);
Ndef(\arp1).free;

Ndef(\arp2, Pbind(
    \instrument, \fm2,
    \release, 0.2,
    \amp, 1,
    \scale, Scale.minor,
    \ratio, 3.01,
    \root, -3,
    \octave, 5,
    \degree, Pseq([7, Prand([5, 3]), 9, 3, 2, 0, 2], inf),
    \dur, Pseq([0.5, 1, 0.5, 1.5], inf),
)
)
Ndef(\arp2).quant = 4;

Ndef(\arp3, Pbind(
    \instrument, \fm2,
    \release, 0.2,
    \amp, 1,
    \scale, Scale.minor,
    \ratio, Pseq([2.5, 2.0, 2.5, 3], inf) + 0.03,
    \root, -3,
    \octave, Pwrand([6, 7], [10, 1].normalizeSum, inf),
    \degree, Pseq([3, Prand([6, 2]), 7, 9], inf),
    \dur, Pseq([1, 2, 0.5, 0.5, 1, 0.5], inf),
)
)
Ndef(\arp3).quant = 4;

Ndef(\arpbass, Pbind(
    \instrument, \fm2,
    \scale, Scale.major,
    \ratio, 0.5,
    \attack, 1.5,
    \modEnvAttack, 1.5,
    \decay, 1.5,
    \modEnvDecay, 1.5,
    \release, 4,
    \root, -3,
    \octave, 4,
    \degree, Prand([0, -2, 3], inf),
    \legato, 0.7,
    \dur, 9,
    \amp, 1,
)
)
Ndef(\arpbass).quant = 9;
