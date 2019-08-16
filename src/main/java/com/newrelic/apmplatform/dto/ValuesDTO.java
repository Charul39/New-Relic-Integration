package com.newrelic.apmplatform.dto;

public class ValuesDTO {

	private String metric_name;
	private Double avg_response_time;
	
	public ValuesDTO() {
		super();
	}

	public ValuesDTO(String metric_name, Double avg_response_time) {
		super();
		this.metric_name = metric_name;
		this.avg_response_time = avg_response_time;
	}

	public String getMetric_name() {
		return metric_name;
	}

	public void setMetric_name(String metric_name) {
		this.metric_name = metric_name;
	}

	public Double getAvg_response_time() {
		return avg_response_time;
	}

	public void setAvg_response_time(Double avg_response_time) {
		this.avg_response_time = avg_response_time;
	}
	
}
