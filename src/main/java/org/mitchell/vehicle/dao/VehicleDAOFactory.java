package org.mitchell.vehicle.dao;

/**
 * 
 * Factory class for Vehicle DAO. A new Data source can be easily supported by modifying this factory.
 */
public class VehicleDAOFactory {
	
	/**
	 * 
	 * returns DAO related to a particular data source.
	 * 
	 */
	public static VehicleDAO get(String datasource){
		if("inmemory".equals(datasource)){
			return InMemoryVehicles.getInstance();
		}
		return null;
	}

}
