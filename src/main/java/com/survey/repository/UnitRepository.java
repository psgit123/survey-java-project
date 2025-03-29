package com.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.survey.model.SurveyMasterUnit;

public interface UnitRepository extends JpaRepository<SurveyMasterUnit, Integer> {

}
