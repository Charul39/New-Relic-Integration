package com.newrelic.apmplatform.dto;

public class AppNameDTO {
	
	private String app_name;
	private Integer app_id;

	public AppNameDTO() {
		super();
	}

	public AppNameDTO(String app_name, Integer app_id) {
		super();
		this.app_name = app_name;
		this.app_id = app_id;
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

}
