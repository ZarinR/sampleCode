

package com.mojonetworks.apiClient.dataObjects.mwm.auth;

public class MWMUserCredentialsAuth extends MWMAuthenticationInfo {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private final String type="usernamepasswordcredentials";
	
	public MWMUserCredentialsAuth(String userName, String password) {
		setUsername(userName);
		setPassword(password);
	}
	public String getUsername() {
		return username;
	}
	private void setUsername(String userName) {
		if(userName!=null){
			this.username = userName;
		}
	}
	public String getPassword() {
		return password;
	}
	private void setPassword(String password) {
		if(password!=null && !password.isEmpty()) {
			this.password = password;
		}
	}
	public String getType() {
		return type;
	}
	
}
