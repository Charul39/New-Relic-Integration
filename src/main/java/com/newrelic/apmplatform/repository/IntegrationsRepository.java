package com.newrelic.apmplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.newrelic.apmplatform.model.Integrations;

public interface IntegrationsRepository extends JpaRepository<Integrations, Long>{

	public static final String FIND_PROFILES = "SELECT apm_profile"
			+ " FROM integrations WHERE user_id=:user_id and test_id=:test_id";
	@Query(value = FIND_PROFILES, nativeQuery = true)
	public List<String> findProfilesByTest(@Param("test_id") Long test_id, @Param("user_id") Long user_id);
}
