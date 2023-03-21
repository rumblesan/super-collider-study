/**
* Based on 16n Supercollider Class
* by vsandstrom
* https://github.com/vsandstrom
*/

FaderProxy {
	var mode, <bus, <proxy, <>verbose;
	*new { |bus, proxy, mode=\bus|
		^super.newCopyArgs(mode, bus, proxy)
	}

	fader {
		switch(mode,
			\bus, {bus},
			\proxy, {proxy},
			{proxy}
		)
	}

	value { this.fader }

}

SixteenFaders {
	classvar func;
	var server, <mode, <busses, <proxy, scaling, verbose = false;

	*new { |server, mode=\bus|
		^super.newCopyArgs(server, mode).init();
	}

	// Ignore the first one created so that
	// indexing starts at 1
	init {
		busses = 17.collect{
			Bus.control(server, 1);
		};

		proxy = busses.collect{|f|
			BusPlug.for(f);
		};

		scaling = 17.collect{
			[0, 1, \lin];
		};

		this.connect();
	}

	connect {
		var found = "16n found";
		var id = 0;
		var midilist = [];
		MIDIClient.init();
		MIDIIn.connectAll;
		midilist = MIDIClient.sources;

		// Check if 16n is in MIDIEndPoints, store uid in global var.
		(
			for(0, midilist.size - 1, {
				arg i;
				if (midilist[i].asString == MIDIEndPoint("16n", "16n").asString, {
					id = midilist[i].uid;
					MIDIIn.connectAll;
					found.postln;
					};
				)};
			);
		);

		// MIDIdef with correct midi \uid (srcID)
		(
			if (func.isNil) {
				MIDIFunc.new({
					|val, num, chan, src|

					if (verbose) {
						("***	Fader: " ++ '[ ' ++ (num - 32) ++ ' ]' ++
						"	Value: " ++ '[ ' ++ val ++ ' ]').postln;
					};

					switch(num,
						32, { this.prSetFader(1, val) },
						33, { this.prSetFader(2, val) },
						34, { this.prSetFader(3, val) },
						35, { this.prSetFader(4, val) },
						36, { this.prSetFader(5, val) },
						37, { this.prSetFader(6, val) },
						38, { this.prSetFader(7, val) },
						39, { this.prSetFader(8, val) },
						40, { this.prSetFader(9, val) },
						41, { this.prSetFader(10, val) },
						42, { this.prSetFader(11, val) },
						43, { this.prSetFader(12, val) },
						44, { this.prSetFader(13, val) },
						45, { this.prSetFader(14, val) },
						46, { this.prSetFader(15, val) },
						47, { this.prSetFader(16, val) },

					)
				}, msgNum: Array.series(16,32,1), chan: 0, msgType: \control, srcID: id);

			}

		);
	}

	prSetFader {|faderPosition, value|
		var faderScaling = scaling[faderPosition];
		var minVal = faderScaling[0];
		var maxVal = faderScaling[1];
		var curve = faderScaling[2];
		switch(curve,
			\lin, { busses[faderPosition].set(value.linlin(0, 127, minVal, maxVal)) },
			\log, { busses[faderPosition].set(value.linlin(0, 127, 1, 10).log10.linlin(0, 1, minVal, maxVal)) },
			\vol, {
				if (value == 0,
					{busses[faderPosition].set(0)},
					{busses[faderPosition].set(value.linlin(0, 127, minVal, maxVal).dbamp)}
				)
			},
			\exp, { busses[faderPosition].set(value.linexp(0, 127, minVal, maxVal)) },
			{ busses[faderPosition].set(value.linexp(0, 127, minVal, maxVal)) }
		)
	}

	scale {|faderPosition, minVal, maxVal, curve=\lin|
		switch(curve,
			\lin, {scaling[faderPosition] = [minVal, maxVal, curve]},
			\log, {scaling[faderPosition] = [minVal, maxVal, curve]},
			\vol, {scaling[faderPosition] = [minVal, maxVal, curve]},
			\exp, {scaling[faderPosition] = [minVal + 0.001, maxVal, curve]},
			{scaling[faderPosition] = [minVal, maxVal, curve]}
		)
		^FaderProxy(this.busAt(faderPosition), this.proxyAt(faderPosition), mode);
	}

	at {|faderPosition|
		^this.fader(faderPosition);
	}

	fader {|faderPosition|
		switch(mode,
			\bus, {^this.busAt(faderPosition)},
			\proxy, {^this.proxyAt(faderPosition)},
			{^this.proxyAt(faderPosition)}
		)
	}

	busAt {|faderPosition|
			^busses[faderPosition];
	}

	proxyAt {|faderPosition|
			^proxy[faderPosition];
	}
}
