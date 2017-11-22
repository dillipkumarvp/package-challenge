package com.mobiquityinc.packer.exception;

/**
 * Custom exception to throw API exception for incorrect parameters.
 * 
 * @author dillipkumar.vp
 *
 */

public class APIException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public APIException() {
		super();
	}

	/**
	 * Method to add error message to the error appender
	 * 
	 * @param message
	 */
	public APIException(String message) {
		super(message);
	}

	/**
	 * Method to Add throwable/exception object to the error appender
	 * 
	 * @param cause
	 */

	public APIException(Throwable cause) {
		super(cause);
	}

	/**
	 * Method to add Custom message and throwable object to error appender.
	 * 
	 * @param message
	 * @param throwable
	 */
	public APIException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
