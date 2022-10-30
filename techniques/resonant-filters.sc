
"Setup.scd".load;

~arp1 = Pbind(
    \instrument, \fm2,
    \release, 0.5,
    \amp, 0.5,
    \scale, Scale.major,
    \ratio, 3,
    \root, -3,
    \octave, 5,
    \degree, Pseq([7, 5, 4, 3, 2], inf),
    \modDepth, 2,
    \dur, 1,
)
~arp1.quant = 4;
~arp1.clear

~arp1.filter(1, {|in|
      Mix.new([
        in * 0.25,
        BPF.ar(in, 3500 + (2000 * ~lfo1), 18.reciprocal, mul: 1),
        BPF.ar(in, 750 + (250 * ~lfo2), 18.reciprocal, mul: 3),
        BPF.ar(in, 500 + (250 * ~lfo3), 18.reciprocal, mul: 2),
      ])
})

(
  SynthDef(\resonator, {arg out=1, in,
    dry=1.0,
    f1=130, q1=1, g1=1,
    f2=260, q2=1, g2=1,
    f3=280, q3=1, g3=1;
    Out.ar(out,
      Mix.new([
        in * dry,
        BPF.ar(in, f1, q1.reciprocal, mul: g1),
        BPF.ar(in, f2, q2.reciprocal, mul: g2),
        BPF.ar(in, f3, q3.reciprocal, mul: g3),
      ])
    );
  },[0, \ar]).add;
)

~lfo1 = {SinOsc.kr(1.9, add: 0.6, mul: 0.5) * 1500}

~res = \resonator
~res.set(\dry, 0)
~res.set(\f1, 440)
~res.set(\q1, 14)
~res.set(\g1, 1)
~res.set(\q2, 24)
~res.set(\f2, ~lfo1)
~res.set(\g2, 3)
~res.set(\q3, 14)
~res.set(\g3, 1)

~arp1 <>> ~res

~res.play
