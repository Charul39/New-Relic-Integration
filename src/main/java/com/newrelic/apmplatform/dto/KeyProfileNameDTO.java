package com.newrelic.apmplatform.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class KeyProfileNameDTO implements Serializable{
	
	private String key_title;
	public KeyProfileNameDTO() {
		super();
	}
	public KeyProfileNameDTO(String key_title) {
		super();
		this.key_title = key_title;
	}
	public String getKey_title() {
		return key_title;
	}
	public void setKey_title(String key_title) {
		this.key_title = key_title;
	}
	@Override
	public String toString() {
		return "KeyProfileNameDTO [key_title=" + key_title + "]";
	}
	
}
