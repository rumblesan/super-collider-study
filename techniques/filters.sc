
"Setup.scd".load;

~lfo1 = {((SinOsc.kr(1) + 1) * 100) + 100}

(
  ~resfilt = { |freq=100, cutoff=100, res=1|
    var osc = Saw.ar(freq);
    RLPF.ar(osc, cutoff, res.reciprocal)
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
