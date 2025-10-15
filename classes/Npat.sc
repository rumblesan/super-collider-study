Npat {
  *new { |name ... pairs|
    var args = Dictionary.newFrom(pairs);
    var loopLength = args.removeAt(\loop);
    ^if (loopLength.isInteger, {
      Npat.withLoop(name, loopLength, pairs);
    }, {
      Npat.noLoop(name, pairs);
    })
  }

  *defaultValues {
    ^Pbind(
      \scale, Scale.minor,
      \root, -5,
      \amp, 1,
    )
  }

  *withLoop{ |name, loopLength, args|
    ^Ndef(name)[0] = Pn(
                       Pfindur(
                         loopLength,
                         Pbindf(Npat.defaultValues, *args)
                       ),
                     inf)
  }

  *noLoop { |name, args|
    ^Ndef(name)[0] = Pbindf(Npat.defaultValues, *args)
  }
}
