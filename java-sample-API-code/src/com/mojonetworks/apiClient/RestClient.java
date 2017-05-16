package com.mojonetworks.apiClient;

import com.mojonetworks.apiClient.accessor.RestServerAccessor;

public class RestClient {

	public static void main(String[] args) {
		
//		RestServerAccessor.mwmKeyLogin("192.168.8.7", "KEY-ATN48-21","385b28d6751d8491d9954e9ef0eae78a");
//		RestServerAccessor.userNamePasswordLogin("192.168.8.7", "admin", "admin");
//		https://mojo.airtightnw.com/rest/api/v2/kvs/login?key_id=KEY-ATN47-19&key_value=49dc318d65448c649484f1d3f5aca3f5
		RestServerAccessor.mlpKeyLogin("mojo.airtightnw.com", "KEY-ATN47-19", "49dc318d65448c649484f1d3f5aca3f5");
		
	}

}
