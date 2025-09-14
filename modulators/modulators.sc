(
  SynthDef(\percenv, {|out, trig, attack=0.01, decay=0.1, curve=(-4), value=1, offset=0|
    Out.ar(
      out,
      Env.perc(attack, decay, value, curve).ar(
        levelBias: offset,
        gate: trig,
      ))
  }, [0, \tr]).add;

  SynthDef(\mod, {|out, value=0, slew=0|
    Out.kr(out, Lag.kr(value, slew))
  }).add;

  SynthDef(\envfollower, {|out, gain=1, attack=0.01, decay=0.7, offset=0, value=1|
    var env = EnvDetect.ar(\in.ar(0) * gain, attack, decay).min(1);
    Out.ar(
      out,
      (env * value) + offset
    );
  }).add;

  SynthDef(\envducker, {|out, gain=1, attack=0.01, decay=0.7, offset=0, value=1|
    var env = EnvDetect.ar(\in.ar(0) * gain, attack, decay);
    var ducker = (1 - env).max(0);

    Out.kr(
      out,
      (ducker * value) + offset
    );
  }).add;
)
