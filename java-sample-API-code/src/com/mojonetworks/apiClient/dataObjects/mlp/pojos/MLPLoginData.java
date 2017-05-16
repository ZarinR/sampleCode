package com.mojonetworks.apiClient.dataObjects.mlp.pojos;

public class MLPLoginData extends MLPData {

	private int errorCode;
	private String message;
	
	public MLPLoginData() {
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "MLPLoginData [errorCode=" + errorCode + ", message=" + message + "]";
	}
	
	
	
}
