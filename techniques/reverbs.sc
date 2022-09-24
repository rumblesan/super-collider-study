
"Setup.scd".load;

s.plotTree;

(
SynthDef(\ping, {arg out=0, freq=220, amp=1, decay=0.5;
  var osc, env;
  env = Env.perc(0.01, decay, amp).kr(doneAction: 2);
  osc = Saw.ar(freq);
  Out.ar(out, osc * env);
}).add;
)


(
  ~ping = Pbind(
    \instrument, \ping,
    \decay, 0.2,
    \freq, 200,
    \dur, Pseq([4], inf),
    \amp, 1,
  )
)
~ping.play;
~ping.stop;
~ping.clear;



(
~bass[0] = {|freq=50, ratio=1, mod=0.1, gain=1.2|
  var modOsc = SinOsc.ar(freq * ratio, mul: mod, add: 1);
  (SinOsc.ar(freq * modOsc) * gain).tanh;
}
)
~bass[1] = \pset -> Pbind(
  \freq, 50,
  \ratio, 1,
  \mod, 0.2,
  \gain, 1.2,
  \dur, Pseq([8, 2, 3, 5], inf),
)

~bass[1] = \pset -> Pbind(
  \freq, Pseq([50, 100, 50, 75], inf),
  \ratio, Pwrand([1, 2, 1.5], [10, 5, 3].normalizeSum, inf),
  \mod, 0.9,
  \gain, Pseq([1.3, 1.6, 1.2, 3, 1.1], inf),
  \dur, Pseq([8, 2, 3, 5], inf),
)

~mix = {(~ping * 0.8) + (~bass * 0.3)}

~outverb[0] = \verb;
~outverb <<> ~mix
~outverb.play;
~outverb.clear;

~outverb.set(\drywet, 0.5);
~outverb.set(\hipass, 90);
~outverb.set(\lopass, 6000);

~lfo = {SinOsc.ar(0.8, add: 1) * 0.4}
~lfo2 = {SinOsc.ar(0.1, add: 2) * 0.1}

~outverb[1] = \pset -> Pbind(
  \verbLevel, 0.9,
  \dryLevel, 0.7,
  \damping, ~lfo2,
  \width, Pwrand([0, 0.3, 0.9], [10, 3, 1].normalizeSum, inf),
  \predelay, Pseq([0.1, 0.003, 0.01, 0.1, 0.2], inf),
  //\predelay, 0.01,
  \size, Pwrand([0.01, 0.9, 0.5], [3, 0, 7].normalizeSum, inf),
  //\diffusion, Pwrand([0.1, 0.9, 0.5], [3, 7, 5].normalizeSum, inf) * 2,
  \diffusion, 0.99,
  \downsampling, Pwrand([0, 0.9, 0.93, 0.99], [7, 3, 9, 1].normalizeSum, inf),
  \gain, Pwrand([1.5, 2.7], [15, 0].normalizeSum, inf),
  \dur, Pseq([0.5, 0.5, 0.25, 0.25], inf),
)

~outverb.set(\damping, 0.2)
~outverb.set(\decay, 0.3)
~outverb.set(\size, 1.3)
~outverb.set(\gain, 3.3)
~outverb.set(\downsampling, 0.3)
~outverb.set(\diffusion, 0.30)


(
  ~verbmod = Pbind(
    \type, \set,
    \id, ~outverb.nodeID,
    \damping, Pseq([0.1, 0.9, 0.4, 0.9, 0.1], inf),
    \dur, 0.5,
  );
)
