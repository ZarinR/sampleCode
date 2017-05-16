package com.mojonetworks.apiClient.dataObjects.mlp.pojos;

import java.util.Arrays;

public class MLPServices extends MLPData {
	private int totalCount;
	private MLPService[] customerServices;

	public MLPServices() {
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public MLPService[] getCustomerServices() {
		return customerServices;
	}

	public void setCustomerServices(MLPService[] customerServices) {
		this.customerServices = customerServices;
	}

	@Override
	public String toString() {
		return "MLPServices [totalCount=" + totalCount + ", customerServices=" + Arrays.toString(customerServices)
				+ "]";
	}
	
	

}
