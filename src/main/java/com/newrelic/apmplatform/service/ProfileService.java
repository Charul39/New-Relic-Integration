package com.newrelic.apmplatform.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.newrelic.apmplatform.dto.AppNameDTO;
import com.newrelic.apmplatform.dto.KeyProfileDTO;
import com.newrelic.apmplatform.dto.KeyProfileNameDTO;
import com.newrelic.apmplatform.dto.ProfileNameDTO;
import com.newrelic.apmplatform.dto.SaveProfileDTO;
import com.newrelic.apmplatform.exceptionhandling.RestTemplateResponseErrorHandler;
import com.newrelic.apmplatform.model.Integrations;
import com.newrelic.apmplatform.model.Profile;
import com.newrelic.apmplatform.model.ProfileId;
import com.newrelic.apmplatform.repository.IntegrationsRepository;
import com.newrelic.apmplatform.repository.ProfileRepository;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private IntegrationsRepository integrationsRepository;
	
	public List<AppNameDTO> findAppList(Long user_id , @Valid KeyProfileDTO key_profile){
		
		String key_title = key_profile.getKey_title();
		System.out.println(key_title);
		
		Integer check = profileRepository.CheckKeyProfile(user_id, key_title);
		System.out.println(check);
		String api_key ;
		if(check == 0){
			System.out.println("Key Profile Not found");
			 api_key = key_profile.getApi_key();
		}
		else {
			System.out.println("Key Profile Found");
			KeyProfileDTO keyProfileDTO = profileRepository.findKeyProfile(user_id, key_title);
			api_key = keyProfileDTO.getApi_key();
			
		}
		List<AppNameDTO> ans = new ArrayList<>();
		//try {
			ans= findAppListUtil(api_key);
		//}catch(ResponseStatusException e) {
			
		//}
		return ans;
		
	}
	
	public List<AppNameDTO> findAppListUtil(String api_key) throws ResponseStatusException{
		
		System.out.println("In HostListUtil Method");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		
		String urlString = "https://api.newrelic.com/v2/applications.json";
		
		System.out.println(urlString);
		headers.set("X-Api-Key", api_key);
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(urlString, HttpMethod.GET, httpEntity, String.class);
		
		String s = responseEntity.getBody();
		
		List<AppNameDTO> lappNameDTOs = new ArrayList<>();
		
		try {
				JSONObject joMain = new JSONObject(s);
				JSONArray jaAppn = (JSONArray)joMain.get("applications");
				
				for (int i = 0; i < jaAppn.length(); i++) {
					
					AppNameDTO appNameDTO = new AppNameDTO();
					JSONObject joApp = (JSONObject)jaAppn.get(i);
					
					Integer app_id = joApp.getInt("id");
					appNameDTO.setApp_id(app_id);
					
					String app_name = joApp.getString("name");
					appNameDTO.setApp_name(app_name);
					
					appNameDTO.toString();
					lappNameDTOs.add(appNameDTO);
				}
				
		        System.out.println("App List Fetched...");    
			}
		catch (Exception e) {
			System.out.println("Error In HostListUtil");
			e.printStackTrace();
		}
		return lappNameDTOs;
	}
	
	public void saveProfile(Long user_id, SaveProfileDTO saveProfileDTO) {
		
		Profile profile = new Profile();
		Integrations integrations = new Integrations();
		/*
		if(saveProfileDTO.getProfile_name().length()==0) {
			
			integrations.setInfra_profile("");
			integrations.setTest_id(saveProfileDTO.getTest_id());
			integrations.setUser_id(user_id);
			integrationsRepository.save(integrations);
			System.out.println("NewRelic APM integration disabled for test ID "+saveProfileDTO.getTest_id().toString());
			return;
		}
		*/
		
		String profile_name = saveProfileDTO.getProfile_name();
		System.out.println(profile_name);
		
		Integer checkProfile = profileRepository.CheckProfile(user_id, profile_name);
		System.out.println(checkProfile);
		
		if(checkProfile == 0){
			System.out.println("Profile Not found");
			ProfileId profileId = new ProfileId();
			profileId.setProfile_name(profile_name);
			profileId.setUser_id(user_id);
			
			profile.setProfile_id(profileId);
			
			String key_title = saveProfileDTO.getKey_title();
			Integer checkKeyProfile = profileRepository.CheckKeyProfile(user_id, key_title);
			
			if(checkKeyProfile==0) {
				System.out.println("Key Profile Not Found");
				
				profile.setKey_title(key_title); // Key Title Set
				
				String api_key = saveProfileDTO.getApi_key();
				profile.setApi_key(api_key); // API Key Set
			}
			else {
				
				
				System.out.println("Key Profile Found");
				
				KeyProfileDTO keyProfileDTO;
				keyProfileDTO = profileRepository.findKeyProfile(user_id, key_title);
				
				profile.setApi_key(keyProfileDTO.getApi_key()); // API Key Set
				profile.setKey_title(key_title); // Key Title Set
			}
			
			String app_name = saveProfileDTO.getApp_name();
			profile.setApp_name(app_name); // App Name Set
			
			Integer app_id = saveProfileDTO.getApp_id();
			profile.setApp_id(app_id); // App Id Set
			
			findAppListUtil(profile.getApi_key());// VALIDATION STEP, Will throw exception if invalid profile
			
			profileRepository.save(profile); // Profile Saved
			System.out.println("Profile Saved");
			
			integrations.setInfra_profile(profile_name);
			integrations.setTest_id(saveProfileDTO.getTest_id());
			integrations.setUser_id(user_id);
			integrationsRepository.save(integrations);
			System.out.println("Integrations Table Updated");
			
		}
		else {
			profile = profileRepository.findById(new ProfileId(user_id, saveProfileDTO.getProfile_name())).get();
			System.out.println("Profile Found");
			List<AppNameDTO> temp = findAppListUtil(profile.getApi_key());// VALIDATION STEP, Will throw exception if invalid profile
			if(temp.size()==0) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No applications associated with given account");
			}
			integrations.setInfra_profile(profile_name);
			integrations.setTest_id(saveProfileDTO.getTest_id());
			integrations.setUser_id(user_id);
			integrationsRepository.save(integrations);
			System.out.println("Integrations Table Updated");
		}
	}
	
	public List<ProfileNameDTO> findProfileNames(Long user_id){
		return profileRepository.findProfileNames(user_id);
	}
	
	public List<KeyProfileNameDTO> findKeyTitles(Long user_id){
		return profileRepository.findKeyTitles(user_id);
	}
}