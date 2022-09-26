# Questions

## Architecture
Confirm basic structure for how super collider works.
IDE -> sclang -> scserver
yep, this is it

## Proxy Space
What is an NDef actually used for?
Is it just a ProxySpace thing?
How does it differ from just declaring a proxy synth?

`Ndef(\foo, {SinOsc.ar(10)})`
is the same thing as
`~foo = {SinOsc.ar(10)}`

investigate `~c.ar` vs `~c`

What is actually happening with a proxied Pbind?
How does timing for Events actually work?

Is all timing handled by sclang? yes

How does quantisation work?
works just fine
`~proxy.quant = 4`

look into offset

## Synth Programming
Pmono vs PmonoArtic
Envelopes gates and triggers


## Audio Programming
How does feedback (in a delay for example) work in proxy space.
LocalIn doesn't seem to work nicely in NodeProxy synths, but proxy feedback works kind of like this
needs a bit of investigation though
```
~a = {~c.ar}
~b = {~a.ar}
~c = {~b.ar}
```


## Answered well enough
How to better handle changes to effects synths with longer crossfade times?
(Improve delays with feedback cutting to silence momentarily when they're replaced)
Sort of answer...
`~synth.fadeTime = 4;`
Just set it high enough and it seems fine.


Can I get supercollider to tell me what class an object is?
`obj.class` gives the classname as a string


How does audio get sent around?
What does `thing.play` actually do?
ProxySynthDefs get Busses assigned to them on creation that their audio is always playing on. When the `play` method is called, a new Monitor is created that handles sending the audio to the destination.

Why does the amplitude of synths being played by Pbind seem so much quieter than expected?
Default \amp is set in the *Event* class to be 0.1.


## Debugging
Is there a way to see what OSC messages are being sent to the server?
Is there an easy way to see the contents of the events that are being played?

s.dumpOSC(1)
will dump out all the OSC message bundles received by the server
