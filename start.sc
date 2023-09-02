"Setup.sc".load;

/*
* ply
* piston + parts/click
* delay-glitch + softkick
* thumper
* troi
* parts/acid
* alles
*/

p.clock.tempo = 165/60;

Ndef(\mix, {|samplerate = 1.0, bits = 16|
  var mix = Mix.new([
    //ChannelStrip.ar(Ndef(\kick).ar(1), ~faders[1].kr(1), 0),
    //Ducker.ar(Ndef(\kick).ar(1) * 0.5, 0.01, 0.2) *
      Mix.new([
        //ChannelStrip.ar(Silent.ar, -3.dbamp, 0),
        //ChannelStrip.ar({SinOsc.ar(50)}, -3.dbamp, 0),
      ])
  ]);
  Decimator.ar(mix, samplerate * 44100, bits)
})

Ndef(\mix).set(\samplerate, 1)
Ndef(\mix).set(\bits, 8)
Ndef(\mix).map(\bits, Ndef(\mixbits))

Ndef(\mixbits,
  Pcontrol(
    \mod,
    \value, Pseq([24, 2, 24, 2, 24, 2, 24], inf),
    \slew, Pseq([0.1], inf),
    \dur, Pseq([2, 0.5, 2.5, 0.5, 5, 1, 4], inf)
  )
)
Ndef(\mixbits).quant = 4;

~faders.scale(14, 1, 4)
~faders.scale(15, 0.01, 0.95)
~faders.scale(16, 0.3, 0.99)

Ndef(\verb)[0] = \dirtverb;
Ndef(\verb).set(
  \drywet, ~faders.proxyAt(13),
  \hipass, 10,
  \lopass, 18000,
  \predelay, 0.07,
  \size, 0.008,
  \decay, ~faders.proxyAt(15),
  \diffusion, 0.19,
  \downsampling, ~faders.proxyAt(16),
  \gain, ~faders.proxyAt(14),
  \damping, 0.5,
  \feedbackHiPass, 160,
  \width, 0.6,
)

Ndef(\verb)[1] = \pset -> Pbind(
  //\downsampling, Pwrand([0, 0.8, 0.9, 0.97], [10, 0, 2, 3].normalizeSum, inf),
  //\downsampling, Pseq([Pn(0.8, 10), Pn(0.9, 12), Pn(0.8, 8), Pn(0.92, 8)], inf),
  //\drywet, Pwrand([0.2, 0.5, 0.9], [10, 2, 1].normalizeSum, inf),
  \gain, Pwrand([1, 1.5, 2], [10, 2, 5].normalizeSum, inf),
  //\dur, Pwrand([2, 1, 0.5], [3, 7, 1].normalizeSum, inf) / 2,
  \size, Pseq([0.4, Pn(0.8, 5), 0.01, 0.9, 0.1, Pn(0.7, 6), 0.01], inf),
  //\damping, Pwrand([0.8, 0.2, 0.91], [15, 4, 2].normalizeSum, inf),
  \dur, 0.5,
);


Ndef(\out, { \in.ar(0!2) * -3.dbamp }); Ndef(\out).play;

Ndef(\verb) <>> Ndef(\out)
Ndef(\mix) <>> Ndef(\verb)


p
p.clock.tempo = 170/60;

Ndef(\out).proxyspace.clean(Ndef(\out))
