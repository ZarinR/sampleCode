package com.mojonetworks.apiClient.dataObjects.mlp.session;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.mojonetworks.apiClient.dataObjects.mlp.pojos.MLPLoginData;
import com.mojonetworks.apiClient.dataObjects.mlp.pojos.MLPResponse;

public class MLPApiSession {
	private String serverHostName=null;
	private ResteasyWebTarget target=null;
	private String jsessionid=null;
	private MLPResponse<MLPLoginData> mlpLoginResponse;
	
	public MLPApiSession(String serverHostName, ResteasyWebTarget target, String jsessionid, MLPResponse<MLPLoginData> mlpLoginResponse) {
		super();
		this.serverHostName = serverHostName;
		this.target = target;
		this.jsessionid = jsessionid;
		this.mlpLoginResponse = mlpLoginResponse;
	}
	public String getServerHostName() {
		return serverHostName;
	}
	public ResteasyWebTarget getTarget() {
		return target;
	}
	public MLPResponse<MLPLoginData> getMlpLoginResponse() {
		return mlpLoginResponse;
	}


}
