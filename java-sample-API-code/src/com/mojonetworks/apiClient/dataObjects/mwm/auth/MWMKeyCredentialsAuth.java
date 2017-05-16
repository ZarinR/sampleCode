

package com.mojonetworks.apiClient.dataObjects.mwm.auth;

public class MWMKeyCredentialsAuth extends MWMAuthenticationInfo {

	private static final long serialVersionUID = 1L;
	
	private String keyId;
	private String keyValue;
	private final String type="apikeycredentials";
	
	public MWMKeyCredentialsAuth(String userName, String password) {
		setKeyId(userName);
		setKeyValue(password);
	}
	
	public String getKeyId() {
		return keyId;
	}
	
	private void setKeyId(String userName) {
		if(userName!=null){
			this.keyId = userName;
		}
	}
	
	public String getKeyValue() {
		return keyValue;
	}
	
	private void setKeyValue(String password) {
		if(password!=null && !password.isEmpty()) {
			this.keyValue = password;
		}
	}

	public String getType() {
		return type;
	}
	
}
