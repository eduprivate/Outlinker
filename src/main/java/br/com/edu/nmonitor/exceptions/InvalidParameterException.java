package br.com.edu.nmonitor.exceptions;

@SuppressWarnings("serial")
public class InvalidParameterException extends Throwable {
	public InvalidParameterException() {
		super();
	}

	public InvalidParameterException(Throwable cause) {
		super(cause);
	}

	public InvalidParameterException(String message) {
		super(message);
	}

	public InvalidParameterException(String message, Throwable cause) {
		super(message, cause);
	}
}

