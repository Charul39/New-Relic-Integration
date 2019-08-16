package com.newrelic.apmplatform.dto;

import java.util.List;

public class DataDTO {
	
	private String name;
	
	private List<ValuesDTO> top5metrics;

	private List<TimeseriesDTO> timeseries;

	public DataDTO() {
		super();
	}

	
	
	public DataDTO(String name, List<ValuesDTO> top5metrics, List<TimeseriesDTO> timeseries) {
		super();
		this.name = name;
		this.top5metrics = top5metrics;
		this.timeseries = timeseries;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ValuesDTO> getTop5metrics() {
		return top5metrics;
	}

	public void setTop5metrics(List<ValuesDTO> top5metrics) {
		this.top5metrics = top5metrics;
	}

	public List<TimeseriesDTO> getTimeseries() {
		return timeseries;
	}

	public void setTimeseries(List<TimeseriesDTO> timeseries) {
		this.timeseries = timeseries;
	}

	
	
}