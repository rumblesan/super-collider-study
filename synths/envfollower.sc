(
  SynthDef(\ef, {|out=0, in, gain=1, attack=0.01, release=0.7|
    var env = EnvDetect.ar(in * gain, attack, release).min(1);
    Out.ar(out, env);
  }, [0, \ar]).add;
)
