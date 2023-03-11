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

  d = Dictionary.new;
  d.add(\wavetables -> Dictionary.new);
  d[\wavetables][\ph1] = WaveTableFile.new(s, "./wavetables/pistonhonda/1.wav", 256, 64);
  d[\wavetables][\ph2] = WaveTableFile.new(s, "./wavetables/pistonhonda/2.wav", 256, 64);
  d[\wavetables][\ph3] = WaveTableFile.new(s, "./wavetables/pistonhonda/3.wav", 256, 64);
  d[\wavetables][\ph4] = WaveTableFile.new(s, "./wavetables/pistonhonda/4.wav", 256, 64);
  d[\wavetables][\ph5] = WaveTableFile.new(s, "./wavetables/pistonhonda/5.wav", 256, 64);
  d[\wavetables][\ph6] = WaveTableFile.new(s, "./wavetables/pistonhonda/6.wav", 256, 64);
  d[\wavetables][\ph7] = WaveTableFile.new(s, "./wavetables/pistonhonda/7.wav", 256, 64);
  d[\wavetables][\ph8] = WaveTableFile.new(s, "./wavetables/pistonhonda/8.wav", 256, 64);

  PathName("./samples").entries.do({ |folderPath|
    var k = folderPath.folderName.asSymbol;
    var samples = SampleFolder.new(s, folderPath);
    "loaded % samples from % to %\n".postf(samples.size, folderPath, k);
    d.add(k -> samples);
  });

  "./synths/*.sc".loadPaths(warn: true, action: {|name| "Loaded file %\n".postf(name)});

  StageLimiter.activate;

  f = SixteenFaders.new;

  "Ready to go!".postln;
}).start;

)
