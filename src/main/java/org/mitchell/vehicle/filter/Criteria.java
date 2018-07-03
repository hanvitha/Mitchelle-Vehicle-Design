package org.mitchell.vehicle.filter;

import java.util.List;

/**
 * 
 * Criteria class that filter objects of given type.
 * 
 */
public interface Criteria<T> {
	
	/**
	 * returns list of objects that meet criteria. 
	 */
	public List<T> meetsCriteria(List<T> vehicles);
	
}
