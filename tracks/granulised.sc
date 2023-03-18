
"Setup.scd".load;

(
~granmix = {
  Mix.new([
    ~chords.ar(1)
  ])
}
)

~gran[0] = \granulizer
~granmix <>> ~gran

~gran.play
~gran.stop
~gran.clear
~granmix.clear

(
  ~gran[1] = \pset -> Pbind(
    \density, 5,
    \randDensity, 10,
    \size, 0.2,
    \position, 0.0,
    \rate, 1,
    \spread, 0.1,
    \spreadRate, 1.1,
    \feedback, 0,
    \drywet, 1.0,
    \amp, 1,
    \dur, 0.5,
  )
)
