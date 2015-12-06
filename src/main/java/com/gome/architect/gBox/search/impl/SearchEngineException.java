package com.gome.architect.gBox.search.impl;

public class SearchEngineException extends RuntimeException {

	private static final long serialVersionUID = 1346438419125431016L;

	/** default constructor */
	  public SearchEngineException() {
	    super();
	  }

	  /**
	   * Constructor
	   * @param s message
	   */
	  public SearchEngineException(String s) {
	    super(s);
	  }

	  /**
	   * Constructor taking another exception.
	   * @param e Exception to grab data from.
	   */
	  public SearchEngineException(Exception e) {
	    super(e);
	  }

	  public SearchEngineException(String s, Exception e) {
	    super(s, e);
	  }



}
