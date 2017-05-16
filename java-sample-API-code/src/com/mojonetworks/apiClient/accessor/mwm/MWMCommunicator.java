package com.mojonetworks.apiClient.accessor.mwm;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mojonetworks.apiClient.accessor.common.APIClientException;
import com.mojonetworks.apiClient.accessor.common.WebHTTPRequestInvoker;
import com.mojonetworks.apiClient.accessor.common.WebServiceConstant;
import com.mojonetworks.apiClient.dataObjects.mwm.pojos.MWMVersion;
import com.mojonetworks.apiClient.dataObjects.mwm.session.MWMApiSession;
import com.mojonetworks.apiClient.dataObjects.mwm.session.MWMApiSession.SessionState;

public class MWMCommunicator {
	/**
	 * Closes a previously opened session. If the application does not use this method explicitly, 
	 * MWM uses session time out to terminate the session and release resources. 
	 * @throws APIClientException
	 */
	public static void disconnect(MWMApiSession apiSession) throws APIClientException {
		if(apiSession.getSessionState()!=SessionState.SESSION_ESTABLISHED){
			throw new APIClientException("API Session not established");
		}
		try{
			String uri = apiSession.getTarget().getUri().toString() + WebServiceConstant.MWM_LOGOUT;
			Response response = WebHTTPRequestInvoker.post(apiSession.getTarget(), uri, null, null);
			if(response.getStatus()>=Status.OK.getStatusCode()){
				apiSession.destroy();
			} else {
				throw new APIClientException("Unable to disconnect session! Error Code:"+response.getStatus());
			}
			response.close();
		}catch(Exception e){
			throw new APIClientException("Unable to disconnect session!!", e);
		}
	}
	
	/**
	 * Returns the version of the MWM API Server
	 */
	public static MWMVersion getServerVersion(MWMApiSession apiSession) {
		String uri = apiSession.getTarget().getUri().toString() + WebServiceConstant.MWM_PRODUCT_INFO + WebServiceConstant.MWM_GET_VERSION;
		Response response = WebHTTPRequestInvoker.get(apiSession.getTarget(), uri, null);
		MWMVersion version = response.readEntity(MWMVersion.class);
		response.close();
		return version;
	}

	/**
	 * Keeps the session alive for the API client. 
	 * This could be called on a periodic basis by the API Client to keep the session alive in case 
	 * there are periods of time where the session is idle. 
	 * In case the API Client calls this method on a periodic basis, this period should be less than 
	 * the session timeout value provided by the API Client.
	 * @throws APIClientException
	 */
	public static void keepSessionAlive(MWMApiSession apiSession) throws APIClientException {
		String uri = apiSession.getTarget().getUri().toString() + WebServiceConstant.MWM_LOGIN + WebServiceConstant.MWM_KEEP_ALIVE;
		Response response = WebHTTPRequestInvoker.get(apiSession.getTarget(), uri, null);
		response.close();
	}
}
