package com.coderszone.common.exception;

public class DataBaseAccessException extends Exception{


	private static final long serialVersionUID = 2399550216798972190L;

	public DataBaseAccessException() {
		super();
	}

	public DataBaseAccessException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataBaseAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataBaseAccessException(String message) {
		super(message);
	}

	public DataBaseAccessException(Throwable cause) {
		super(cause);
	}

}
