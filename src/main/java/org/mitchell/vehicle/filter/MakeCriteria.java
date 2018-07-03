package org.mitchell.vehicle.filter;

import java.util.ArrayList;
import java.util.List;

import org.mitchell.vehicle.model.Vehicle;

public class MakeCriteria extends BaseVehicleCriteria{

	@Override
	public List<Vehicle> meetsCriteria(List<Vehicle> vehicles) {
		List<Vehicle> filtered = new ArrayList<Vehicle>();
		for (Vehicle vehicle : vehicles) {
			if("Toyota".equals(vehicle.getMake())){
				filtered.add(vehicle);
			}
		}
		return filtered;
	}

}
