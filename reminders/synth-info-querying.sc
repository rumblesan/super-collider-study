
(
  SynthDescLib.global.synthDescs.keys.select({|synthName|
    synthName.asString.beginsWith("system_").not
  })
)

(
  SynthDescLib.global.synthDescs[\karpluspluck].controls.collect({
    |cn|
    "% -> %  (%)".format(cn.rate, cn.name, cn.defaultValue)
  }).do({
    |control|
    control.postln
  })
)

SynthDescLib.global.synthDescs[\karpluspluck].outputs[0]
