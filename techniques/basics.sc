
s.boot;

(
{SinOsc.ar}.play;
)

c = Pbind(\note, Pwhite(0, 10), \dur, 0.1);

c.play;

Pbind(\degree, Pseries(0, 1, 30), \dur, 0.05).play;

Pbind(\degree, 2).play;

(
p = Pbind(
  \degree, Pseq([[0, 2, 4], -3, [5, 7, 11], 4], inf),
  \scale, Scale.harmonicMinor,
  \dur, 0.3,
  \amp, Pseq([0.7, 0.5, 0.3, 0.2], inf),
  \legato, 0.4,
);
)

~player = p.play;

~player.stop;


~ther = {Saw.ar(freq: MouseX.kr(300, 2500), mul: MouseY.kr(0, 1))}.play;
~ther.free;

{ SinOsc.ar }.plot;
{ Saw.ar }.plot;
{ Pulse.ar }.plot;

{SinOsc.kr(1).poll}.play;
{LFNoise0.kr(10).range(500, 1500).poll}.play;

{Out.ar(0, Saw.ar(freq: [440, 570], mul: Line.kr(0, 1, [10, 16])))}.play;


~reverbSend = Bus.audio(s, 2);

~reverb = {Out.ar(0, FreeVerb.ar(In.ar(~reverbSend, 2), mix: 0.5, room: 0.9, mul: 0.4))}.play;

b = {Out.ar(~reverbSend, SinOsc.ar([800, 880], mul: LFPulse.ar(2)))}.play;

~reverb.free; b.free;



s.plotTree;


(
x = {
var lfn = LFNoise2.kr(1);
var saw = Saw.ar(
  freq: 30,
  mul: LFPulse.kr(
    freq: LFNoise1.kr(1).range(1, 10),
    width: 0.1));
var bpf = BPF.ar(in: saw, freq: lfn.range(500, 2500), rq: 0.01, mul: 20);
    Pan2.ar(in: bpf, pos: lfn);
  }.play;
)

c = { Mix.fill(16, {SinOsc.ar(rrand(100, 3000), mul: 0.01)}) }.play;
c.free;


{WhiteNoise.ar(Line.kr(0.2, 0, 2))}.play;

{WhiteNoise.ar(Line.kr(0.2, 0, 2, doneAction: 2))}.play;

Env.perc.plot;
