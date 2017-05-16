package com.mojonetworks.apiClient.dataObjects.mlp.pojos;

public class MLPResponse<T extends MLPData> {
/*
 * {
  "success": true,
  "message": "Logged in successfully",
  "data": {
//    "errorCode": 200,
//    "message": "Logged in successfully"
  }
}
 */
	
	public MLPResponse() {
		super();
	}
	
	private boolean success;
	private String message;
	private T data;
	
	public boolean isSuccess() {
		return success;
	}
	public String getMessage() {
		return message;
	}
	@Override
	public String toString() {
		return "MLPResponse [success=" + success + ", message=" + message + ", data=" + data + "]";
	}
	public T getData() {
		return data;
	}
	
}
