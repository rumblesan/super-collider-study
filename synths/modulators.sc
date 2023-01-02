(
  SynthDef(\percenv, {|out, trig, attack=0.01, decay=0.1, curve=(-4), value=1|
    Out.kr(
      out,
      Env.perc(attack, decay, value, curve).kr(
        gate: trig,
      ))
  }, [0, \tr]).add;

  SynthDef(\mod, {|out, value=0, slew=0|
    Out.kr(out, Lag.kr(value, slew))
  }).add;
)
