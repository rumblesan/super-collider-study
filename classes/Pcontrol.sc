Pcontrol : Pmono {
	buildForProxy { | proxy, channelOffset = 0 |
		var player, event;
		player = this.asEventStreamPlayer;
		proxy.initBus(\control);
		event = player.event.buildForProxy(proxy, channelOffset);
		^event !? { player };
	}
}

NPatControl {
	*new { |name ... args|
		^Ndef(name, Pcontrol(*args))
	}
}
