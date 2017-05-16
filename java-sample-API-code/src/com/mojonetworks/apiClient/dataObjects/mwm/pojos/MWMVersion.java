/*
 * Name: Version.java
 * Created by zarinr on Dec 19, 2009
 * Description: TODO zarinr
 * $Id: Version.java,v 1.4 2013-04-15 15:53:29 milindt Exp $
 *
 * Copyright (C) 2003-2009 AirTight Networks, Inc. All rights reserved.
 *
 * The information and contents of this file are the
 * proprietary information of AirTight Networks and may not
 * be disclosed or used without the formal written approval of
 * AirTight Networks.
 *
 */ 

package com.mojonetworks.apiClient.dataObjects.mwm.pojos;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MWMVersion implements Serializable{

	private static final long serialVersionUID = 1L;

	private String version;
	private String build;
	
	public MWMVersion() {
	}
	
	/**
	 * Returns the version identifier.
	 * @return Version identifier
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * Returns the build number.
	 * @return Build number.
	 */
	public String getBuild() {
		return build;
	}
	/**
	 * @exclude
	 */
	@Override
	public String toString() {
		return "Version:"+version +" Build:"+build;
	}

	/**
	 * @exclude
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(obj instanceof MWMVersion){
			MWMVersion otherVersion = (MWMVersion) obj;
			return (otherVersion.version.equalsIgnoreCase(this.version) && otherVersion.build.equalsIgnoreCase(this.build));
		}
		return false;
	}
}
