
"Setup.scd".load;


(
b = Buffer.alloc(s, 512, 1);
b.cheby([1, 0, 1, 0, 1, 0, 1, 1]);
)

(
  ~shaped = { |shaping=0.3|
    var d = shaping.min(1.0).max(0.01);
    (Shaper.ar(b, SinOsc.ar(50) * d)) / d
  }
)

~shaped.set(\shaping, 0.9)
~shaped.play;
~shaped.plot;
~shaped.clear;

~folded
(
  ~folded[0] = { |folding=1|
    var f = folding.max(1);
    (SinOsc.ar(100) * f).fold2
  }
)
~folded.play;
~folded.set(\folding, 1);
~folded.plot;
(
  ~folded[1] = \set -> Pbind(
    \dur, Pseq([1, 0.25, 0.25, 0.5], inf),
    \folding, Pwrand([1, 5, 7, 3, 16], [20, 5, 5, 7, 2].normalizeSum, inf),
  )
)
~folded.clear;

// works basically the same as the above
(
  ~fold2 = { |folding=1|
    Fold.ar(SinOsc.ar(100) * folding, -1, 1)
  }
)
~fold2.plot


(
  ~dist = { |gain=1|
    (SinOsc.ar(100) * gain).tanh
  }
)

~dist.set(\gain, 10);
~dist.play;
~dist.plot;
~dist.clear;
