Continuation {

  // Iterate over a collection of things, passing each
  // in turn to a function, but not moving onto the next one
  // until the function has called the continuation passed in
  // Simplifies callback chaining
  *new { |collection, f, finalAction=({|c| c.value}), idx=0|
    ^Continuation.fromIterator(collection.iter, f, finalAction, idx);
  }

  *fromIterator { |iterator, f, finalAction=({|c| c.value}), idx=0|
    ^{
      var v = iterator.next;
      if (v.isNil,
        finalAction,
        {
          f.value(
            v,
            Continuation.fromIterator(iterator, f, finalAction, idx + 1),
            idx,
          )
        }
      )
    }
  }

  *chain { |continuationList|
    ^{ |finalAction|
      Continuation.fromIterator(continuationList.iter, { |item, continuation|
        item.value(continuation);
      }, finalAction).value()
    }
  }

}
