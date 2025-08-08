
Ndef(\grain1, {|
  density = 1,
  pitch = 1,
  dur = 0.1,
  centre = 0,
  gain = 1,
  buffer = nil,
  |
  var trigger = Impulse.kr(density);
  gain * TGrains.ar(1, trigger, buffer, pitch, centre, dur);
})
