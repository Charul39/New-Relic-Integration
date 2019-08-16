package com.newrelic.apmplatform.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "nr_apm_metrics", indexes = {@Index(columnList = "test_run_id" , name = "test_run_id")})
public class MetricsWebTrans implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer metric_id;
	
	private Long test_run_id; 
	
	private String metric_name;
	
	private String graph_type;
	
	
	private Long to_time;
	
	private Long from_time;
	
	private Double avg_response_time;

	public MetricsWebTrans() {
		super();
	}

	public MetricsWebTrans(Integer metric_id, Long test_run_id, String metric_name, Long to_time, Long from_time,
			Double avg_response_time,String graph_type) {
		super();
		this.metric_id = metric_id;
		this.test_run_id = test_run_id;
		this.metric_name = metric_name;
		this.to_time = to_time;
		this.from_time = from_time;
		this.graph_type=graph_type;
		this.avg_response_time = avg_response_time;
	}

	public Integer getMetric_id() {
		return metric_id;
	}

	public void setMetric_id(Integer metric_id) {
		this.metric_id = metric_id;
	}

	public Long getTest_run_id() {
		return test_run_id;
	}

	public void setTest_run_id(Long test_run_id) {
		this.test_run_id = test_run_id;
	}

	public String getMetric_name() {
		return metric_name;
	}

	public void setMetric_name(String metric_name) {
		this.metric_name = metric_name;
	}

	public Long getTo_time() {
		return to_time;
	}

	public void setTo_time(Long to_time) {
		this.to_time = to_time;
	}

	public Long getFrom_time() {
		return from_time;
	}

	public void setFrom_time(Long from_time) {
		this.from_time = from_time;
	}

	public Double getAvg_response_time() {
		return avg_response_time;
	}

	public void setAvg_response_time(Double avg_response_time) {
		this.avg_response_time = avg_response_time;
	}

	public String getGraph_type() {
		return graph_type;
	}

	public void setGraph_type(String graph_type) {
		this.graph_type = graph_type;
	}

	@Override
	public String toString() {
		return "MetricsWebTrans [metric_id=" + metric_id + ", test_run_id=" + test_run_id + ", metric_name="
				+ metric_name + ", to_time=" + to_time + ", from_time=" + from_time + ", avg_response_time="
				+ avg_response_time + "]";
	}
	
}
