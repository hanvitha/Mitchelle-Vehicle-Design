package org.mitchell.vehicle.client;

/**
 *
 * Client for CRUD operations on Vehicles.
 * U -> the type of request.
 * V -> the type of response.
 * 
 * Client can be implemented in many ways. It could be a REST client or
 * a Command Line client etc. Each client can have a different request and response type.
 * 
 * EX: request could be in string format and response can be XML or JSON or HTML format.
 * 
 */
public interface IClient<U,V> {

	public V sendRequest(U request) throws Exception;
	
}
