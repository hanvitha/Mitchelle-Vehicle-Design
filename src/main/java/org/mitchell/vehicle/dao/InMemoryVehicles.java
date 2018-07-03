package org.mitchell.vehicle.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mitchell.vehicle.model.Vehicle;

/**
 *  singleton object to access in-memory vehicle objects. 
 *
 */
public class InMemoryVehicles implements VehicleDAO{
	
	/**
	 * Id acts as a primary key.
	 */
	private Map<Integer,Vehicle> vehicles = new HashMap<Integer,Vehicle>();
	
	private InMemoryVehicles() {
	}
	
	private static final InMemoryVehicles INSTANCE = new InMemoryVehicles();
	
	public static InMemoryVehicles getInstance() {
		return INSTANCE;
	}
	
	@Override
	public List<Vehicle> get() {
		List<Vehicle> temp_vehicles = new ArrayList<>();
		Collection<Vehicle> values = vehicles.values();
		for (Vehicle vehicle : values) {
			temp_vehicles.add(vehicle);
		}
		return temp_vehicles;
	}

	@Override
	public Vehicle getById(int id) {
		return vehicles.get(id);
	}

	@Override
	public boolean create(Vehicle vehicle) {
		vehicles.put(vehicle.getId(), vehicle);
		return true;
	}

	@Override
	public boolean update(Vehicle vehicle) {
		vehicles.put(vehicle.getId(), vehicle);
		return true;
	}

	@Override
	public boolean delete(int id) {
		vehicles.remove(id);
		return true;
	}

}
