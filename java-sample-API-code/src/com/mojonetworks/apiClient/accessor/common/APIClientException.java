package com.mojonetworks.apiClient.accessor.common;

public class APIClientException extends Exception {

	public APIClientException(String errorMsg) {
		super(errorMsg);
	}

	public APIClientException() {
		super();
	}

	public APIClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public APIClientException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;

}
