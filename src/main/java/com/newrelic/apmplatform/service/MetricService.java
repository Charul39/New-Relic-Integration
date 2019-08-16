package com.newrelic.apmplatform.service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrelic.apmplatform.dto.DataDTO;
import com.newrelic.apmplatform.dto.TimeseriesDTO;
import com.newrelic.apmplatform.dto.ValuesDTO;
import com.newrelic.apmplatform.repository.MetricRepository;

@Service
public class MetricService {
    
    @Autowired
    MetricRepository metricRepository;
    
      	
    

	public DataDTO getData(Long test_run_id, String graph_type,String timezone) {
		
		String gt="";
		if(graph_type.equalsIgnoreCase("Datastore")) {
			gt = "Datastore";
		}else if(graph_type.equalsIgnoreCase("WebTransaction")) {
			gt = "WebTransaction";
		}else if(graph_type.equalsIgnoreCase("External")) {
			gt = "External";
		}
		graph_type = gt;
		DataDTO dataDTO = new DataDTO();
		dataDTO.setName(graph_type);
		System.out.println(graph_type);
		
		dataDTO.setTop5metrics(GetTop5Metrics(test_run_id,gt));
		
		System.out.println("Inside data DTO");
		List<Long> timesList = metricRepository.getTimesList(test_run_id, graph_type);
		System.out.println("from time fetched correctly");
		System.out.println(timesList);
		
		List<TimeseriesDTO> lTimeseriesDTOs = new ArrayList<TimeseriesDTO>();
		
		for (int i = 0; i < timesList.size(); i++) {
			
			Long granularity = 60*1000L; // Granularity of Graph
			//String timezone = "UTC"; // Time zone
			
			TimeseriesDTO timeseriesDTO = new TimeseriesDTO();
			
			SimpleDateFormat jdf = new SimpleDateFormat("HH:mm:ss"); // Set Date Time Format
			jdf.setTimeZone(TimeZone.getTimeZone(timezone)); // Set Time zone
			
			Date fromDateTime = new Date(timesList.get(i)*1000L);
			String fromDateTimeString = jdf.format(fromDateTime);
			timeseriesDTO.setFrom_time(fromDateTimeString);
			System.out.println("from time set correctly");
			Long from_timeLong = timesList.get(i)*1000L;
			Long to_timeLong = from_timeLong + granularity;
			Date toDate = new Date(to_timeLong); // Get Time Stamp stored as Long in DB
			String toString = jdf.format(toDate);
			timeseriesDTO.setTo_time(toString);
			System.out.println("to time set correctly");
			List<ValuesDTO> lValuesDTOs = metricRepository.getMetricData(test_run_id, graph_type, timesList.get(i));
			System.out.println("lValue DTOS populated");
			timeseriesDTO.setValues(lValuesDTOs);
			System.out.println("DTO prepared inside for loop");
			lTimeseriesDTOs.add(timeseriesDTO);
		}
		dataDTO.setTimeseries(lTimeseriesDTOs);
		System.out.println("DataDTO ka timeseires set correctly");
		return dataDTO;
	}

	
	private List<ValuesDTO> GetTop5Metrics(Long test_run_id, String gt) {
		String matched = gt+"5";
		return metricRepository.getTop5Metrics(test_run_id,matched);
	}
	
}
