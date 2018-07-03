package org.mitchell.vehicle.validators;

import org.mitchell.vehicle.model.Vehicle;

/**
 * 
 *  Validation for 'model' of the vehicle object.
 */
public class ModelValidator extends BaseVehicleValidator{

	@Override
	public boolean validate(Vehicle t) {
		if(isNullOrEmpty(t.getModel())){
			return false;
		}
		return true;
	}

}
