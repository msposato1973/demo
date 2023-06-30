package com.fsb.demo.error;

public class GameOwnException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameOwnException() {
		super();
	}

	public GameOwnException(String message) {
		super(message);
		
	}

	public GameOwnException(Throwable cause) {
		super(cause);
		 
	}

	public GameOwnException(String message, Throwable cause) {
		super(message, cause);
		 
	}


}
