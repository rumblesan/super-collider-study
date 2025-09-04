# JITlib basics

"Setup.sc".load;
ProxyMixer(p, 8);
s.plotTree;

// Create a proxy synth
// It's running in the background making sound on a private bus
~test = {arg freq=440; SinOsc.ar(freq)};

// connect that bus up to the output so it can be heard
~test.play
// disconnect the bus from the output, but leave it playing
~test.stop

~test.pause;
~test.resume;
// get rid of it completely
~test.clear;

// Not clear what free does either
~test.free;



// create a basic synth and add it to the server
(
SynthDef(\ping, {arg out=0, freq=220, amp=1, decay=0.5;
  var osc, env;
  env = Env.perc(0.01, decay, amp).kr(doneAction: 2);
  osc = SinOsc.ar(freq);
  Out.ar(out, osc * env);
}).add;
)

// create a pattern that's playing the synth and sequencing events
// this will create a new instance of the synth on every note
(
  ~ping = Pbind(
    \instrument, \ping,
    \decay, 0.8,
    \freq, 220,
    \dur, Pseq([4], inf),
    \amp, 1,
  )
)
// monitor the synth
~ping.play;
// stop monitoring the synth
~ping.stop;

(
~ping = Pbind(
  \instrument, \ping,
  \degree, Pseq([[0, 2, 4], -3, [5, 7, 11], 4], inf),
  \scale, Scale.harmonicMinor,
  \dur, 0.3,
  \amp, Pseq([0.7, 0.5, 0.3, 0.2], inf),
  \legato, 0.4,
);
)

// not totally clear what pause and resume are for
~ping.pause;
~ping.resume;

// clear pattern
~ping.clear;


~ther = {Saw.ar(freq: MouseX.kr(300, 2500), mul: MouseY.kr(0, 1))};
~ther.play;
~ther.clear;

// plotting
{ SinOsc.ar }.plot;
{ Saw.ar }.plot;
{ Pulse.ar }.plot;

Env.perc.plot;

~pollex = {SinOsc.kr(1).poll};
~pollex.clear;
