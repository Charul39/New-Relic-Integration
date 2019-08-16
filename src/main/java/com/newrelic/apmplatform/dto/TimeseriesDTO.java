package com.newrelic.apmplatform.dto;

import java.util.List;

public class TimeseriesDTO {

	private String from_time;
	private String to_time;
	private List<ValuesDTO> values;
	
	public TimeseriesDTO() {
		super();
	}

	public TimeseriesDTO(String from_time, String to_time, List<ValuesDTO> values) {
		super();
		this.from_time = from_time;
		this.to_time = to_time;
		this.values = values;
	}

	public String getFrom_time() {
		return from_time;
	}

	public String getTo_time() {
		return to_time;
	}

	public List<ValuesDTO> getValues() {
		return values;
	}

	public void setFrom_time(String from_time) {
		this.from_time = from_time;
	}

	public void setTo_time(String to_time) {
		this.to_time = to_time;
	}

	public void setValues(List<ValuesDTO> values) {
		this.values = values;
	}
	
}
