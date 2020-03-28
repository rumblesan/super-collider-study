
"Setup.scd".load;

(
SynthDef(\lead, {arg freq = 220, fmod = 0.2, ctf=100, res = 0.2, gate = 1, sus=0, dec=1.0, vol = 0.8;
  var osc, volEnv, filEnv, lpf;
  volEnv =  EnvGen.kr(
    envelope: Env([10e-10, 1, 1, 10e-10], [0.1, sus, dec], curve: \exponential, releaseNode: 2),
    gate: gate,
    doneAction: Done.freeSelf,
  );
  filEnv =  EnvGen.kr(
    envelope: Env.new([10e-10, 1, 10e-10], [0.01, dec], releaseNode: 1, curve: \exponential ),
    gate: gate,
    doneAction: Done.freeSelf,
  );
  osc = Saw.ar(freq, volEnv);
  lpf = RLPF.ar(osc, ctf + (filEnv), res, vol);
  Out.ar(0, lpf.dup);
}).add;
)


~flfo = {SinOsc.ar(0.3, 0.0, 30, 370)};

(
  var notes, lengths, durs, dur;
  dur = 0.17;

  notes = [0, 3, 5, 2, 1, 0];
  lengths = [2, 1, 1, 1, 1, 2];

  durs = lengths.collect { |i| i * dur };
  Pdef(\tune,
    Pbind(
      \instrument, \lead,
      \degree, Pseq(notes, inf),
      \scale, Scale.harmonicMinor,
      \ctf, ~flfo,
      \res, 5.8,
      \dur, Pseq(durs, inf),
    )
  );
  Pdef(\tune).quant = durs.sum { |i| i };
)

Pdef(\tune).play;
Pdef(\tune).stop;
