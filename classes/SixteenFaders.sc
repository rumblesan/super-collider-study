/**
* Based on 16n Supercollider Class
* by vsandstrom
* https://github.com/vsandstrom
*/

SixteenFaders {
	classvar func;
	var <fader, <proxy, scaling, verbose = false;

	*new {
		^super.new.init();
	}

	// Ignore the first one created so that
	// indexing starts at 1
	init {
		fader = 17.collect{
			Bus.control(Server.default, 1);
		};

		proxy = fader.collect{|f|
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
		if (curve == \exp,
			{ fader[faderPosition].set(value.linexp(0, 127, minVal, maxVal)) },
			{ fader[faderPosition].set(value.linlin(0, 127, minVal, maxVal)) },
		)
	}

	setScaling {|faderPositon, minVal, maxVal, curve=\lin|
		if ((curve == \exp) && (minVal <= 0), {minVal = minVal + 0.001});
		scaling[faderPositon] = [minVal, maxVal, curve];
	}

	faderAt {|faderPosition|
			^fader[faderPosition];
	}

	proxyAt {|faderPosition|
			^proxy[faderPosition];
	}

	enableVerbose {
		verbose = true;
	}

	disableVerbose {
		verbose = false;
	}

	usage {
      var usage = "Usage: A 'Bus' object is accessed by <instance of object>.fader[n] or \n<instance of object>.faderAt(n). 'n' is the corresponding fader number, \nbut zero-indexed. ('0' gets you the first fader)\n";
        Post << "|----------------------------------------------------------------------//\n" << usage << "|----------------------------------------------------------------------//\n";
	}
}
