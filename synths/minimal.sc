
(

SynthDef(\ping, {arg out=0, freq=200, amp=1, decay=0.5, t_ptrig=0, pdecay=0.1, pmod=0;
  var osc, env, penv;
  env = Env.perc(0.01, decay, amp).kr(doneAction: 2);
  penv = Decay2.kr(t_ptrig, 0.01, pdecay) * pmod;
  osc = SinOsc.ar(freq * (penv + 1));
  Out.ar(out, osc * env);
}).add;

)
