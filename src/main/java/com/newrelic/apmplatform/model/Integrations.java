package com.newrelic.apmplatform.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "integrations")
public class Integrations implements Serializable{
	
	@Id
	private Long test_id;
	
	private Long user_id;
	
	private String apm_profile;

	public Integrations() {
		super();
	}

	public Integrations(Long test_id, Long user_id, String apm_profile) {
		super();
		this.test_id = test_id;
		this.user_id = user_id;
		this.apm_profile = apm_profile;
	}

	public Long getTest_id() {
		return test_id;
	}

	public void setTest_id(Long test_id) {
		this.test_id = test_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getApm_profile() {
		return apm_profile;
	}

	public void setInfra_profile(String apm_profile) {
		this.apm_profile = apm_profile;
	}

	@Override
	public String toString() {
		return "Integrations [test_id=" + test_id + ", user_id=" + user_id + ", apm_profile=" + apm_profile + "]";
	}
	
}
