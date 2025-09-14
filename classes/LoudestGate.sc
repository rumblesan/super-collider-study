LoudestGate {
  *new {
    ^{ |inputs|
      var rms = inputs.collect({|in| RMS.ar(in)});
      var arrMax = ArrayMax.ar(rms);
      var maxIdx = arrMax[1];
      LinSelectX.ar(maxIdx, inputs);
    }
  }
}
