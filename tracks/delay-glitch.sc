
"Setup.scd".load;

s.plotTree;

(
  SynthDef(\ping, {arg out=0, freq=220, amp=1, decay=0.5, t_ptrig=0, pdecay=0.1, pmod=0;
    var osc, env, penv;
    env = Env.perc(0.01, decay, amp).kr(doneAction: 2);
    penv = Decay2.kr(t_ptrig, 0.01, pdecay) * pmod;
    osc = SinOsc.ar(freq * (penv + 1));
    Out.ar(out, osc * env);
  }).add;
)


(
  ~ping = Pbind(
    \instrument, \ping,
    \decay, Pwrand([0.4, 1], [10, 2].normalizeSum, inf),
    \freq, Pwrand([220, 440, 55, 550], [15, 3, 1].normalizeSum, inf),
    \dur, Pseq([4, 4, 1, 0.25, 0.25, 0.5], inf),
    \ptrig, Pseq([1, 1, 0, 1, 1, 0, 0], inf),
    \pmod, Prand([1, 0, 0, 3, 7, 0], inf),
    \pdecay, Prand([0.05], inf),
    \amp, 1,
  )
)
~ping.play;
~ping.stop;
~ping.pause;
~ping.resume;
~ping.clear;

(
  SynthDef(\clikr, {arg out, gate=1, attack=0.0, decay=1, freq=200, amp=1;
    var env = Env.asr(attack, amp, decay).kr(doneAction: 2, gate: gate);
    var osc = Blip.ar(freq, 200);
    Out.ar(out, osc * env);
  }).add;
)

(
  ~click = Pbind(
    \instrument, \clikr,
    \dur, Pwrand([1, 0.5, 0.25], [3, 6, 19].normalizeSum, inf),
    \freq, Pwrand([220, 55, 200], [20, 3, 10].normalizeSum, inf),
    \amp, 1,
    \decay, Pwrand([0.1, 0.5, 1], [15, 1, 4].normalizeSum, inf),
    \sustain, Prand([0.1, 0.5, 0.03], inf),
  )
)
~click.play;
~click.stop;
~click.clear;
~click.plot;

~bass = {SinOsc.ar(90)}

~delaymix = { ~ping }
~delaymix = { (~ping * 1.1) + (~click * 0.5)}

~delayed[0] = \simpledelay;
~delayed <<> ~delaymix

~delayed.play
~delayed.clear
~delayed.stop

(
  ~delayed[1] = \pset -> Pbind(
    \delay, Pseq([0.4, 0.9, Prand([0.01, 0.1])], inf),
    \feedback, Pwrand([0.1, 0.4, 0.98], [2, 10, 1].normalizeSum, inf),
    \dur, 0.25,
  )
)
