TestClass {

	at { | index |
    "getting from index %\n".postf(index);
    nil
	}

  put { | index, obj |
    "putting % ad index %\n".postf(obj, index);
    nil
  }

}
