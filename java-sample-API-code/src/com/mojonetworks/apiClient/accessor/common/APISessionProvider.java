package com.mojonetworks.apiClient.accessor.common;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.mojonetworks.apiClient.dataObjects.mlp.auth.MLPKeyCredentialsAuth;
import com.mojonetworks.apiClient.dataObjects.mlp.pojos.MLPLoginData;
import com.mojonetworks.apiClient.dataObjects.mlp.pojos.MLPResponse;
import com.mojonetworks.apiClient.dataObjects.mlp.session.MLPApiSession;
import com.mojonetworks.apiClient.dataObjects.mwm.auth.MWMAuthenticationInfo;
import com.mojonetworks.apiClient.dataObjects.mwm.auth.MWMKeyCredentialsAuth;
import com.mojonetworks.apiClient.dataObjects.mwm.auth.MWMUserCredentialsAuth;
import com.mojonetworks.apiClient.dataObjects.mwm.pojos.MWMUser;
import com.mojonetworks.apiClient.dataObjects.mwm.session.MWMApiSession;
import com.mojonetworks.apiClient.dataObjects.mwm.session.MWMApiSession.SessionState;

public class APISessionProvider {
	public static MWMApiSession getMWMAPISession(String mwmHost, MWMAuthenticationInfo authInfo, String clientIdPrefix) throws APIClientException {
		return getMWMAPISession(mwmHost, authInfo, clientIdPrefix,WebServiceConstant.MWM_DEFAULT_TIMEOUT);
	}
	
	public static MWMApiSession getMWMAPISession(String mwmHost, MWMAuthenticationInfo authInfo, String clientIdPrefix, long timeout) throws APIClientException {
		return connectMWM(mwmHost, authInfo, clientIdPrefix, timeout);
	}
	
	public static MLPApiSession getMLPApiSession(String hostName, MLPKeyCredentialsAuth authInfo) throws APIClientException{
		return connectMLP(hostName, authInfo);
	}
	
	private static MLPApiSession connectMLP(String hostName, MLPKeyCredentialsAuth authInfo) throws APIClientException{
		ResteasyClient client = buildClient();
		ResteasyWebTarget target = client.target("https://"+hostName + WebServiceConstant.MLP_BASE_URL);
		return authenticateToMLP(hostName, target, authInfo);
	}
	
	
	private static MWMApiSession connectMWM(String hostName, MWMAuthenticationInfo authInfo, String cliendIdentifierPrefix, long timeout) throws APIClientException {
		ResteasyClient client = buildClient();
		ResteasyWebTarget target = client.target("https://"+hostName + WebServiceConstant.MWM_BASE_URL);
		return authenticateToMWM(hostName,target,authInfo,cliendIdentifierPrefix,timeout);
	}

	private static MLPApiSession authenticateToMLP(String hostName, ResteasyWebTarget target, MLPKeyCredentialsAuth authInfo) throws APIClientException{
		Response loginResponse= null;
		try{
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put(WebServiceConstant.MLP_KEY_ID, authInfo.getKey_id());
			queryParams.put(WebServiceConstant.MLP_KEY_VALUE, authInfo.getKey_value());
			
			String uri= target.getUri().toString() + WebServiceConstant.MLP_KEY_LOGIN;
			loginResponse = WebHTTPRequestInvoker.get(target, uri, queryParams, (String[])null);
		}catch (Exception e){
			throw new APIClientException("Session not established", e);	
		}
		if(loginResponse!=null && loginResponse.getStatus()==Status.OK.getStatusCode()){
			MultivaluedMap<String, Object> map = loginResponse.getHeaders();
			List<Object> oList =  map.get("Set-Cookie");
			String jsessionid = ((oList.get(0).toString().split(";"))[0]).split("=")[1];
			MLPResponse<MLPLoginData> mlpLoginResponse= loginResponse.readEntity(new GenericType<MLPResponse<MLPLoginData>>(){});
			loginResponse.close();
			//set the cookie to the target object
			target.request().cookie(new Cookie(WebServiceConstant.JSESSION_ID, jsessionid));
			return new MLPApiSession(hostName, target, jsessionid, mlpLoginResponse);
		} else if(loginResponse!=null && loginResponse.getStatus()==Status.UNAUTHORIZED.getStatusCode()){
			loginResponse.close();
			throw new APIClientException("Unauthorized!! -" +loginResponse.getStatusInfo());
		} else if (loginResponse!=null){
			loginResponse.close();
			throw new APIClientException("Failed to login -" +loginResponse.getStatusInfo());
		}else {
			throw new APIClientException("Failed to login!!");
		}
	}
	
