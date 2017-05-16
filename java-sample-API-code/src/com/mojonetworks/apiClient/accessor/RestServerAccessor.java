package com.mojonetworks.apiClient.accessor;

import com.mojonetworks.apiClient.accessor.common.APIClientException;
import com.mojonetworks.apiClient.accessor.common.APISessionProvider;
import com.mojonetworks.apiClient.accessor.common.mlp.MLPCommunicator;
import com.mojonetworks.apiClient.accessor.mwm.MWMCommunicator;
import com.mojonetworks.apiClient.dataObjects.mlp.auth.MLPKeyCredentialsAuth;
import com.mojonetworks.apiClient.dataObjects.mlp.pojos.MLPServices;
import com.mojonetworks.apiClient.dataObjects.mlp.session.MLPApiSession;
import com.mojonetworks.apiClient.dataObjects.mwm.auth.MWMAuthenticationInfo;
import com.mojonetworks.apiClient.dataObjects.mwm.auth.MWMKeyCredentialsAuth;
import com.mojonetworks.apiClient.dataObjects.mwm.auth.MWMUserCredentialsAuth;
import com.mojonetworks.apiClient.dataObjects.mwm.pojos.MWMVersion;
import com.mojonetworks.apiClient.dataObjects.mwm.session.MWMApiSession;

public class RestServerAccessor {

	public static void mwmKeyLogin(String mwmHost, String keyId, String keyValue){
		MWMKeyCredentialsAuth auth= new MWMKeyCredentialsAuth(keyId, keyValue);
		authenticateToMWM(mwmHost, auth);
	}
	
	public static void mwmUserNamePasswordLogin(String mwmHost, String loginId, String passwd){
		MWMUserCredentialsAuth auth= new MWMUserCredentialsAuth(loginId, passwd);
		authenticateToMWM(mwmHost, auth);
	}
	
	
	public static void mlpKeyLogin(String mlpHost, String keyId, String KeyValue){
		MLPKeyCredentialsAuth auth= new MLPKeyCredentialsAuth(keyId, KeyValue);
		try {
			MLPApiSession mlpApiSession = APISessionProvider.getMLPApiSession(mlpHost, auth);
			System.out.println("Logged into MLP, response:" +mlpApiSession.getMlpLoginResponse());
			MLPServices allServices = MLPCommunicator.getAllMWMServices(mlpApiSession);
			System.out.println("Service:"+allServices);
		} catch (APIClientException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void authenticateToMWM(String mwmHost, MWMAuthenticationInfo auth) {
		try {
			MWMApiSession apiSession = APISessionProvider.getMWMAPISession(mwmHost, auth, "TestClientId");
			MWMVersion version= MWMCommunicator.getServerVersion(apiSession);
			System.out.println("Version:"+version);
			System.out.println("Logged in user: "+apiSession.getUser());
			MWMCommunicator.disconnect(apiSession);
		} catch (APIClientException e) {
			e.printStackTrace();
		}
	}
	

}
