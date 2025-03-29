package com.survey.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.survey.service.MasterDataService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class MasterDataController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	MasterDataService masterDataService;

	HttpSession httpSession = null;

	private static final Map<String, String> tableMapping = new HashMap<>();

	static {

		tableMapping.put("business_sector", "survey_master_business_sector");
		tableMapping.put("classification_units", "survey_master_classification_units");
		tableMapping.put("environmental_impact", "survey_master_environmental_impact");
		tableMapping.put("", "");
		tableMapping.put("affiliation", "survey_master_institute_affiliation");
		tableMapping.put("institute_board", "survey_master_institute_board");
		tableMapping.put("institute_type", "survey_master_institute_type");
		tableMapping.put("road_condition", "survey_master_internal_road_condition");
		tableMapping.put("legal_status", "survey_master_legal_status");
		tableMapping.put("power_feeders", "survey_master_power_feeders");
		tableMapping.put("power_supply_capacity", "survey_master_power_supply_capacity");
		tableMapping.put("transport_network", "survey_master_transportation_networks");

		
		  tableMapping.put("cluster", "survey_master_cluster smc\r\n" +
		  "JOIN survey_states ss\r\n" + "  ON smc.id_state = ss.id_state;");
		 

		tableMapping.put("total_area_range", "survey_master_total_area_range");
		tableMapping.put("tctype", "survey_master_tctype");
		tableMapping.put("water_supply", "survey_master_water_supply_sources");

	}
	
	private HashMap<String, Object> createResponse(String msgCode, String message) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("code", msgCode);
		map.put("message", message);
		return map;
	}

	@RequestMapping(value = "/getlist/{listName}", method = RequestMethod.GET)
	public Map<String, Object> getListTemplate(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("listName") String listName) {

		Map<String, Object> responseMap = new HashMap<>();

		/*
		 * String nameS = (String) httpSession.getAttribute("user"); if (nameS == null)
		 * { return createResponse("redirect", "1002"); }
		 */

		if (!tableMapping.containsKey(listName.toLowerCase())) {
			responseMap.put("code", "1001");
			responseMap.put("message", "Invalid list name provided.");
			return responseMap;
		}

		String actuallistName = tableMapping.get(listName.toLowerCase());

		String query = "SELECT * FROM " + actuallistName;

		try {

			List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

			if (result.isEmpty()) {
				responseMap.put("code", "1002");
				responseMap.put("message", "No data found for the requested list.");
			} else {
				responseMap.put("code", "1000");
				responseMap.put("message", "Data retrieved successfully.");
				responseMap.put("data", result);
			}

		} catch (Exception e) {

			responseMap.put("code", "1003");
			responseMap.put("message", "Error occurred while fetching data: " + e.getMessage());
		}

		return responseMap;
	}

	@RequestMapping(value = "/getUnitList", method = RequestMethod.GET)
	public Map<String, Object> getUnitList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id_industrial", required = true) int id) {

