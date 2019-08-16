package com.newrelic.apmplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.newrelic.apmplatform.dto.KeyProfileDTO;
import com.newrelic.apmplatform.dto.KeyProfileNameDTO;
import com.newrelic.apmplatform.dto.ProfileNameDTO;
import com.newrelic.apmplatform.model.Profile;
import com.newrelic.apmplatform.model.ProfileId;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, ProfileId> {
	
	// Fetch List of Profile Names - Drop down
		public static final String FIND_PROFILE_NAMES = "SELECT new com.newrelic.apmplatform.dto.ProfileNameDTO( p.profile_id.profile_name) "
				+ "FROM Profile p WHERE p.profile_id.user_id=:user_id";
		@Query(value = FIND_PROFILE_NAMES)
		public List<ProfileNameDTO> findProfileNames(@Param("user_id") Long user_id);
		
		// Fetch List of Key Titles - Drop down
		public static final String FIND_KEY_TITLES = "SELECT DISTINCT new com.newrelic.apmplatform.dto.KeyProfileNameDTO(p.key_title) "
				+ "FROM Profile p WHERE p.profile_id.user_id=:user_id";
		@Query(value = FIND_KEY_TITLES)
		public List<KeyProfileNameDTO> findKeyTitles(@Param("user_id") Long user_id);
		
		//Check whether Key Profile is present or not
		public static final String CHECK_PROFILES = "Select COUNT(profile_name) "
				+ "from Profile p where p.profile_id.user_id=:user_id and p.profile_id.profile_name=:profile_name";
		@Query(value = CHECK_PROFILES)
		public Integer CheckProfile(@Param("user_id") Long user_id, @Param("profile_name") String profile_name);
		
		//Check whether Key Profile is present or not
		public static final String CHECK_KEY_PROFILES = "Select COUNT(key_title) "
				+ "from Profile p where p.profile_id.user_id=:user_id and p.key_title=:key_title";
		@Query(value = CHECK_KEY_PROFILES)
		public Integer CheckKeyProfile(@Param("user_id") Long user_id, @Param("key_title") String key_title);
		
		//Get Key Profile values from Database
		public static final String FIND_KEY_PROFILES = "Select distinct new com.newrelic.apmplatform.dto.KeyProfileDTO(p.key_title,p.api_key) "
				+ "from Profile p where p.profile_id.user_id=:user_id and p.key_title=:key_title";
		@Query(value = FIND_KEY_PROFILES)
		public KeyProfileDTO findKeyProfile(@Param("user_id") Long user_id, @Param("key_title") String key_title);
		
}
