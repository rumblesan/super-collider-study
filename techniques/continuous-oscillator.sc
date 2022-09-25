
"Setup.scd".load;
s.plotTree

(
  SynthDef(\fm, {arg out, freq = 60, gate=1, pgate=0, mgate=0, depth=1.0, amp=0.1, modRatio=1.0;
    var carrier, modulator, env, modEnv, penv;
    penv = Env.perc(0.01, 0.1, pgate).ar(gate: pgate);
    env = Env.asr(0.1, 1, 0.7).kr(2, gate);
    modEnv = Env.asr(0, depth, 1).kr(2, mgate);

    modulator = SinOsc.ar(freq * modRatio, mul: freq * modEnv);

    carrier = SinOsc.ar((freq + modulator) * (1 + penv), env);
    Out.ar(out, carrier);
  }).add;
)

(
  ~synth = Pbind(
    \instrument, \fm,
    \amp, Pseq([0.8], inf),
    \freq, Pseq([200, 201, 203, 400], inf),
    \pgate, Pseq([0, 0, 5], inf),
    \mgate, Pseq([0, 1], inf),
    \depth, Prand([0.1, 0.7, 10, 1, 3], inf),
    \decay, 1.7,
    \dur, Pseq([1, 0.5, 0.5, 1, 2, 4], inf),
  )
)

~synth.play;
~synth.clear;




(
  SynthDef(\fmcontinuous, {arg out, amp=1, freq = 60, vtrig=0, ptrig=0, mtrig=0, pdepth=1.0, mdepth=1.0, modRatio=1.0;
    var carrier, modulator, venv, penv, modEnv;
    venv = Env.perc(0.01, 0.5, amp).kr(gate: vtrig);
    penv = Env.perc(0.01, 0.1, pdepth).kr(gate: ptrig);
    modEnv = Env.perc(0.01, 0.1, mdepth * freq).kr(gate: mtrig);

    modulator = SinOsc.ar(freq * modRatio, mul: modEnv);

    carrier = SinOsc.ar((freq + modulator) * (1 + penv), mul: venv);
    Out.ar(out, carrier);
  }, [0, 0, 0, 0, \tr, \tr, \tr]).add;
)

~con = \fmcontinuous
~con.play

// gate doesn't seem to work quite right here
~con[1] = \set -> Pbind(
  \freq, 200,
  \ptrig, Pseq([1, 0, 0, 1, 0], inf),
  \vtrig, 1,
  \amp, 0,
  \vtrig, Pseq([1, 0], inf),
  \dur, 1,
)
