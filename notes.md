# Notes to self

## Proxy space basics

```
~synth = Pbind(etc)
// Synth/Pattern is now playing but output isn't being sent to main out


~synth.play;
// Output now being sent to main out

~synth.stop;
// Synth/Pattern still playing but no audio being sent

~synth.clear;
// Synth/Pattern removed

```

## BPM

```
p.clock.tempo = 180/60;
```
