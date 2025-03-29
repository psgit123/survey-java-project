package com.survey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.survey.model.SurveyMasterCluster;

public interface SurveyMasterClusterRepository extends JpaRepository<SurveyMasterCluster, Integer> {
	
	@Query(value = "SELECT id_units ,units_name ,status FROM survey_master_units WHERE id_industrial=?1 ", nativeQuery = true)
	List<Object> findUnitListByIdIndustrial(int idIndustrial);
//	
//	@Query(value = "SELECT c.id_cluster ,c.cluster_name ,c.status FROM survey_master_cluster c JOIN survey_master_cluster_district cd on c.id_cluster = cd.id_cluster JOIN survey_district sd on sd.id_district = cd.id_district  ", nativeQuery = true)
//	List<Object> findClusterListByIdIndustrial(int id);
	
	@Query(value = "SELECT c.district , c.id_district , cd.id_cluster, cd.status FROM survey_master_cluster_district cd JOIN survey_district c ON c.id_district = cd.id_district where cd.id_cluster=?1", nativeQuery = true)
	List<Object> findClusterDistrictListbyIdCluster(int idCluster);

	@Query(value = "SELECT i.id_industrial , i.industrial_name FROM survey_master_industrial i JOIN survey_master_cluster_district cd ON i.id_master_cluster_district = cd.id_master_cluster_district where cd.id_cluster=?1 AND cd.id_district=?2", nativeQuery = true)
	List<Object> findClusterIndustrialListbyIdClusterAndIdDistrict(int idCluster, int idDistrict);

	

}
