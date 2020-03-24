

SynthDef("mySine1", {Out.ar(0, SinOsc.ar(770, 0, 0.1))}).add;

x = Synth("mySin1");
