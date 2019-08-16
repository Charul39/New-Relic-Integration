package com.newrelic.apmplatform.dto;

public class TimezoneDTO {
	private String timezone;

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public TimezoneDTO() {
		super();
	}

	public TimezoneDTO(String timezone) {
		super();
		this.timezone = timezone;
	}
	
}
