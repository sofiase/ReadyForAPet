package edu.chl.dat255.sofiase.readyforapet.test.model;

import edu.chl.dat255.sofiase.readyforapet.model.*;
import junit.framework.TestCase;



public class PetTest extends TestCase {
	Pet pet = new Pet("olle",2,2,2,0);
	

	protected void setUp() throws Exception{
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * test for getName Method 
	 *  	
	 *  Test for the method getName in the class Pet
	 *  
	 */
	public void testMethodgetName () {
		assertEquals("olle",pet.getName());
	}
}
