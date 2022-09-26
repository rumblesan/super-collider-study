/*
prime numbers up to 1000
2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997.
*/

(
  SynthDef(\verb, {arg out=0, in,
    drywet=0.2,
    hipass=50, lopass=6000,
    predelay=0.06,
    size=0.7, decay=0.8, diffusion=0.3,
    downsampling=0, gain=1.0,
    damping=0.5, width=0.2;
    var samplerate, signal,
    maxPredelay,
    reflectionCount, reflectionTimes, previousReflectionTime,
    initialDiffusionCount, initialDiffusionTimes, initialDiffusionAmount,
    tankLoops, tankDelays, tankFeedback, loops, verbOut, output;

    samplerate = SampleRate.ir;
    signal = in;
    signal = HPF.ar(signal, hipass);
    signal = LPF.ar(signal, lopass);
    maxPredelay = 1;
    signal = DelayC.ar(signal, predelay, maxPredelay);

    reflectionCount = 4;
    reflectionTimes = ([180, 269, 444, 151, 109, 139, 163, 233, 211, 113] / samplerate).keep(reflectionCount);
    previousReflectionTime = 0;
    reflectionTimes.do({ |delayTime, i|
      var t = delayTime + previousReflectionTime;
      signal = DelayC.ar(signal, t, t, mul: 1);
      previousReflectionTime = delayTime;
    });

    initialDiffusionCount = 2;
    initialDiffusionTimes = ([379, 491, 509, 607] / samplerate).keep(initialDiffusionCount);
    initialDiffusionAmount = 0.15;
    initialDiffusionTimes.do({ |t|
      signal = AllpassC.ar(signal,
        t,
        t,
        initialDiffusionAmount);
    });


    tankLoops = 4;
    tankDelays = [ 1447, 727, 613, 673, 1439, 2083, 2011, 1511, 1493, 1277, 2437, 2383, 2341, 997, 1061, 2039, 1997, 1481, 1627, 1129, 743, 983, 1091, 907, 541, 2393, 1801, 2081, 1787, 1453, 977, 2311, 691, 479, 2377, 1693, 1013, 1931, 1049, 2243, 839, 739, 1747, 601, 1823, 1123, 2467, 1297, 1613, 1361, 2207, 593, 619, 1709, 449, 937 ] / samplerate;
    tankFeedback = LocalIn.ar(1);

    loops = tankLoops.collect({ |i|
      var delayi = i*3;
      var loopinput = signal + (tankFeedback * decay);
      var d1 = tankDelays[delayi];
      var ap1 = AllpassC.ar(loopinput, d1, d1, diffusion);
      var d2 = tankDelays[delayi + 1];
      var ap2 = AllpassC.ar(ap1, d2, d2, diffusion);
      var d3 = tankDelays[delayi + 2] * size;
      var outdelay = DelayC.ar(ap1, d3, d3);
      var distorted = (outdelay * gain).tanh;
      var downsampled = SmoothDecimator.ar(distorted, samplerate * (1-downsampling));
      var filtered = OnePole.ar(downsampled, damping);
      tankFeedback = filtered;
      tankFeedback;
    });

    LocalOut.ar(tankFeedback);

    verbOut = [loops[0] + loops[2], loops[1] + loops[3]] * drywet;
    output = (in * (1-drywet)) ! 2;

    Out.ar(out, Splay.ar(verbOut + output, width));
  }, [0, \ar]).add;
)

/*


~mix = {SinOsc.ar(100)}
~mix.play
~mix.stop

~outverb[0] = \verb;
~outverb <<> ~mix
~outverb.play;
~outverb.clear;

~outverb.set(\drywet, 0.5);
~outverb.set(\hipass, 90);
~outverb.set(\lopass, 6000);

~lfo = {SinOsc.ar(0.1, add: 2) * 0.1}

~outverb[1] = \pset -> Pbind(
  \predelay, Pseq([0.1, 0.003, 0.01, 0.1, 0.2], inf),
  \size, Pwrand([0.01, 0.9, 0.5], [3, 0, 7].normalizeSum, inf),
  \diffusion, 0.9,
  \downsampling, Pwrand([0, 0.9, 0.93, 0.99], [7, 3, 9, 1].normalizeSum, inf),
  \gain, Pwrand([1.5, 2.7], [15, 0].normalizeSum, inf),
  \damping, ~lfo,
  \width, Pwrand([0, 0.3, 0.9], [10, 3, 1].normalizeSum, inf),
  \dur, Pseq([0.5, 0.5, 0.25, 0.25], inf),
)



*/
