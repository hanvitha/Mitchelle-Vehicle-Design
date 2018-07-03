package org.mitchell.vehicle.validators;

import org.mitchell.vehicle.model.Vehicle;

/**
 * 
 *  Validation for 'make' of the vehicle object.
 */
public class MakeValidator extends BaseVehicleValidator{

	@Override
	public boolean validate(Vehicle t) {
		
		if(isNullOrEmpty(t.getMake())){
			return false;
		}
		return true;
	}
	
}
