"../../Setup.sc".load;


Ndef(\cutoffmod,
  Pcontrol(
    \mod,
    \value, Pseq([0.3, 0.7], inf) + 0.3,
    \slew, 9,
    \dur, 16,
  )
)
Ndef(\cutoffmod).quant = 4;
Ndef(\cutoffmod).scope

Ndef(\modlfo,
  Pcontrol(
    \mod,
    \value, Pseq([2, 1, 0.5], inf) * 0.4,
    \slew, 2,
    \dur, 3
  )
)
Ndef(\modlfo).quant = 4;
Ndef(\modlfo).scope

Ndef(\voice1,
  Pbind(
    \instrument, \fm3filter,
    \degree, Prand([0, 2, 3, 5, 6], inf),
    \scale, Scale.minor,
    \attack, 0.1,
    \release, 1.4,
    //\cutoff, Ndef(\cutoffmod),
    \resonance, 0.7,
    //\mod, Ndef(\modlfo),
    \mod1, 0.7,
    \level1, 1,
    \ratio1, 0.5,

    \ratio2, 2.71,
    \mod2, 1.7,
    \attack2, 0.01,
    \decay2, 0.1,
    \level2, 0,

    \gain, 2,
    \amp, 1,
    \legato, 0.3,
    \dur, Pseq([1, Rest(7)], inf),
    \dur, Pwrand(
      [1, 2, 3, Rest(2), Rest(3), Rest(6)],
      [15, 7, 2, 16, 5, 2 ].normalizeSum, inf)
  )
)
Ndef(\voice1).clear;

Ndef(\fourtap)[0] = \fourtapdelay

Ndef(\fourtap).set(
    \delay, 1.0,
    \timeScale, Pwrand([1.0, 0.5], [15, 4].normalizeSum, inf),
    \drywet, 1.0,
    \feedback, 0.6,
    \lopass, 10000,
    \dur, 2,
)
Ndef(\fourtap) <<> Ndef(\voice1)
Ndef(\fourtap).clear;

Ndef(\bass,
  PmonoArtic(
    \fm3filter,
    \degree, Prand([0, 2, 3, 5, 6], inf),
    \octave, Pwrand([3, 2], [15, 2].normalizeSum, inf),
    \scale, Scale.minor,
    \attack, 0.3,
    \decay, 5,
    \cutoff, 500,
    \mod, 0.5,
    \mod1, 0.2,

    \amp, 1,
    \legato, 0.4,
    \dur, Pseq([1, Rest(7)], inf),
    \dur, Pwrand(
      [1, 2, 3, Rest(2), Rest(6)],
      [15, 7, 2, 11, 2].normalizeSum, inf)
  )
)
Ndef(\voice1).clear;
Ndef(\mix, {|samplerate = 1.0, bits = 16|
  Mix.new([
    ChannelStrip.ar(Silent.ar, -0.dbamp, 0),
    ChannelStrip.ar({SinOsc.ar(50)}, -3.dbamp, 0),
    //ChannelStrip.ar(Ndef(\voice1).ar(1), -3.dbamp, 0),
    //ChannelStrip.ar(Ndef(\bass).ar(1), -6.dbamp, 0),
  ]);
})



Ndef(\verb).clear
Ndef(\verb)[0] = \dirtverb;
Ndef(\verb).set(
  \drywet, 0.35,
  \hipass, 160,
  \lopass, 12000,
  \predelay, 0.02,
  \size, 0.89,
  \decay, 0.70,
  \diffusion, 0.9,
  \damping, 0.6,
  \feedbackHiPass, 160,
  \width, 0.5,
)

Ndef(\out, { \in.ar(0!2) * 0.dbamp }); Ndef(\out).play;

Ndef(\verb) <>> Ndef(\out)
Ndef(\mix) <>> Ndef(\verb)
