package com.newrelic.apmplatform.dto;

public class MetricDataDTO {
	
	private Integer metric_id;
	
	private String metric_name;
	
	private String to_time;
	
	private String from_time;
	
	private Double data;

	public MetricDataDTO() {
		super();
	}

	public MetricDataDTO(Integer metric_id, String metric_name, String to_time, String from_time, Double data) {
		super();
		this.metric_id = metric_id;
		this.metric_name = metric_name;
		this.to_time = to_time;
		this.from_time = from_time;
		this.data = data;
	}

	public Integer getMetric_id() {
		return metric_id;
	}

	public String getMetric_name() {
		return metric_name;
	}

	public String getTo_time() {
		return to_time;
	}

	public String getFrom_time() {
		return from_time;
	}

	public Double getData() {
		return data;
	}
	
}
