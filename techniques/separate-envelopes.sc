
"Setup.scd".load;
s.plotTree

(
  ~synth = Pbind(
    \instrument, \hd,
    \amp, 0.8,
    \mod, 0.3,
    \fold, 0.1,
    \modDepth1, 1,
    \foldDepth1, 2.3,
    \ratio1, 1.5,
    \modDepth2, 0.1,
    \ratio2, 0.5,
    \freq, Pseq([200, 250, 300, 350], inf),
    \dur, 1,
  )
)
~synth.quant = 4

~synth.play;
~synth.clear;


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
