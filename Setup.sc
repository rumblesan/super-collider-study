(

Task({
  //increase number of buffers the server has access to for loading samples
  s.options.numBuffers = 1024 * 8;

  //increase the memory available to the server
  s.options.memSize = 8192 * 32;

  s.options.numInputBusChannels = 0;

  s.waitForBoot({

    "Server Booted".postln;

    p = Ndef.dictFor(s);
    //start tempo clock
    p.makeTempoClock;
    //give proxyspace a tempo
    p.clock.tempo = 150/60;
    p.quant = 4.0;

    d = Dictionary.new;
    d.add(\wavetables -> Dictionary.new);

    "Loading Wavetables".postln;
    List[
      (\ph1 -> "wavetables/pistonhonda/1.wav"), (\ph2 -> "wavetables/pistonhonda/2.wav"),
      (\ph3 -> "wavetables/pistonhonda/3.wav"), (\ph4 -> "wavetables/pistonhonda/4.wav"),
      (\ph5 -> "wavetables/pistonhonda/5.wav"), (\ph6 -> "wavetables/pistonhonda/6.wav"),
      (\ph7 -> "wavetables/pistonhonda/7.wav"), (\ph8 -> "wavetables/pistonhonda/8.wav"),
    ].do({ |pair|
      var key = pair.key;
      var filepath = pair.value.resolveRelative;
      d[\wavetables][key] = WaveTableFile.new(s, filepath, 256, 64, {
        "    loaded wavetables from %\n".postf(filepath);
      });
    });

    "Loading Samples".postln;
    Continuation.iterator(
      PathName("samples".resolveRelative).entries,
      { |folderPath, continuation|
        var k = folderPath.folderName.asSymbol;
        var samples = SampleFolder.new(s, folderPath, {
          "    loaded % samples from % to %\n".postf(samples.size, folderPath, k);
          continuation.value;
        });
        d.add(k -> samples);
      },
    ).value({ "Finished Loading Samples".postln });

    "Loading Impulse Responses".postln;
    d.add(\irs -> ImpulseResponseFolder(
      s,
      PathName("irs".resolveRelative),
      [512, 2048],
      { |irs|
        "    loaded % impulse responses from % to %\n".postf(
          irs.lookup.size, irs.folderPath, \irs
        );
      }
    ));

    "Loading Synths".postln;
    Continuation.iterator("synths/*.sc".resolveRelative.pathMatch,
      { |synthFilePath, continuation|
        synthFilePath.loadPaths(warn: true, action: {|name|
          "    loaded synth file %\n".postf(name);
          0.01.wait;
          continuation.value;
        });
      },
    ).value({ "Finished Loading Synths".postln });

    StageLimiter.activate;

    "Ready to go!".postln;

  });

}).start;

)
