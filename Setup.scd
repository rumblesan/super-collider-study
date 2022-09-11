(

//increase number of buffers the server has access to for loading samples
s.options.numBuffers = 1024 * 16;

//increase the memory available to the server
s.options.memSize = 8192 * 64;

s.options.numInputBusChannels = 0;

s.boot;


p = ProxySpace.push(s);
//start tempo clock
p.makeTempoClock;
//give proxyspace a tempo
p.clock.tempo = 150/60;
p.quant = 1.0;


Task({
  3.wait;

  "./synths/*.sc".loadPaths(warn: true, action: {|name| "Loaded file %\n".postf(name)});

  StageLimiter.activate;
  "Ready to go!".postln;
}).start;

)
