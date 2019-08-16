package com.newrelic.apmplatform.dto;

public class KeyProfileDTO {

	private String key_title;
	
	private String api_key;

	public KeyProfileDTO() {
		super();
	}

	public KeyProfileDTO(String key_title, String api_key) {
		super();
		this.key_title = key_title;
		this.api_key = api_key;
	}

	public String getKey_title() {
		return key_title;
	}

	public void setKey_title(String key_title) {
		this.key_title = key_title;
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	@Override
	public String toString() {
		return "KeyProfileDTO [key_title=" + key_title + ", api_key=" + api_key + "]";
	}
	
}
