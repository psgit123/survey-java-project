package com.survey.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.bean.MasterDataBean;
import com.survey.model.SurveyMasterCluster;
import com.survey.model.SurveyMasterUnit;
import com.survey.repository.SurveyMasterClusterRepository;

@Service
public class MasterDataDao {
	
	@Autowired
	SurveyMasterClusterRepository surveyMasterClusterRepository; 

	public Map<String, Object> getUnitList(int id) {
		
		
		Map<String, Object> responseMap = new HashMap<>();
		
		List<Object> list = surveyMasterClusterRepository.findUnitListByIdIndustrial(id);
		
		if (list.isEmpty()) {
            responseMap.put("code", "1002");
            responseMap.put("message", "No Units found for the given ID.");
            return responseMap;
        }
		
		Iterator<Object> itr = list.iterator();
		List<SurveyMasterUnit> unitList = new ArrayList<SurveyMasterUnit>();
		SurveyMasterUnit unit = null;
		while(itr.hasNext())
		{
			Object[] obj = (Object[])itr.next();
			unit = new SurveyMasterUnit();
			
			long idUnit = (long) obj[0];
			String uname = (String) obj[1];
			byte status = (byte) obj[2];
			
			unit.setUnitsName(uname);
			unit.setIdUnits(idUnit);
			unit.setStatus(status);
			
			unitList.add(unit);
		}
		
		
		responseMap.put("code", "1000");
        responseMap.put("message", "Data retrieved successfully.");
        responseMap.put("data", unitList);
        
        
        return responseMap;
		
	}
	
	/*
	 * public Map<String, Object> getClusterList(int id) {
	 * 
	 * 
	 * Map<String, Object> responseMap = new HashMap<>();
	 * 
	 * List<Object> list = surveyMasterClusterRepository.findClusterList();
	 * 
	 * if (list.isEmpty()) { responseMap.put("code", "1002");
	 * responseMap.put("message", "No Units found for the given ID."); return
	 * responseMap; }
	 * 
	 * Iterator<Object> itr = list.iterator(); List<SurveyMasterCluster> unitList =
	 * new ArrayList<SurveyMasterCluster>(); SurveyMasterCluster cluster = null;
	 * while(itr.hasNext()) { Object[] obj = (Object[])itr.next(); cluster = new
	 * SurveyMasterCluster();
	 * 
	 * long idCluster = (long) obj[0]; String cname = (String) obj[1]; byte Status =
	 * (int)
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * responseMap.put("code", "1000"); responseMap.put("message",
	 * "Data retrieved successfully."); responseMap.put("data", unitList);
	 * 
	 * return responseMap;
	 * 
	 * }
	 */
	
	
	public Map<String, Object> getClusterDistrictList(int idCluster) {
		
		
		Map<String, Object> responseMap = new HashMap<>();
		
		List<Object> list = surveyMasterClusterRepository.findClusterDistrictListbyIdCluster(idCluster);
		
		if (list.isEmpty()) {
            responseMap.put("code", "1002");
            responseMap.put("message", "No Clusters found for the given ID.");
            return responseMap;
        }
		
		Iterator<Object> itr = list.iterator();
		List<MasterDataBean> districtList = new ArrayList<MasterDataBean>();
		MasterDataBean district = null;
		
		while(itr.hasNext())
		{
			Object[] obj = (Object[])itr.next();
			district = new MasterDataBean();
			
			String districtName = (String) obj[0];
			int idDistrict = (int) obj[1];	
			
			district.setName(districtName);
			district.setId(idDistrict);
			
			districtList.add(district);
		}
		
		
		responseMap.put("code", "1000");
        responseMap.put("message", "Data retrieved successfully.");
        responseMap.put("data", districtList);
        
        return responseMap;
		
	}

	public Map<String, Object> getClusterIndustrialList(int idCluster, int idDistrict) {
		
		Map<String, Object> responseMap = new HashMap<>();
		
		
		List<Object> list = surveyMasterClusterRepository.findClusterIndustrialListbyIdClusterAndIdDistrict(idCluster,idDistrict);
		
		if (list.isEmpty()) {
            responseMap.put("code", "1002");
            responseMap.put("message", "No Industrial found for the given ID.");
            return responseMap;
        }
		
		Iterator<Object> itr = list.iterator();
		List<MasterDataBean> industrialList = new ArrayList<MasterDataBean>();
		MasterDataBean industrial = null;
		
		while(itr.hasNext())
		{
			Object[] obj = (Object[])itr.next();
			industrial = new MasterDataBean();
			
			String name = (String) obj[1];
			int id = (int) obj[0];	
			
			industrial.setName(name);
			industrial.setId(id);
			
			industrialList.add(industrial);
		}
		
		
		responseMap.put("code", "1000");
        responseMap.put("message", "Data retrieved successfully.");
        responseMap.put("data", industrialList);
        

		
		
		return responseMap ;
	}

}
