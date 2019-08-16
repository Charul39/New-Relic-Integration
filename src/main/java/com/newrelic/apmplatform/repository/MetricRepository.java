package com.newrelic.apmplatform.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.newrelic.apmplatform.dto.ValuesDTO;
import com.newrelic.apmplatform.model.MetricsWebTrans;

@Repository
public interface MetricRepository extends JpaRepository<MetricsWebTrans, Long>{

	//Select Distinct Times
		public static final String FIND_TIMES = "SELECT DISTINCT from_time"
				+ " FROM nr_apm_metrics WHERE test_run_id=:test_run_id and graph_type=:graph_type";
		@Query(value = FIND_TIMES, nativeQuery = true)
		public List<Long> getTimesList(@Param("test_run_id") Long test_run_id, @Param("graph_type") String graph_type);
		
		//Fetch List of Metric Name and Data Value
		public static final String FIND_METRIC_DATA = "SELECT NEW com.newrelic.apmplatform.dto.ValuesDTO(m.metric_name, m.avg_response_time) "
				+ "FROM MetricsWebTrans m WHERE m.test_run_id=:test_run_id and m.graph_type=:graph_type and m.from_time=:from_datetime";
		@Query(value = FIND_METRIC_DATA)
		public List<ValuesDTO> getMetricData(@Param("test_run_id") Long test_run_id,
				@Param("graph_type") String graph_type, @Param("from_datetime") Long from_datetime);
		
		@Query(value="SELECT NEW com.newrelic.apmplatform.dto.ValuesDTO(m.metric_name, m.avg_response_time) "
				+ "FROM MetricsWebTrans m WHERE m.test_run_id=:test_run_id and m.graph_type=:graph_type ")
		public List<ValuesDTO> getTop5Metrics(@Param("test_run_id")Long test_run_id, @Param("graph_type") String graph_type); 
}
