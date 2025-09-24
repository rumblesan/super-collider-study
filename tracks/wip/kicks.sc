// kind of meh

Ndef(\kick)

Ndef(\kick)[0] = Pbind(
  \instrument, \modkick,
  \amp, Pseq([2, 1, 1], inf),
  \decay, 2,
  \rdecay, 0.08,
  \mdecay, 0.3,
  \pfreq, 50,
  \ramp, 3,
  \mod, 20,
  \freq, 50,
  \dur, Pn(
    Pfinval(16,
    Pseq([4, 4, 8], inf)
    ), inf
  )
)


Ndef(\kick).filter(1, {|in, distortion=0.8|
  (in * distortion).tanh * 1.2;
})

Ndef(\kick).set(\distortion, 1)

(
Ndef(\kick).filter(2, {|in, ffreq=2000, q=1|
  RLPF.ar(in, ffreq, q.reciprocal)
})
)

Ndef(\kick).set(\ffreq, 800)
Ndef(\kick).set(\q, 4)
