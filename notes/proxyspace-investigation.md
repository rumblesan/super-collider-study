# ProxySpace Investigation

## Basics

```
~abc = 10;
```

`~abc = 10` is equivalent to `currentEnvironment.put(\abc, 10);`

`put` method is on *LazyEnvir* class which *ProxySpace* class inherits from.

If variable doesn't exist then a new *NodeProxy* object is made to store the object.
*NOTE* code path for this is somewhat convoluted.
`put` method called on *ProxySpace* is actually the implementation on *LazyEnvir* which calls `at` method on the same object.
That then calls `makeProxy` implementation on the original *ProxySpace* object, which is where the *NodeProxy* constructor is called.

The object being stored in the global variable is then stored in the *NodeProxy* using the `source_` method, which is just a wrapper around `put`.

The `put` method is where all the logic for having audio sources work in proxy space lives (This may not be quite true, do some more digging).
The object gets wrapped in a control proxy class, with the object itself being stored in the `source` instance variable, and the `build` method then called on it.
Which control proxy class is used depends on the type of object being stored, and is decided by a set of extension methods in the **ProxySpace/wrapForNodeProxy.sc** file.
Going to assume here that the object being used here is a *Function* and so the control class is a *SynthDefControl*.

In the *SynthDefControl* `build` method, a *ProxySynthDef* class is created which wraps a *SynthDef*.
Internally a new *Bus* (with as many channels as the synth has) is allocated and stored on the *NodeProxy*.


*TLDR*
All objects created if global `~` variables are stored as *NodeProxies* in the *ProxySpace* environment.



## Play method

*NodeProxy* class inherits from *BusPlug* which has the `play` method that allows monitoring the audio.

When the `play` method gets called, a new *Monitor* gets created which handles sending the audio to the required destination bus.
