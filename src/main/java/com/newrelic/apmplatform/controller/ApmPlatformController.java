package com.newrelic.apmplatform.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newrelic.apmplatform.dto.DataDTO;
import com.newrelic.apmplatform.dto.AppNameDTO;
import com.newrelic.apmplatform.dto.KeyProfileNameDTO;
import com.newrelic.apmplatform.dto.KeyProfileDTO;
import com.newrelic.apmplatform.dto.ProfileNameDTO;
import com.newrelic.apmplatform.dto.SaveProfileDTO;
import com.newrelic.apmplatform.dto.TimezoneDTO;
import com.newrelic.apmplatform.service.IntegrationService;
import com.newrelic.apmplatform.service.MetricService;
import com.newrelic.apmplatform.service.ProfileService;

@RestController
@RequestMapping("/apm")
@CrossOrigin
public class ApmPlatformController {
	
	@Autowired
	ProfileService profileService;
		
	@Autowired
	MetricService metricService;
	
	@Autowired
	IntegrationService integrationService;
	
	//0. Index Page
	@RequestMapping("/")
	public String start() {
		return "index";
	}
	
	// 0. Fetch Profile Name corresponding to a Test Id and User Id - From DB
		@RequestMapping(value = "/{test_id}/{user_id}/getProfilesByTest", produces = "application/json")
	    public List<String> getProfilesByTest(@PathVariable("user_id") Long user_id, @PathVariable("test_id") Long test_id){
			return integrationService.FindProfilesByTest(test_id, user_id);
	    }
		
		//1. Get List of Profile Names - Database //--------------------------------------------
		@RequestMapping(value = "/{user_id}/getProfiles", produces = "application/json")
		public List<ProfileNameDTO> getAllProfiles(@PathVariable("user_id") Long user_id){
			return profileService.findProfileNames(user_id);
		}
		
		//2. Get List of Key Profiles - Database //--------------------------------------------
		@RequestMapping("/{user_id}/getKeyProfiles")
		public List<KeyProfileNameDTO> getAllKeyProfiles(@PathVariable("user_id") Long user_id){
			return profileService.findKeyTitles(user_id);
		}
		
		//3. Get List of Applications - New Relic //--------------------------------------------
		@RequestMapping(value = "/{user_id}/getAppList", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<AppNameDTO> getAllApp(@PathVariable("user_id") Long user_id, @Valid@RequestBody KeyProfileDTO key_profile){
			return profileService.findAppList(user_id, key_profile);
		}
		
		//4. Save Profile - Database + Integrations Table Entry
		@PostMapping(value = "/{user_id}/saveProfile")
		public String saveProfile(@Valid @RequestBody SaveProfileDTO inputProfile, @PathVariable("user_id") Long user_id){
			profileService.saveProfile(user_id, inputProfile);
			return "Profile Saved";
		}
		
		
		
		//6. Fetch Data for specific graphs - From DB
	    @RequestMapping(value = "/{test_run_id}/{graph_type}/getData",produces = MediaType.APPLICATION_JSON_VALUE)
	    public DataDTO getData(@PathVariable("test_run_id") Long test_run_id, @PathVariable("graph_type") String graph_type,@RequestBody TimezoneDTO timezone){
	    	System.out.println("controller method called");
	    	return metricService.getData(test_run_id, graph_type,timezone.getTimezone());
	    }
	
}