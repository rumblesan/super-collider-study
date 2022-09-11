# Questions

What is actually happening with a proxied Pbind?


How does timing for Events actually work?


## Answered

Can I get supercollider to tell me what class an object is?
`obj.class` gives the classname as a string

How does audio get sent around?
What does `thing.play` actually do?
ProxySynthDefs get Busses assigned to them on creation that their audio is always playing on. When the `play` method is called, a new Monitor is created that handles sending the audio to the destination.
