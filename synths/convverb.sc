
(
  SynthDef(\convverb, {arg out=0, drywet=0.2, fftsize=2048;
    var in = \in.ar(0!2);
    var irbuffer = \irbuffer.ir(0);
    var l = (PartConv.ar(in[0], 2048, irbuffer, 0.5) * drywet);
    var r = (PartConv.ar(in[1], 2048, irbuffer, 0.5) * drywet);
    var output = (in * (1 - drywet)) + [l, r];

    Out.ar(out, output);
  }).add;
)