//    	String nameS = (String) httpSession.getAttribute("user");
//        if(nameS == null)
//        {
//        	return createResponse("redirect", "1002");
//        }

		Map<String, Object> responseMap = new HashMap<>();

		responseMap = masterDataService.getUnitList(id);

		return responseMap;
	}

	@RequestMapping(value = "/getClusterDistrictList", method = RequestMethod.GET)
	public Map<String, Object> getClusterDistrictList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "id_cluster") int id) {

//    	String nameS = (String) httpSession.getAttribute("user");
//        if(nameS == null)
//        {
//        	return createResponse("redirect", "1002");
//        }

		Map<String, Object> responseMap = new HashMap<>();

		responseMap = masterDataService.getClusterDistrictList(id);

		return responseMap;
	}


	
	
	
	@RequestMapping(value = "/getDistrictIndustrialList", method = RequestMethod.GET)
	public Map<String, Object> getClusterIndustrialList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "id_cluster") int idCluster,@RequestParam(name = "id_district") int idDistrict) {

//    	String nameS = (String) httpSession.getAttribute("user");
//        if(nameS == null)
//        {
//        	return createResponse("redirect", "1002");
//        }

		Map<String, Object> responseMap = new HashMap<>();

		responseMap = masterDataService.getClusterIndustrialList(idCluster,idDistrict);

		return responseMap;
	}



	/*
	 * @RequestMapping(value = "/editMaster/{listName}/{primaryKeyValue}", method =
	 * RequestMethod.GET) public Map<String, Object>
	 * getByPrimaryKey(HttpServletRequest request, HttpServletResponse response,
	 * 
	 * @PathVariable("listName") String listName,
	 * 
	 * @PathVariable("primaryKeyValue") String primaryKeyValue) {
	 * 
	 * Map<String, Object> responseMap = new HashMap<>();
	 * 
	 * 
	 * if (!tableMapping.containsKey(listName.toLowerCase())) {
	 * responseMap.put("code", "1001"); responseMap.put("message",
	 * "Invalid list name provided."); return responseMap; }
	 * 
	 * 
	 * String actualTableName = tableMapping.get(listName.toLowerCase());
	 * 
	 * try {
	 * 
	 * String pkQuery =
	 * "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME = ? AND CONSTRAINT_NAME = 'PRIMARY'"
	 * ;
	 * 
	 * 
	 * List<Map<String, Object>> pkColumnResult = jdbcTemplate.queryForList(pkQuery,
	 * actualTableName);
	 * 
	 * 
	 * if (pkColumnResult.isEmpty()) { responseMap.put("code", "1002");
	 * responseMap.put("message", "No primary key found for the specified table.");
	 * return responseMap; }
	 * 
	 * 
	 * String pkColumn = (String) pkColumnResult.get(0).get("COLUMN_NAME");
	 * 
	 * 
	 * String query = "SELECT * FROM " + actualTableName + " WHERE " + pkColumn +
	 * " = ?";
	 * 
	 * 
	 * List<Map<String, Object>> result = jdbcTemplate.queryForList(query,
	 * primaryKeyValue);
	 * 
	 * 
	 * if (result.isEmpty()) { responseMap.put("code", "1003");
	 * responseMap.put("message", "No data found for the provided ID"); } else {
	 * responseMap.put("code", "1000"); responseMap.put("message",
	 * "Data retrieved successfully."); responseMap.put("data", result); } } catch
	 * (Exception e) { responseMap.put("code", "1004"); responseMap.put("message",
	 * "Error occurred while fetching data: " + e.getMessage()); }
	 * 
	 * return responseMap; }
	 * 
	 * 
	 * @RequestMapping(value = "/deleteMaster/{listName}/{primaryKeyValue}", method
	 * = RequestMethod.POST) public Map<String, Object>
	 * deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response,
	 * 
	 * @PathVariable("listName") String listName,
	 * 
	 * @PathVariable("primaryKeyValue") String primaryKeyValue) {
	 * 
	 * Map<String, Object> responseMap = new HashMap<>();
	 * 
	 * 
	 * if (!tableMapping.containsKey(listName.toLowerCase())) {
	 * responseMap.put("code", "1001"); responseMap.put("message",
	 * "Invalid list name provided."); return responseMap; }
	 * 
	 * 
	 * String actualTableName = tableMapping.get(listName.toLowerCase());
	 * 
	 * try {
	 * 
	 * String pkQuery =
	 * "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME = ? AND CONSTRAINT_NAME = 'PRIMARY'"
	 * ; List<Map<String, Object>> pkColumnResult =
	 * jdbcTemplate.queryForList(pkQuery, actualTableName);
	 * 
	 * if (pkColumnResult.isEmpty()) { responseMap.put("code", "1002");
	 * responseMap.put("message", "No primary key found for the specified table.");
	 * return responseMap; }
	 * 
	 * String pkColumn = (String) pkColumnResult.get(0).get("COLUMN_NAME");
	 * 
	 * 
	 * String deleteQuery = "DELETE FROM " + actualTableName + " WHERE " + pkColumn
	 * + " = ?";
	 * 
	 * 
	 * int rowsAffected = jdbcTemplate.update(deleteQuery, primaryKeyValue);
	 * 
	 * if (rowsAffected > 0) { responseMap.put("code", "1000");
	 * responseMap.put("message", "Data deleted successfully."); } else {
	 * responseMap.put("code", "1003"); responseMap.put("message",
	 * "No data found to delete for the provided ID."); }
	 * 
	 * } catch (Exception e) { responseMap.put("code", "1004");
	 * responseMap.put("message", "Error occurred while deleting data: " +
	 * e.getMessage()); }
	 * 
	 * return responseMap; }
	 */

}
