/*
prime numbers up to 1000
2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997.
*/

(
  SynthDef(\niceverb, {
    var in = Mix.new([\in.ar(0!2), PinkNoise.ar(-90.dbamp)!2]);
    var signal = Mix.new([in[0], in[1]]) / 2;
    var maxPredelay = 1;
    var verbFeedback = LocalIn.ar(1);
    var loops = 4;
    var maxSize = 4;
    var size = \size.ar(0.5);
    var verbDelays;
    var output;
    var drywet = \drywet.ar(0.3);
    var samplerate = SampleRate.ir;
    var initialDiffusionCount;
    var initialDiffusionAmount;
    var initialDiffusionTimes;
    var diffusion = \diffusion.ar(0.3);
    var d1, d2, d3, d4;
    var delayNumOffset = 12;
    var decay = \decay.ar(0.1);
    var damping = \damping.ar(0.5);
    signal = HPF.ar(signal, \hipass.ar(150));
    signal = LPF.ar(signal, \lopass.ar(12000));
    signal = DelayC.ar(signal, maxPredelay, \predelay.ar(0.02));

    verbFeedback = Select.ar(CheckBadValues.ar(verbFeedback, 0, 0), [verbFeedback, DC.ar(0), DC.ar(0), verbFeedback]);
    signal = signal + (verbFeedback * decay);

    initialDiffusionCount = 2;
    initialDiffusionTimes = ([379, 491, 509, 607] / samplerate).keep(initialDiffusionCount);
    initialDiffusionAmount = 0.15;
    initialDiffusionTimes.do({ |t|
      signal = AllpassC.ar(signal,
        t,
        t,
        initialDiffusionAmount);
    });

    verbDelays = [ 1447, 727, 613, 673, 1439, 2083, 2011, 1511, 1493, 1277, 2437, 2383, 2341, 997, 1061, 2039, 1997, 1481, 1627, 1129, 743, 983, 1091, 907, 541, 2393, 1801, 2081, 1787, 1453, 977, 2311, 691, 479, 2377, 1693, 1013, 1931, 1049, 2243, 839, 739, 1747, 601, 1823, 1123, 2467, 1297, 1613, 1361, 2207, 593, 619, 1709, 449, 937 ] / samplerate;

    signal = loops.collect({ |j|
      d1 = verbDelays[(j * 4) + delayNumOffset];
      signal = AllpassC.ar(signal, d1, d1, diffusion);
      d2 = verbDelays[(j * 4) + 1 + delayNumOffset];
      signal = AllpassC.ar(signal, d2, d2, diffusion);
      d3 = verbDelays[(j * 4) + 2 + delayNumOffset];
      signal = CombC.ar(signal, d3, d3, 0.05);
      d4 = verbDelays[(j * 4) + 3 + delayNumOffset];
      signal = DelayC.ar(signal, maxSize, size * d4);
      signal = OnePole.ar(signal, damping);
    });

    LocalOut.ar(signal);

    output = (signal * drywet) + (in * (1-drywet));

    Out.ar(\out.ar(), output!2);
  }).add;
)
