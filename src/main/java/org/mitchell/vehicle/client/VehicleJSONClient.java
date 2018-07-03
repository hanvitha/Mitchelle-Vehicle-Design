package org.mitchell.vehicle.client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.mitchell.vehicle.controller.CRUDVehicleController;
import org.mitchell.vehicle.filter.BaseVehicleCriteria;
import org.mitchell.vehicle.filter.MakeCriteria;
import org.mitchell.vehicle.model.Vehicle;
import org.mitchell.vehicle.validators.BaseVehicleValidator;
import org.mitchell.vehicle.validators.MakeValidator;
import org.mitchell.vehicle.validators.ModelValidator;
import org.mitchell.vehicle.validators.YearValidator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  This client takes URL as input and returns response in JSON format.
 *
 */
public class VehicleJSONClient implements IClient<String, JSONObject>{

	CRUDVehicleController controller;
	
	@Override
	public JSONObject sendRequest(String request) throws Exception{
		List<BaseVehicleCriteria> criteria = new ArrayList<BaseVehicleCriteria>();
		criteria.add(new MakeCriteria());
		
		List<BaseVehicleValidator> validators = new ArrayList<BaseVehicleValidator>();
		validators.add(new MakeValidator());
		validators.add(new YearValidator());
		validators.add(new ModelValidator());
		
		controller = new CRUDVehicleController(criteria, validators);
		
		if(request == null || request.isEmpty()){
			return null;
		}
		
		if(request.contains("GET/")){
			return processGETRequest(request);
		}else if(request.contains("CREATE/")){
			return processCREATERequest(request);
		}else if(request.contains("UPDATE/")){
			return processUPDATERequest(request);
		}else if(request.contains("DELETE/")){
			return processDELETERequest(request);
		}
		
		return null;
	}

	private JSONObject processGETRequest(String request) throws JsonProcessingException, JSONException{
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		String[] split = request.split("/");
		/*
		 *  get/vehicles
		 */
		if(split.length == 2){
			List<Vehicle> vehicles = controller.get();
			jsonString = mapper.writeValueAsString(vehicles);
		}
		/*
		 *  get/vehicles/{id} 
		 */
		if(split.length == 3){
			Vehicle vehicle = controller.getById(Integer.valueOf(split[2]));
			jsonString = mapper.writeValueAsString(vehicle);
		}
		return new JSONObject(jsonString);
	}
	
	/*
	 *  CREATE/vehicles/?id=2&year=2000&make=nissan&model=SUV 
	 */
	private JSONObject processCREATERequest(String request) throws JSONException {
		int id = Integer.parseInt(getParameter(request,"id=(.+?)&"));
		int year = Integer.parseInt(getParameter(request,"year=(.+?)&"));
		String make = getParameter(request,"make=(.+?)&");
		String model = getParameter(request,"model=(.+?)&");
		
		boolean isCreated = controller.create(id, year, make, model);
		if(isCreated){
			return new JSONObject().put("status", "Created");
		}
		return new JSONObject().put("status", "Not Created");
	}
	

	/*
	 *  UPDATE/vehicles/?id=2&year=2000&make=nissan&model=SUV 
	 */
	private JSONObject processUPDATERequest(String request) throws JSONException {
		int id = Integer.parseInt(getParameter(request,"id=(.+?)&"));
		int year = Integer.parseInt(getParameter(request,"year=(.+?)&"));
		String make = getParameter(request,"make=(.+?)&");
		String model = getParameter(request,"model=(.+?)&");
		
		boolean isUpdated = controller.update(id, year, make, model);
		if(isUpdated){
			return new JSONObject().put("status", "Updated");
		}
		return new JSONObject().put("status", "Not Updated");
	}
	

	/*
	 *  DELETE/vehicles/{id} 
	 */
	private JSONObject processDELETERequest(String request) throws JSONException {
		String[] split = request.split("/");
		if(split.length == 3){
			boolean isDeleted =  controller.delete(Integer.valueOf(split[2]));
			if(isDeleted){
				return new JSONObject().put("status", "Deleted");
			}
		}
		return new JSONObject().put("status", "Not Deleted");
	}

	private String getParameter(String request, String pattern) {
		Pattern id_pattern = Pattern.compile(pattern);
		Matcher matcher = id_pattern.matcher(request);
		String group = matcher.group(1);
		String[] split = group.split("=");
		return split[1];
	}
}
