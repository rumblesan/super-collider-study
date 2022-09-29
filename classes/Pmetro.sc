Pmetro : Pattern {
  var pitches, pulses, types, repeats, pulseDuration;
  *new { |pitches, pulses, types, repeats, pulseDuration=0.25|
    	^super.newCopyArgs(pitches, pulses, types, repeats, pulseDuration)
  }

  storeArgs { ^[pitches, pulses, types, pulseDuration, repeats] }
  embedInStream { |inval|

    var pitchStream = pitches.asStream;
    var pulseStream = pulses.asStream;

    repeats.value(inval).do({ |r, i|
      var pulseCount = pulseStream.next(inval);
      if (pulseCount > 0, {
        var gateType = types.wrapAt(i);
        var pitch    = pitchStream.next(inval);
        switch(
          gateType,
          $-, { inval = [pitch, pulseCount * pulseDuration].yield },
          $*, {
            pulseCount.do({
              inval = [pitch, pulseDuration].yield
            });
          },
          $., {
            inval = [pitch, pulseDuration].yield;
            if (pulseCount > 1, {
              inval = [pitch, Rest((pulseCount - 1) * pulseDuration)].yield;
            })
          },
          {
            inval = [pitch, Rest(pulseCount * pulseDuration)].yield
          }
        );
      });
    });
    ^inval;
  }
}
