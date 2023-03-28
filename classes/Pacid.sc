Pacid : Pattern {
  var <>patternpairs;

	*new { arg ... pairs;
		if (pairs.size.odd, { Error("Pacid should have even number of args.\n").throw; });
		^super.newCopyArgs(pairs)
	}

  storeArgs { ^patternpairs }
  embedInStream { |inevent|

    var streampairs = Dictionary.newFrom(patternpairs);
		var config = Dictionary.newFrom([
      \degree, Pseq([0], inf),
      \pulses, Pseq([1], inf),
      \types, Pseq(".", inf),
      \dur, 0.25,
      \repeats, inf,
    ]);

    var event = inevent.copy;
    var pitchStream;
    var pulseStream;
    var typeStream;
    var pulseDuration;

    config.keys.do({|key|
      if (streampairs.trueAt(key) != false, {
        config.put(key, streampairs.at(key))
      });
    });

    pitchStream = config[\degree].asStream;
    pulseStream = config[\pulses].asStream;
    typeStream = config[\types].asStream;
    pulseDuration = config[\dur];

    if (inevent.isNil) { ^nil.yield };

    config[\repeats].value(event).do({ |r, i|
      var pulseCount = pulseStream.next(event);
      if (pulseCount > 0, {
        var gateType = typeStream.next(event);
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
