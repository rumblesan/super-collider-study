
"Setup.scd".load;

(
  ~fmbass = Pbind(
    \instrument, \fm2,
    \freq, 100,
    \pitchEnvDepth, 5,
    \pitchEnvDecay, 0.05,
    \pitchEnv, 0.3,
    \modDepth, 0.5,
    \bend, 0,
    \attack, 0.25,
    \decay, 3,
    \legato, 0.6,
    \ratio, 0.49,
    \amp, 0.4,
    \dur, 8,
  )
)
~fmbass.quant = 8
~fmbass.play
~fmbass.clear

~fmbass.map(\bend, ~penv1)

(
  ~penv1[0] = {
    |attack=0.01, decay=0.1, t_trig=1|
    Env.perc(attack, decay).kr(gate: t_trig)
  };
  ~penv1[1] = \set -> Pn(Pfindur(16, Pbind(
    \t_trig, 1,
    \attack, Pwrand([0.01, 0.1], [20, 1].normalizeSum, inf),
    \decay, Pwrand([0.1, 0.3, 0.01], [10, 3, 3].normalizeSum, inf),
    \dur, 0.5,
  )) , inf)
)

~penv1.scope
~penv1.clear
