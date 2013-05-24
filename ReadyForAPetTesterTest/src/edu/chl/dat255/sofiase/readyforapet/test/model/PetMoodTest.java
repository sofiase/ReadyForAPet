package edu.chl.dat255.sofiase.readyforapet.test.model;

import junit.framework.TestCase;
import edu.chl.dat255.sofiase.readyforapet.model.*;

public class PetMoodTest extends TestCase {
PetMood petmood = new PetMood();
 
 protected void setUp() throws Exception {
		super.setUp();
		PetMood.setFoodMood(2);
		PetMood.setWalkMood(3);
		PetMood.setPlayMood(0);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

/**
 * Method for testing the method getfoodMood in PetMood
 */
public void testInitialFoodMood(){
	assertEquals(2, PetMood.getFoodMood());
	}
/**
 * Method for testing the method getPlayMood in PetMood
 */
public void testInitialPlayMood(){
	assertEquals(0, PetMood.getPlayMood());
	}
/**
 * Method for testing the method getWalkMood in PetMood
 */
public void testInitialWalkMood(){
	assertEquals(3, PetMood.getWalkMood());
	}
/**
 * Method for testing the method getSumMood in PetMood
 */
public void testInitialSumMood(){
	assertEquals(5, PetMood.getSumMood());
	}
}


