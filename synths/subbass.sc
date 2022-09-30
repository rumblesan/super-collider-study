(
  SynthDef(\subbass, { arg out=0, freq=50, gate=1, amp=1,
    detune=0.01, pulsewidth=0.5,
    filterOffset=0,
    attack=0.1, release=0.5;
    var oscCount, env, oscs, filtered;
    oscCount = 2;
    env = Env.asr(attack, amp, release).kr(2, gate: gate);
    oscs = Mix.new(oscCount.collect({|i|
      var d = (i * detune * freq);
      Pulse.ar(freq + d, pulsewidth);
    }));
    filtered = RLPF.ar(oscs, freq * (1 + filterOffset), 0.85);
    Out.ar(out, filtered * env);
  }).add;
)


/*
(
  ~bass = Pbind(
    \instrument, \subbass,
    \octave, 3,
    \detune, 0.02,
    \filterOffset, 0.3,
    \freq, Pseq([50], inf),
    \amp, 0.7,
    \dur, Pseq([2, 2, 1], inf),
  )
)

~bass.play
*/
