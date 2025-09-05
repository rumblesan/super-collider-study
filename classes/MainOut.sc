MainOut {
  *new { |locut=40, gain=(0.dbamp)|
    ^{ BHiPass4.ar( \in.ar(0!2) * gain, locut) }
  }
}
