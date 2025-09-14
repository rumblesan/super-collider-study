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
    p.fadeTime = 0.001;

    r = Rest(1);

    d = Dictionary.new;
    d.add(\wavetables -> Dictionary.new);
    List[
      (\ph1 -> "wavetables/pistonhonda/1.wav"),
      (\ph2 -> "wavetables/pistonhonda/2.wav"),
      (\ph3 -> "wavetables/pistonhonda/3.wav"),
      (\ph4 -> "wavetables/pistonhonda/4.wav"),
      (\ph5 -> "wavetables/pistonhonda/5.wav"),
      (\ph6 -> "wavetables/pistonhonda/6.wav"),
      (\ph7 -> "wavetables/pistonhonda/7.wav"),
      (\ph8 -> "wavetables/pistonhonda/8.wav"),
    ].do({ |pair|
      var key = pair.key;
      var filePath = pair.value.resolveRelative;
      WaveTableFile.loadFolderAsync(s, filePath, 64, 256, { |waveTableFile|
        d[\wavetables][key] = waveTableFile;
        "Wavetables:         loaded wavetables from % to %\n".postf(filePath, key);
      });
    });

    Continuation(
      PathName("samples".resolveRelative).entries,
      { |folderPath, continuation|
        "Samples:            loading from %\n".postf(folderPath);
        SampleFolder.loadFolderAsync(s, folderPath, {| sampleFolder |
          var k = folderPath.folderName.asSymbol;
          d.add(k -> sampleFolder);
          "Samples:            loaded % samples to %\n".postf(sampleFolder.size, k);
          continuation.value;
        });
      },
      { "Samples:            finished loading".postln },
    ).value();

    Continuation(
      "synths/*.sc".resolveRelative.pathMatch,
      { |synthFilePath, continuation|
        "Synths:             loading %\n".postf(synthFilePath);
        synthFilePath.loadPaths(warn: true, action: {|name|
          "Synths:             loaded %\n".postf(name);
          0.01.wait;
          continuation.value;
        });
      },
      { "Synths:             finished".postln; },
    ).value();

    Continuation(
      "modulators/*.sc".resolveRelative.pathMatch,
      { |synthFilePath, continuation|
        "Modulators:         loading %\n".postf(synthFilePath);
        synthFilePath.loadPaths(warn: true, action: {|name|
          "Modulators:         loaded %\n".postf(name);
          0.01.wait;
          continuation.value;
        });
      },
      { "Modulators:         finished".postln; },
    ).value();

    Continuation(
      "effects/*.sc".resolveRelative.pathMatch,
      { |synthFilePath, continuation|
        "Effects:            loading %\n".postf(synthFilePath);
        synthFilePath.loadPaths(warn: true, action: {|name|
          "Effects:            loaded %\n".postf(name);
          0.01.wait;
          continuation.value;
        });
      },
      { "Effects:            finished".postln; },
    ).value();

    ImpulseResponseFolder.loadFolderAsync(
      s,
      PathName("irs".resolveRelative),
      [512, 2048],
      { |irs|
        "Impulse Responses:  loaded % from %\n".postf(
          irs.lookup.size, irs.folderPath,
        );
        d.add(\irs -> irs);
      }
    );

    StageLimiter.activate;
    "Ready to go!".postln;
  });

}).start;

)
