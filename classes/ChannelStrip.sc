ChannelStrip {
  *ar {|audio, level, pan|
    ^if (audio.numChannels == 1,
      {Pan2.ar(audio, pan, level)},
      {Balance2.ar(audio[0], audio[1], pan, level)}
    )
  }
}
