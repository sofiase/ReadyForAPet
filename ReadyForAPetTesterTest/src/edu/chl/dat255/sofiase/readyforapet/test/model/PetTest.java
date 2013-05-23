package edu.chl.dat255.sofiase.readyforapet.test.model;


import Model.Pet;
import junit.framework.TestCase;



public class PetTest extends TestCase {
	Pet pet = new Pet("olle",2,2,2);
	

	protected void setUp() throws Exception{
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * testMethodgetName Method 
	 *  	
	 *  Test for the method getName in the class Dog 
	 */
	public void testMethodgetName () {
		assertEquals("olle",pet.getName());
	}
}
