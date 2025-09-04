
Ndef(\grains1, {
    var rate = \rate.ar(10);
    var trigger = Impulse.ar(rate);
    var dur = (1.0 / rate) * \durmod.ar(1.0);
    var b = d[\milkwood][15];
    var centerPos = \pos.ar(0.1) * b.numFrames * b.sampleRate;
    TGrains.ar(
      2,
      trigger,
      b,
      \pitch.ar(1.0),
      centerPos,
      dur,
      \pan.ar(0.0),
      1.0,
      4
    )
  };
)

Ndef(\grains1).set(\pos, 0.31);
Ndef(\grains1).set(\durmod, 1);
Ndef(\grains1).set(\rate, 33.5);
Ndef(\grains1).map(\rate, Ndef(\ratemod));
Ndef(\grains1).map(\pos, Ndef(\posmod));
