ChannelStrip {
  *new {|audio, level=(-0.dbamp), pan=0, locut=40|
    ^BHiPass4.ar(if (audio.numChannels == 1,
      {Pan2.ar(audio, pan, level)},
      {Balance2.ar(audio[0], audio[1], pan, level)}
    ), locut)
  }
}
