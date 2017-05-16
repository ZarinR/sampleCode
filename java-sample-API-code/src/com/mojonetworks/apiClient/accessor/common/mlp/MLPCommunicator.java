package com.mojonetworks.api.client.accessor.common.mlp;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import com.mojonetworks.apiClient.accessor.common.WebHTTPRequestInvoker;
import com.mojonetworks.apiClient.accessor.common.WebServiceConstant;
import com.mojonetworks.apiClient.dataObjects.mlp.pojos.MLPResponse;
import com.mojonetworks.apiClient.dataObjects.mlp.pojos.MLPServices;
import com.mojonetworks.apiClient.dataObjects.mlp.session.MLPApiSession;

public class MLPCommunicator {

	public static MLPServices getAllServices(MLPApiSession apiSession) {
		String uri = apiSession.getTarget().getUri().toString() + WebServiceConstant.MLP_SERVICES;
		Response response = WebHTTPRequestInvoker.get(apiSession.getTarget(), uri, null);
		MLPResponse<MLPServices> services= response.readEntity(new GenericType<MLPResponse<MLPServices>>(){});
		response.close();
		return services.getData();
	}
	public static MLPServices getAllMWMServices(MLPApiSession apiSession) {
		String uri = apiSession.getTarget().getUri().toString() + WebServiceConstant.MLP_SERVICES;
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("type","amc");
		Response response = WebHTTPRequestInvoker.get(apiSession.getTarget(), uri, queryParams);
		MLPResponse<MLPServices> services= response.readEntity(new GenericType<MLPResponse<MLPServices>>(){});
		response.close();
		return services.getData();
	}
}
