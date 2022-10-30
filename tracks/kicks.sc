"Setup.scd".load;

(

  ~kickz[0] = Pbind(
    \instrument, \modkick,
    \amp, 0.3,
    \decay, 2,
    \rdecay, 0.08,
    \mdecay, 0.3,
    \pfreq, 50,
    \ramp, 3,
    \mod, 20,
    \freq, 50,
    \dur, 2,
  )
)

~kickz.play;
~kickz.stop;

(
  ~kickz.filter(1, {|in, distortion=0.8|
    (in * distortion).tanh * 1.2;
  })
)

~kickz.set(\distortion, 20)

(
  ~kickz.filter(2, {|in, ffreq=2000, q=1|
    RLPF.ar(in, ffreq, q)
  })
)

~kickz.set(\ffreq, 5000)
