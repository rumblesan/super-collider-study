Pacid : Pattern {
  var pitches, pulses, types, repeats, pulseDuration;
  *new { |pitches, pulses, types, repeats, pulseDuration=0.25|
    	^super.newCopyArgs(pitches, pulses, types, repeats, pulseDuration)
  }

  storeArgs { ^[pitches, pulses, types, pulseDuration, repeats] }
  embedInStream { |inevent|

    var event = inevent.copy;
    var pitchStream = pitches.asStream;
    var pulseStream = pulses.asStream;
    if (inevent.isNil) { ^nil.yield };

    repeats.value(event).do({ |r, i|
      var pulseCount = pulseStream.next(event);
      if (pulseCount > 0, {
        var gateType = types.wrapAt(i);
        var pitch    = pitchStream.next(event);
        if (pitch.isNil) { ^inevent.yield };
        switch(
          gateType,
          $-, {
            inevent = event.putPairs([
              \degree, pitch,
              \dur, pulseCount * pulseDuration
            ]).yield;
          },
          $*, {
            pulseCount.do({
              inevent = event.putPairs([
                \degree, pitch,
                \dur, pulseDuration
              ]).yield;
            });
          },
          $., {
            inevent = event.putPairs([
              \degree, pitch,
              \dur, pulseDuration
            ]).yield;
            if (pulseCount > 1, {
              inevent = event.putPairs([
                \degree, pitch,
                \dur, Rest((pulseCount - 1) * pulseDuration)
              ]).yield;
            })
          },
          {
            inevent = event.putPairs([
              \degree, pitch,
              \dur, Rest(pulseCount * pulseDuration)
            ]).yield;
          }
        );
      });
    });
    ^inevent;
  }
}
