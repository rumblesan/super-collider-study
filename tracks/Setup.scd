
(

s.boot;

p = ProxySpace.push(s);
//start tempo clock
p.makeTempoClock;
//give proxyspace a tempo
p.clock.tempo = 2;

Task({
  3.wait;

  StageLimiter.activate;
  "Ready to go!".postln;
}).start;

)
