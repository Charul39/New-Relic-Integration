package com.newrelic.apmplatform.dto;

public class SaveProfileDTO {

	private String profile_name;
	private String key_title;
	private String api_key;
	private String app_name;
	private Integer app_id;
	private Long test_id;
	public SaveProfileDTO() {
		super();
	}
	public SaveProfileDTO(String profile_name, String key_title, String api_key, String app_name, Integer app_id,
			Long test_id) {
		super();
		this.profile_name = profile_name;
		this.key_title = key_title;
		this.api_key = api_key;
		this.app_name = app_name;
		this.app_id = app_id;
		this.test_id = test_id;
	}
	public String getProfile_name() {
		return profile_name;
	}
	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
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
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public Integer getApp_id() {
		return app_id;
	}
	public void setApp_id(Integer app_id) {
		this.app_id = app_id;
	}
	public Long getTest_id() {
		return test_id;
	}
	public void setTest_id(Long test_id) {
		this.test_id = test_id;
	}
	
}
