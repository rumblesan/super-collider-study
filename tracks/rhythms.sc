Ndef(\kick,
  Pbind(
    \instrument, \modkick,
    \freq, 50,
    \moddecay, 0.1,
    \moddepth, 0.5,
    \ratio, 3,
    \rampattack, 0.02,
    \rampdecay, 0.02,
    \rampcurve, -4,
    \attack, 0.01,
    \decay, 0.5,
    \amp, 1,
    \ramp, 2,
    \gain, 1.5,
    \hipass, 50,
    \dur, Pseq([
      4, 4, 4, 4
    ], inf) / 4,
  )
)
Ndef(\kick).clear;
Ndef(\kick).fadeTime = 0;
Ndef(\kick).quant = 8;

Ndef(\kick).fadeTime

Ndef(\kick).plot(10)

Ndef(\perc,
  Pbind(
    \instrument, \digiclap,
    \degree, Pseq([7, Pn(0, 5)], inf),
    \degree, 0,
    \attack, 0.01,
    \decay, 0.01,
    \penv, 5,
    \amp, Pseq([
      1, Pn(0.8, 4),
      1, Pn(0.8, 2)], inf),
    \dur, Pseq([
      1, 1, 1,
      1, r, 1,
      1, 1, r,
      1, 1, 1,

      1, 0.5, 0.5, 1,
      r, 1, r,
      1, r, 1,
      r, 1, 1,
    ], inf) / 3,
  )
)

Ndef(\perc).quant = 8;
Ndef(\perc).clear;

Ndef(\stick,
  Pbind(
    \instrument, \stick,
    \degree, 0,
    \attack, 0.01,
    \decay, 0.1,
    \noise, 0.7,
    \click, 3,
    \amp, 1,
    \dur, Pseq([
      r, r, 1, r,
      r, 1, r, r,
      r, r, 1, r,
      r, 1, r, r,
      1, r, 1, r,
    ], inf),
  )
)
Ndef(\stick).quant = 4;
Ndef(\stick).clear;



Ndef(\clicker, Pbind(
  \instrument, \fmhat,
  \degree, 0,
  \octave, 5,
  \amp, 1,
  \attack, 0.0,
  \decay, 0.01,
  \sustain, 0.001,
  \dur, Pseq([
    1, 1, 1, 1,
    r, 1, 1, r,
    1, r, r, r,
    1, r, 1, r,
  ], inf) / 4,
)
)
Ndef(\clicker).quant = 4;
