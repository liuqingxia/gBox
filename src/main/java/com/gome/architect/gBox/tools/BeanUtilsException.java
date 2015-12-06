package com.gome.architect.gBox.tools;

public class BeanUtilsException extends RuntimeException {

	private static final long serialVersionUID = -8449471106336706338L;

	/** default constructor */
	  public BeanUtilsException() {
	    super();
	  }

	  /**
	   * Constructor
	   * @param s message
	   */
	  public BeanUtilsException(String s) {
	    super(s);
	  }

	  /**
	   * Constructor taking another exception.
	   * @param e Exception to grab data from.
	   */
	  public BeanUtilsException(Exception e) {
	    super(e);
	  }

	  public BeanUtilsException(String s, Exception e) {
	    super(s, e);
	  }
}
