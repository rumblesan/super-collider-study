
"Setup.scd".load;
s.plotTree

(
  SynthDef(\hd, {arg out, freq=50, gate=1, amp=0.1,
    mod=0, fold=0, bend=0,
    attack=0.1, decay=0.5,
    ratio1=1, ratio2=1,
    modDepth1=0, modDepth2=0, mod21Depth=0,
    modBend1=0, modBend2=0,
    foldDepth1=0, foldDepth2=0
    ;
    var venv, modulator2, modulator1, carrier, folded;
    venv = Env.asr(attack, amp, decay).kr(2, gate);

    modulator2 = SinOsc.ar(freq * ratio2 * (1 + modBend2));
    modulator1 = SinOsc.ar(
      (freq * ratio1 * (1 + modBend1)) +
      (freq * mod21Depth * modulator2 * mod)
    );

    carrier = SinOsc.ar(
      (
        freq +
        (modulator1 * modDepth1 * mod * freq) +
        (modulator2 * modDepth2 * mod * freq)
      ) * (1 + bend));

    folded = Fold.ar(
      carrier *
      (1 +
        (
          (foldDepth1 + 1) * mod *
          (foldDepth2 + 1) * mod
        )
      ) * (1 + fold),
      -1.0, 1.0);

    Out.ar(out, folded * venv);
  }).add;
)

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
