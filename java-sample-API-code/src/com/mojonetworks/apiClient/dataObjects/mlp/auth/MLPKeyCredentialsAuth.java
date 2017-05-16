package com.mojonetworks.apiClient.dataObjects.mlp.auth;

public class MLPKeyCredentialsAuth {
	private String key_id=null;
	private String key_value=null;
	
	public MLPKeyCredentialsAuth(String key_id, String key_value) {
		super();
		this.key_id = key_id;
		this.key_value = key_value;
	}
	public String getKey_id() {
		return key_id;
	}
	public void setKey_id(String key_id) {
		this.key_id = key_id;
	}
	public String getKey_value() {
		return key_value;
	}
	public void setKey_value(String key_value) {
		this.key_value = key_value;
	}
	

}
