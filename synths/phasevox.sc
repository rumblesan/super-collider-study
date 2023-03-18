
(
  SynthDef(\phasevox, {|out=0, buf, position, rate, shift|

    var binbuf = LocalBuf.new(2048, 1);
    var hop = (rate / 2);
    var audio = PlayBuf.ar(1, buf, BufRateScale.ir(buf) * rate, 1 BufDur.kr(buf) * position * 44100);

    var signal = (LocalIn.ar(1) * feedback) + in;
    var bufferLength = \bufferLength.ir(5);
    var grainAudio, output;
    var frames = bufferLength * SampleRate.ir;
    var buffer = LocalBuf.new(frames, 1);
    var phase = Phasor.ar(0, 1, 0, frames);
    var trigs = Impulse.ar(density) + Dust.ar(randDensity);
    var pos = (
      (((phase / frames)
        - position)
        - ControlDur.ir)
      - Demand.ar(trigs, 0, Diwhite((spread.neg * 100), (spread * 100)) / 100)
    ).wrap(0, 1);

    BufWr.ar(signal, buffer, phase, loop: 1);

    grainAudio = GrainBuf.ar(1, trigs, size, buffer, rate, pos, 2, 0);
    LocalOut.ar(grainAudio);
    output = Mix.new([
      grainAudio * drywet,
      in * (1 - drywet)
    ]);
    Out.ar(out, output);
  }).add;
)
