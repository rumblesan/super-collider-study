
"Setup.scd".load;

~lfo1 = {((SinOsc.kr(1) + 1) * 100) + 100}

(
  ~resfilt = { |freq=100, eq=100, rq=1, gain=0|
    var osc = Saw.ar(freq);
    BPeakEQ.ar(osc, eq, rq.reciprocal, gain)
  }
)

~resfilt.set(\res, 20)
~resfilt.set(\cutoff, 200)
~resfilt.map(\cutoff, ~lfo1)
~resfilt.play

~resfilt.plot(5)

~lfo2 = {SinOsc.kr(1) * 0.5 + 0.49}

(
  ~onepolefilt = { |freq=100, coef=0.5|
    var osc = Saw.ar(freq);
    OnePole.ar(osc, coef)
  }
)

~onepolefilt.set(\coef, 0.95)
~onepolefilt.map(\coef, ~lfo2)
~onepolefilt.play
