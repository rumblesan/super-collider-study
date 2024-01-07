Continuation {

  // Iterate over a collection of things, passing each
  // in turn to a function, but not moving onto the next one
  // until the function has called the continuation passed in
  // Simplifies callback chaining
  *iterator { |collection, f, idx=0|
    ^Continuation.iterate(collection.iter, f, idx)
  }

  *iterate { |iterator, f, idx=0|
    ^{|finalAction|
      var v = iterator.next;
      if (v.isNil, finalAction,
        {
          f.value(
            v,
            { Continuation.iterate(iterator, f, idx + 1).value(finalAction) },
            idx,
          )
        }
      )
    }
  }

  *chain { |continuationList|
    ^Continuation.iterate(continuationList.iter, { |func, continuation, idx|
      func.value(continuation, idx)
    });
  }

  *wrap { |f|
    ^{ |continuation|
      f.value;
      continuation.value;
    }
  }

}
