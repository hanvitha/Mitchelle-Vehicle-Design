package org.mitchell.vehicle.controller;

import java.util.List;

import org.mitchell.vehicle.dao.VehicleDAO;
import org.mitchell.vehicle.dao.VehicleDAOFactory;
import org.mitchell.vehicle.filter.BaseVehicleCriteria;
import org.mitchell.vehicle.model.Vehicle;
import org.mitchell.vehicle.validators.BaseVehicleValidator;

/**
 * Controller for CRUD operations on Vehicles. 
 *
 */
public class CRUDVehicleController {
	
	private VehicleDAO vehicleDAO = VehicleDAOFactory.get("inmemory");
	private List<BaseVehicleCriteria> criteria;
	private List<BaseVehicleValidator> validators;
	
	public CRUDVehicleController(List<BaseVehicleCriteria> criteria, List<BaseVehicleValidator> validators) {
		this.criteria = criteria;
		this.validators = validators;
	}
	
	/**
 	 * gets all vehicles present in data source.
	 */
	public List<Vehicle> get(){
		return vehicleDAO.get();
	}
	
	/**
	 * gets a vehicle matching with given id. 
	 */
	public Vehicle getById(int id){
		return vehicleDAO.getById(id);
	}
	
	/**
	 * gets list of vehicles meeting certain criteria 
	 */
	public List<Vehicle> getByCriteria(){
		List<Vehicle> filtered = get();
		for(BaseVehicleCriteria eachCriteria : criteria) {
			filtered = eachCriteria.meetsCriteria(filtered);
		}
		return filtered;
	}

	/**
	 * creates vehicle in data source after passing all validations.
	 */
	public boolean create(int id, int year, String make, String model){
		Vehicle vehicle = new Vehicle(id, year, make, model);
		for (BaseVehicleValidator eachValidation : validators) {
			if(!eachValidation.validate(vehicle)){
				return false;
			}
		}
		
		return vehicleDAO.create(vehicle);
		
	}
	
	/**
	 * updates vehicle in data source after passing all validations.
	 */
	public boolean update(int id, int year, String make, String model){
		Vehicle vehicle = new Vehicle(id, year, make, model);
		for (BaseVehicleValidator eachValidation : validators) {
			if(!eachValidation.validate(vehicle)){
				return false;
			}
		}
		return vehicleDAO.update(vehicle);
		
	}

	/**
	 * deletes vehicle form data source. 
	 */
	public boolean delete(int id){
		return vehicleDAO.delete(id);
		
	}

}
