package com.gome.architect.gBox.tools;

public class StringKitException extends RuntimeException{

	private static final long serialVersionUID = -2235130535151797769L;

	/** default constructor */
	  public StringKitException() {
	    super();
	  }

	  /**
	   * Constructor
	   * @param s message
	   */
	  public StringKitException(String s) {
	    super(s);
	  }

	  /**
	   * Constructor taking another exception.
	   * @param e Exception to grab data from.
	   */
	  public StringKitException(Exception e) {
	    super(e);
	  }

	  public StringKitException(String s, Exception e) {
	    super(s, e);
	  }


}
