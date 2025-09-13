Npat {
  *new { |name ... pairs|
    var args = Dictionary.newFrom(pairs);
    ^if (args.includes(\loop), {
      Npat.withLoop(name, args.at(\loop), pairs);
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
  }

  *noLoop { |name, args|
    ^Ndef(name)[0] = Pbindf(Npat.defaultValues, *args)
  }
}
