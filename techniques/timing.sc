
"Setup.scd".load;

// quant works fine with Pbinds, but you need to make sure everything has a suitable quant set

// e.g. if you want the \clikr to hit at the same time as a \bkick then they both need to
// have quant values that are multiples of 2. 4 works well
// if the \clickr \rim pattern has a quant set, but the \bkick doesn't then the kick could
// still be on the offbeat.

// if you change the quant, then the proxy needs to be re-evaluated

// the proxy will keep its quant value even if you clear/free it

(
  ~kick = Pbind(
    \instrument, \bkick,
    \amp, 0.8,
    \decay, 1,
    \rdecay, 0,
    \mdecay, 1,
    \ramp, 1,
    \freq, 50,
    \dur, Pseq([2], inf),
  )
)
~kick.play;
~kick.quant = 4;

(
  ~click = Pbind(
    \instrument, Pseq([\clikr, \rim, \rim, \rim], inf),
    \amp, 1,
    \decay, 0.2,
    \dur, 1,
  )
)
~click.quant = 4;
~click.play
