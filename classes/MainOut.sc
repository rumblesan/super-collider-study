MainOut {
  *compressor { |wet=0.36, makeup=0.98, locut=40|
		^{
			var lfreq = 250, hfreq = 4000, q = 1.5;
			var dry, low, mid, high;
			var att = 20/1000;
			var lrel = 137/1000, lpre = dbamp(4.4*wet), lpos = dbamp(6*wet), lexp = 0.07*wet, lstereo = -0.8*wet;
			var mrel = 85/1000, mpre = dbamp(6*wet), mpos = 1.32, mstereo = 0.3*wet;
			var hrel = 75/1000, hpre = dbamp(6.3*wet), hpos = dbamp(4.1*wet), hexp = 0.1*wet, hstereo = 0.2, hsat = 1/12*wet;

			dry = \in.ar(0!2);
			dry = LeakDC.ar(dry,0.996);

			low = BLowPass4.ar(dry,lfreq,q);
			mid = BHiPass4.ar(dry,lfreq,q); mid = BLowPass4.ar(mid,hfreq,q);
			high = BHiPass4.ar(dry,hfreq,q);

			low = CompanderD.ar(low*lpre,1,1+lexp,10,att,lrel,lpos);
			low = MidSide(low, lstereo);
			low = SineShaper.ar(low);

			mid = CompanderD.ar(mid*mpre,1,1,10,att,lrel,mpos);
			mid = MidSide(mid, mstereo);

			high = CompanderD.ar(high*hpre,1,1+hexp,10,att,hrel,hpos);
			high = MidSide(high, hstereo);
			high = SineShaper.ar(high,hpos-(hpos*hsat));

			Limiter.ar(BHiPass4.ar(LeakDC.ar(Mix.new([low,mid,high])), locut), 0.99, 0.01) * makeup;
		}
  }

 *simple { |locut=40, gain=(0.dbamp)|
    ^{ BHiPass4.ar( \in.ar(0!2) * gain, locut) }
	}

}
