package com.survey.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.dao.MasterDataDao;

@Service
public class MasterDataService {
	
	@Autowired
	MasterDataDao masterDataDao;

	public Map<String, Object> getUnitList(int id) {

		return masterDataDao.getUnitList( id);
	}

	public Map<String, Object> getClusterDistrictList(int id) {
		
		return masterDataDao.getClusterDistrictList(id);
	}
	
//	public Map<String, Object> getClusterList(int id) {
//
//		return masterDataDao.getClusterList( id);
//	}
	
	
public Map<String, Object> getClusterIndustrialList(int idCluster,int idDistrict) {
		
		return masterDataDao.getClusterIndustrialList(idCluster,idDistrict);
	}

}
