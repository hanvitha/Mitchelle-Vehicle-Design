package org.mitchell.vehicle.validators;

import org.mitchell.vehicle.model.Vehicle;

/**
 * 
 *  Validation for 'year' of the vehicle object.
 */
public class YearValidator extends BaseVehicleValidator{

	@Override
	public boolean validate(Vehicle t) {
		int year = t.getYear();
		if(year < 1950 || year > 2050){
			return false;
		}
		return true;
	}

}
