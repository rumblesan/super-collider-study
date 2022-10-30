
"Setup.scd".load;


~arp1 = Pbind(
    \instrument, \fm2,
    \release, 0.5,
    \amp, 0.5,
    \scale, Scale.major,
    \ratio, 2,
    \root, -3,
    \octave, 5,
    \degree, Pseq([7, 5, 4, 3, 2], inf),
    \dur, 1,
)
~arp1.quant = 4;

~arp2 = Pbind(
    \instrument, \fm2,
    \release, 0.2,
    \amp, 0.1,
    \scale, Scale.major,
    \ratio, 3.5,
    \root, -3,
    \octave, 5,
    \degree, Pseq([7, Prand([5, 3]), 9, 3, 2, 0, 2], inf),
    \dur, Pseq([0.5, 1, 0.5, 1.5], inf),
)
~arp2.quant = 4;

~arp3 = Pbind(
    \instrument, \fm2,
    \release, 0.2,
    \amp, 0.1,
    \scale, Scale.major,
    \ratio, Pseq([2.5, 2.0, 2.5, 3], inf),
    \root, -3,
    \octave, Pwrand([6, 7], [10, 1].normalizeSum, inf),
    \degree, Pseq([3, Prand([6, 2]), 7, 9], inf),
    \dur, Pseq([1, 2, 0.5, 0.5, 1, 0.5], inf),
)
~arp3.quant = 4;

(
~bass = Pbind(
    \instrument, \fm2,
    \scale, Scale.major,
    \ratio, 0.5,
    \release, 0.75,
    \root, -3,
    \octave, 3,
    \degree, 0,
    \dur, 9,
    \amp, 0.1,
)
)
~bass.quant = 8;
