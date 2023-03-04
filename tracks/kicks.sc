"Setup.scd".load;

(

  ~kickz[0] = Pbind(
    \instrument, \modkick,
    \amp, -6.dbamp,
    \decay, 2,
    \rdecay, 0.08,
    \mdecay, 0.3,
    \pfreq, 50,
    \ramp, 3,
    \mod, 20,
    \freq, 50,
    \dur, Pn(
      Pfinval(16,
      Pseq([1, 1, 3, 1, 3, 4], inf)
      ), inf
    )
  )
)

~kickz.play;
~kickz.stop;

(
  ~kickz.filter(1, {|in, distortion=0.8|
    (in * distortion).tanh * 1.2;
  })
)

~kickz.set(\distortion, 3)

(
  ~kickz.filter(2, {|in, ffreq=2000, q=1|
    RLPF.ar(in, ffreq, q.reciprocal)
  })
)

~kickz.set(\ffreq, 800)
~kickz.set(\q, 4)
