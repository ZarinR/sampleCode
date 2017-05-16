package com.mojonetworks.apiClient.dataObjects.mwm.session;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.mojonetworks.apiClient.accessor.common.WebServiceConstant;
import com.mojonetworks.apiClient.dataObjects.mwm.auth.MWMAuthenticationInfo;
import com.mojonetworks.apiClient.dataObjects.mwm.pojos.MWMUser;

public class MWMApiSession {
	
	private long timeout = WebServiceConstant.MWM_DEFAULT_TIMEOUT;
	private SessionState state=SessionState.SESSION_NOT_ESTABLISHED;

	private MWMAuthenticationInfo authInfo=null;
	private MWMUser user=null;
	private String clientIdPrefix=null;
	private String serverHostName=null;
	private ResteasyWebTarget target=null;
	private String jsessionid=null;

	public enum SessionState {
		SESSION_NOT_ESTABLISHED,
		SESSION_ESTABLISHED,
		SESSION_EXPIRED;
	}

	public MWMApiSession(SessionState state, MWMAuthenticationInfo authInfo, MWMUser user, String clientIdPrefix,
			String ipAddress, ResteasyWebTarget target, long timeout, String jsessionid) {
		super();
		this.state = state;
		this.authInfo = authInfo;
		this.user = user;
		this.clientIdPrefix = clientIdPrefix;
		this.serverHostName = ipAddress;
		this.target = target;
		this.timeout = timeout;
		this.jsessionid = jsessionid;
	}
	
	public void destroy(){
		this.state=SessionState.SESSION_NOT_ESTABLISHED;
		this.authInfo=null;
		this.user=null;
		this.clientIdPrefix=null;
		this.serverHostName=null;
		this.target=null;
		this.jsessionid=null;
		this.timeout=WebServiceConstant.MWM_DEFAULT_TIMEOUT;
	}
	
	public MWMUser getUser() {
		return user;
	}

	public MWMAuthenticationInfo getAuthInfo() {
		return authInfo;
	}

	public long getTimeout() {
		return timeout;
	}

	public String getClientIdentifier() {
		return clientIdPrefix;
	}

	public SessionState getSessionState(){
		return state;
	}

	public String getServerHostName() {
		return serverHostName;
	}

	public ResteasyWebTarget getTarget(){
		return target;
	}

	public boolean isConnected() {
		if(state == SessionState.SESSION_ESTABLISHED){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jsessionid == null) ? 0 : jsessionid.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MWMApiSession other = (MWMApiSession) obj;
		if (jsessionid == null) {
			if (other.jsessionid != null)
				return false;
		} else if (!jsessionid.equals(other.jsessionid))
			return false;
		if (state != other.state)
			return false;
		return true;
	}

}
