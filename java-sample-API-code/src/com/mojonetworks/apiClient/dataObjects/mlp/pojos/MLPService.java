package com.mojonetworks.apiClient.dataObjects.mlp.pojos;

import org.codehaus.jackson.annotate.JsonProperty;

public class MLPService {
	private int id;
	@JsonProperty("customer_service_name")
	private String customerServiceName;
	@JsonProperty("show_tile")
	private boolean showTitle;
	private Service service;
	
	public static class Service{
		@JsonProperty("service_url")
		private String serviceURL;
		
		@JsonProperty("service_id")
		private int serviceId;
		
		@JsonProperty("service_type_id")
		private int serviceTypeId;
		public String getServiceURL() {
			return serviceURL;
		}
		public void setServiceURL(String serviceURL) {
			this.serviceURL = serviceURL;
		}
		public int getServiceId() {
			return serviceId;
		}
		public void setServiceId(int serviceId) {
			this.serviceId = serviceId;
		}
		public int getServiceTypeId() {
			return serviceTypeId;
		}
		public void setServiceTypeId(int serviceTypeId) {
			this.serviceTypeId = serviceTypeId;
		}
		@Override
		public String toString() {
			return "Service [serviceURL=" + serviceURL + ", serviceId=" + serviceId + ", serviceTypeId=" + serviceTypeId
					+ "]";
		}
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerServiceName() {
		return customerServiceName;
	}

	public void setCustomerServiceName(String customerServiceName) {
		this.customerServiceName = customerServiceName;
	}

	public boolean isShowTitle() {
		return showTitle;
	}

	public void setShowTitle(boolean showTitle) {
		this.showTitle = showTitle;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "MLPService [id=" + id + ", customerServiceName=" + customerServiceName + ", showTitle=" + showTitle
				+ ", service=" + service + "]";
	}
	
}
