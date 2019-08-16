package com.newrelic.apmplatform.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "nr_apm_profile")
public class Profile implements Serializable {

	@EmbeddedId
    private ProfileId profile_id;
    
    @NotBlank
	private String key_title;
	
    @NotBlank
	private String api_key;
	
    @NotBlank
	private String app_name;
	
    @NotNull
	private Integer app_id;
    
	public Profile() {
		super();
	}

	public Profile(ProfileId profile_id, @NotBlank String key_title, @NotBlank String api_key,
			@NotBlank String app_name, @NotNull Integer app_id) {
		super();
		this.profile_id = profile_id;
		this.key_title = key_title;
		this.api_key = api_key;
		this.app_name = app_name;
		this.app_id = app_id;
	}

	public ProfileId getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(ProfileId profile_id) {
		this.profile_id = profile_id;
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

	@Override
	public String toString() {
		return "Profile [profile_id=" + profile_id + ", key_title=" + key_title + ", api_key=" + api_key
				+ ", app_name=" + app_name + ", app_id=" + app_id + "]";
	}
	
}