	private static MWMApiSession authenticateToMWM(String hostName,ResteasyWebTarget target, MWMAuthenticationInfo authInfo, String clientIdPrefix, long timeout) throws APIClientException {
		Response loginResponse = null;
		try {
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put(WebServiceConstant.MWM_INSTANCE_ID_REQUEST_ATTR, WebAPIUtility.convertDOToJSON(0));
			String uri = target.getUri().toString() + WebServiceConstant.MWM_LOGIN;

			if(authInfo instanceof MWMUserCredentialsAuth){
				MWMUserCredentialsAuth auth = (MWMUserCredentialsAuth) authInfo;
				loginResponse = WebHTTPRequestInvoker.post(target, uri, Entity.json(auth), queryParams, clientIdPrefix, ""+timeout);
			}else if(authInfo instanceof MWMKeyCredentialsAuth){
				MWMKeyCredentialsAuth auth = (MWMKeyCredentialsAuth) authInfo;
				loginResponse = WebHTTPRequestInvoker.post(target, uri, Entity.json(auth), queryParams, clientIdPrefix, ""+timeout);
			}
		} catch (Exception e) {
			throw new APIClientException("Session not established", e);
		}

		if(loginResponse!=null && loginResponse.getStatus()==Status.OK.getStatusCode()){
			MultivaluedMap<String, Object> map = loginResponse.getHeaders();
			List<Object> oList =  map.get("Set-Cookie");
			String jsessionid = ((oList.get(0).toString().split(";"))[0]).split("=")[1];
			MWMUser user= loginResponse.readEntity(MWMUser.class);
			loginResponse.close();
			//set the cookie to the target object
			target.request().cookie(new Cookie(WebServiceConstant.JSESSION_ID, jsessionid));
			return new MWMApiSession(SessionState.SESSION_ESTABLISHED,authInfo, user, clientIdPrefix, hostName, target, timeout, jsessionid);
		} else if(loginResponse!=null && loginResponse.getStatus()==Status.UNAUTHORIZED.getStatusCode()){
			loginResponse.close();
			throw new APIClientException("Unauthorized!! -" +loginResponse.getStatusInfo());
		} else if (loginResponse!=null){
			loginResponse.close();
			throw new APIClientException("Failed to login -" +loginResponse.getStatusInfo());
		}else {
			throw new APIClientException("Failed to login!!");
		}
	}
	
	
	private static ResteasyClient buildClient() {
		System.setProperty(WebServiceConstant.HTTPS_PROTOCOLS, WebServiceConstant.PROTOCOL_TLSv1);
		X509TrustManager xtm = new CompleteTrustManager();
		TrustManager mytm[] = {xtm};
		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance(WebServiceConstant.PROTOCOL_SSL);
			ctx.init(null,mytm, null );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

		ResteasyClientBuilder resteasyClientBuilder = new ResteasyClientBuilder();
		resteasyClientBuilder.connectionPoolSize(10);
		resteasyClientBuilder.hostnameVerifier(new PseudoNameVerifier());
		resteasyClientBuilder.sslContext(ctx);
		ResteasyClient client = resteasyClientBuilder.build();
		//client.register(CustomJacksonProvider.class);
		return client;
	}

	
	/*
	 * TODO
	 * This is a allow any server certificate trust manager, We suggest you modify this appropriately to trust only the MWM certificates
	 */
	public  static class CompleteTrustManager implements X509TrustManager {
		public CompleteTrustManager() {
			// create/load keystore
		}
		public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
		}
		public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
		}
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}
	@SuppressWarnings("deprecation")
	private static class PseudoNameVerifier implements X509HostnameVerifier {
		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
		public void verify(String arg0, SSLSocket arg1) throws IOException {
		}
		public void verify(String arg0, X509Certificate arg1) throws SSLException {
		}
		public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {
		}
	}
}
