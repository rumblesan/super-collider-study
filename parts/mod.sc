(
  ~mod = Pcontrol(
    \mod,
    \value, Pseq([1], inf),
    \slew, 0,
    \dur, 1
  )
)
~mod.quant = 4;

~mod.scope
