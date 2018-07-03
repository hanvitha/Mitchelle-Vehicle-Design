package org.mitchell.vehicle.validators;

import org.mitchell.vehicle.model.Vehicle;

/**
 * This is base validator for vehicle objects. This validator separates out the validation logic of vehicle object to a different class.
 * Any new validation to a vehicle can be introduced by creating a implementation of this class with modifying vehicle object.
 *
 */
public abstract class BaseVehicleValidator implements Validator<Vehicle>{

	public boolean isNull(String string){
		return string == null;
	}
	
	public boolean isEmpty(String string){
		return string.isEmpty();
	}
	
	public boolean isNullOrEmpty(String string){
		return string == null || string.isEmpty();
	}
	
}
