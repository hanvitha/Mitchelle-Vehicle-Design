package org.mitchell.vehicle.dao;

import java.util.List;

import org.mitchell.vehicle.model.Vehicle;

/**
 * The Data Access Object for Vehicles. For every data source(like DB, file
 * system etc) a new implementation of this class can be created.
 * 
 *
 */
public interface VehicleDAO {

	/**
	 * gets all vehicles present in data source.
	 */
	public List<Vehicle> get();
	
	/**
	 * gets a vehicle matching with given id. 
	 */
	public Vehicle getById(int id);

	/**
	 * creates vehicle in data source.
	 */
	public boolean create(Vehicle vehicle);
	
	/**
	 * updates vehicle in data source.
	 */
	public boolean update(Vehicle vehicle);

	/**
	 * deletes vehicle form data source. 
	 */
	public boolean delete(int id);

}
