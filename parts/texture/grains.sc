
Ndef(\grains1, {
    var rate = \rate.ar(10);
    var trigger = Impulse.ar(rate);
    var dur = (1.0 / rate) * \durmod.ar(1.0);
    var b = d[\milkwood][3];
    var centerPos = \pos.ar(0.1) * BufFrames.kr(b) * BufSampleRate.kr(b);
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
Ndef(\grains1).set(\rate, 3);
Ndef(\grains1).map(\rate, Ndef(\ratemod));
Ndef(\grains1).map(\pos, Ndef(\posmod));

Ndef(\grains1).map(\pos, Ndef(\posmod))
NpatControl(\posmod,
  \mod,
  \value, Pseq([0.3, 0.31, 0.32, 0.5], inf),
  \slew, 2,
  \dur, 4
)

Ndef(\grains1).map(\rate, Ndef(\ratemod))
NpatControl(\ratemod,
  \mod,
  \value, Pseq([1, 20, 1, 30], inf),
  \slew, 0,
  \dur, Pseq([3, 1, 5, 1], inf)
)
