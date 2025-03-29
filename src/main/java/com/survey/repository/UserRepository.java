package com.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.survey.model.SurveyAdminUser;

@Repository
public interface UserRepository extends JpaRepository<SurveyAdminUser, Integer> {
	
	
	@Query(value = "SELECT user_password  FROM survey_admin_users where user_name =?1  ", nativeQuery = true)
	String getUserPasswordByUsername(String username);

}
