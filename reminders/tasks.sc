"Setup.scd".load;

(
  ~mytask = Task({
    ["go", thisThread.clock.beats].postln;
    inf.do({ arg i;
        1.wait;
        [ "wake up", i ].postln;
    });
  });
)
