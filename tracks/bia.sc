
"Setup.scd".load;
m = ProxyMixer(p, 8);
s.plotTree

(
SynthDef(\bia, {arg out=0, freq=50, t_trig=0,
  wave=0,
  attack=0.01, decay=0.5,
  pattack=0.01, pdecay=0.5, mod=0,
  fold=1;
  var sinosc, pulseosc, sawosc, oscs,
  env, penv,
  snd, folded;
  env = Decay2.kr(t_trig, attack, decay);
  penv = Decay2.kr(t_trig, pattack, pdecay) * mod;
  sinosc = SinOsc.ar(freq * (1 + penv));
  pulseosc = Pulse.ar(freq * (1 + penv));
  sawosc = Saw.ar(freq * (1 + penv));
  oscs = [sinosc, pulseosc, sawosc];
  snd = LinSelectX.ar(wave * 3, oscs);
  folded = Fold.ar(snd, (fold * -1), fold) * (1/fold);
  Out.ar(out, folded * env);
}).add;
)

p.clock.tempo = 180/60;

(
  ~go = Pmono(
    \bia,
    \decay, 1.9,
    \octave, 2,
    \degree, 0,
    \mod, Pseq([3, 20, 0, 0.1], inf),
    \pdecay, Pseq([0.1, 0, 3, 0.1, 0.1, 0.2], inf),
    \scale, Scale.harmonicMinor,
    \dur, Prand([0.5], inf),
    \fold, 0.3,
    \wave, Pseq([0.3, 0.1, 0.3, 0,7], inf),
  )
)
~go.play;
~go.clear;
