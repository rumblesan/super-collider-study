MidSide {
  *new { |in, msBalance=0|
    var sig = Balance2.ar(in[0] + in[1], in[0] - in[1], msBalance);
    ^([sig[0] + sig[1], sig[0] - sig[1]] * sqrt (
      (msBalance.max(0) + 1) / 2
    ))
  }
}
