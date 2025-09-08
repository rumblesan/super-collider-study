Npat {
  *new { |name ... pairs|
    var defaults = Pbind(
      \scale, Scale.minor,
      \root, -5,
      \amp, 1,
    );
    ^Ndef(name)[0] = Pbindf(defaults, *pairs)
  }
}

NpatLoop {
  *new { |name, length ... pairs|
    var defaults = Pbind(
      \scale, Scale.minor,
      \root, -5,
      \amp, 1,
    );
    ^Ndef(name)[0] = Pn(Pfindur(length,
                       Pbindf(defaults, *pairs)
                     ), inf)
  }
}
