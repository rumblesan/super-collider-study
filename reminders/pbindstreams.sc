"Setup.sc".load;

p.clock.tempo = 160/60;

a = Pseq([1, 2, 3], inf).asStream

a = Pn(
    Pfindur(3, Pbind(
      \degree, Prand([0, 1], inf),
      \instrument, Prand([\bkick, \modkick], inf),

      \bar, Pbinop('==', Pkey(\instrument), \bkick),
      //\foo, Pif((Pkey(\instrument) == 'bkick'), "its bkick", "it's modkick"),
      \dur, 0.5,
    )
    ),
    inf
)
x = a.asStream


x.nextN(10, ())

x.next(())
