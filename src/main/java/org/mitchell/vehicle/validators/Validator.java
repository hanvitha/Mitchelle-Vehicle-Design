package org.mitchell.vehicle.validators;

/**
 * 
 * interface for validations. 
 *
 */

public interface Validator<T> {

	/**
	 *  returns true if validation is successful, else false. 
	 */
	public boolean validate(T t);
	
}
