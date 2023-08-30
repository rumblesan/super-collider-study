Ducker {
  *ar {|audio, attack, decay|
    var env = EnvDetect.ar(audio, attack, decay);
    var ducker = (1 - env).max(0);
    ^ducker!2
  }
}
