package com.coderszone.common.exception;

public class MailServiceException extends Exception {
	private static final long serialVersionUID = -1714422363280912103L;

	public MailServiceException() {
		super();
	}

	public MailServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MailServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MailServiceException(String message) {
		super(message);
	}

	public MailServiceException(Throwable cause) {
		super(cause);
	}

	
}
