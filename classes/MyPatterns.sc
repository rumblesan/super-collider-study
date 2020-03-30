Pixi : Pseq {
  var <>score="", <>duration=0.5;

  *new { arg score="", repeats=1, offset=0, duration=0.5;
    var list = Pixi.parseScore(score, duration);
    list.postln;
    ^super.newCopyArgs(list, repeats, offset, score, duration);
  }

  *parseScore { arg score, duration;
    var initialGap=0, count=0, num="", values=[];

    score.do({arg c;
      case
      {c == Char.space} { if (num == "", {initialGap = initialGap + 1}, {count = count + 1}) }
      {c.isDecDigit}
        {if (num == "",
          {num = c; if (initialGap > 0, {values = values.add([nil, Rest(initialGap)])});},
          {values = values.add([num.digit, (count + 1) * duration]); num = c; count = 0}
        )};
    });
    if (num != "", {
      values = values.add([num.digit, (count + 1) * duration]);
    });
    ^values;
  }

  storeArgs { ^ [ score, repeats, offset, duration ] }
}
