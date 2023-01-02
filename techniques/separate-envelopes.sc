
"Setup.scd".load;
s.plotTree

p.clock.tempo = 150/60;

(
  ~synth = Pbind(
    \instrument, \hd,
    \amp, 0.8,
    \mod, 1,
    \fold, 0.1,
    \modDepth1, Pwrand([0, 0.1, 0.7], [10, 2, 1].normalizeSum, inf),
    \foldDepth1, 0.1,
    \ratio1, 1.0,
    \modDepth2, 0.1,
    \ratio2, 1.5,
    \attack, 0.01,
    \decay, Pwrand([0.05, 0.3], [10, 3].normalizeSum, inf),
    \octave, Pwrand([2, 4], [1, 7].normalizeSum, inf),
    \legato, 0.1,
    [\degree, \dur], Pmetro(
      Pseq([0,0,7,0, 3], inf),
      Pseq([2,2,1,1,3,1], inf),
      "*.*..*.",
      inf,
      0.25
    ),
  )
)
~synth.quant = 4

~synth.play;
~synth.clear;

(
  ~penv = Pcontrol(
    \percenv,
    \dur, 2,
  )
)
~synth.map(\bend, ~penv)

(
  ~extenv[0] = {|attack=0.01, decay=0.1, t_trig=1| Env.perc(attack, decay).kr(gate: t_trig) };
  ~extenv[1] = \set -> Pbind(
    \t_trig, 1,
    \dur, Pseq([1.0, 0.25, 0.25, Rest(1.5)], inf),
  );
)

~synth.map(\bend, ~extenv);

(
  ~extenv2[0] = {|attack=0.01, decay=0.3, t_trig=1| Env.perc(attack, decay).kr(gate: t_trig) };
  ~extenv2[1] = \set -> Pbind(
    \t_trig, 1,
    \dur, Pseq([1.0, 0.25, 0.25, Rest(1.5)], inf),
  );
)

~synth.map(\modBend1, ~extenv2);
