/* * Name: LocationId.java
 *
 * Created by lalitm on 29-Feb-2012
 *
 * Description: 
 *
 * Copyright (C) 2003-2012 AirTight Networks, Inc. All rights reserved.
 *
 * The information and contents of this file are the
 * proprietary information of AirTight Networks and may not
 * be disclosed or used without the formal written approval of
 * AirTight Networks.
 *
 */
package com.mojonetworks.apiClient.dataObjects.mwm.pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MWMLocationId {
	private String type;
	private int id;
	
	public static final int ROOT_LOCATION_ID = 0;
	
	public MWMLocationId() {
	}
	
	public MWMLocationId(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		MWMLocationId other = (MWMLocationId) obj;
		if (id != other.id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LocationId [type=" + type + ", id=" + id + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
