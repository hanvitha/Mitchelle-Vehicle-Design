package org.mitchell.vehicle.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mitchell.vehicle.controller.CRUDVehicleController;
import org.mitchell.vehicle.filter.BaseVehicleCriteria;
import org.mitchell.vehicle.filter.MakeCriteria;
import org.mitchell.vehicle.model.Vehicle;
import org.mitchell.vehicle.validators.BaseVehicleValidator;
import org.mitchell.vehicle.validators.MakeValidator;
import org.mitchell.vehicle.validators.ModelValidator;
import org.mitchell.vehicle.validators.YearValidator;

public class TestCRUDVehicleController {

	private static CRUDVehicleController controller = null;
	
	@BeforeClass
	public static void setUp(){
		List<BaseVehicleCriteria> criteria = new ArrayList<BaseVehicleCriteria>();
		criteria.add(new MakeCriteria());
		
		List<BaseVehicleValidator> validators = new ArrayList<BaseVehicleValidator>();
		validators.add(new MakeValidator());
		validators.add(new YearValidator());
		validators.add(new ModelValidator());
		
		controller = new CRUDVehicleController(criteria, validators);
		controller.create(1,2007,"Nissan","Altima");
		controller.create(2,2006,"Ford","Mustang");
		controller.create(3, 1998, "Chevrolet", "Street Rod");
		controller.create(4,2007, "Mercedes Benz", "E356");
		controller.create(5,2007,"Mercedes","Altima");
		controller.create(6,2017,"Toyota","KUV");
		controller.create(7,2047,"Toyota","Innova");
		
	}
	
	@Test
	public void testGET(){
		List<Vehicle> vehicles = controller.get();
		assertEquals(7, vehicles.size());
	}
	
	@Test
	public void testGetById(){
		Vehicle vehicle = controller.getById(5);
		assertEquals(2007, vehicle.getYear());
		assertEquals("Mercedes", vehicle.getMake());
		assertEquals("Altima", vehicle.getModel());
	}
	
	@Test
	public void testUpdate(){
		boolean update = controller.update(3, 1998, "Chevrolet", "SUV");
		assertTrue(update);
		Vehicle vehicle = controller.getById(3);
		assertEquals("SUV", vehicle.getModel());
	}
	
	@Test
	public void testDelete(){
		boolean delete = controller.delete(7);
		assertTrue(delete);
		Vehicle vehicle = controller.getById(7);
		assertNull(vehicle);
	}
	
	@Test
	public void testCriteria(){
		List<Vehicle> vehicles = controller.getByCriteria();
		assertEquals(2, vehicles.size());
	}
	
	@Test
	public void testValidation(){
		assertFalse(controller.create(10,2500,"Nissan","Altima"));
		assertFalse(controller.create(10,2500,"","Altima"));
		assertFalse(controller.create(10,2500,"Nissan",""));
	}
}
