InfoServer {
  var oscInPort, oscOutPort, host;
  var listeners;
  *new { |oscInPort, oscOutPort, host = "127.0.0.1"|
    	^super.newCopyArgs(oscInPort, oscOutPort, host).init
  }

  init {
    listeners = [];

    listeners.add({ |msg|
    }, '/info/synth');
  }

}
