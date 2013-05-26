package edu.chl.dat255.sofiase.readyforapet.test.model;

import junit.framework.TestCase;
import edu.chl.dat255.sofiase.readyforapet.model.*;

public class PetMoodTest extends TestCase {
	PetMood petMood = new PetMood(2,3,0,30);
 
 protected void setUp() throws Exception {
		super.setUp();
		petMood.setFoodMood(2);
		petMood.setWalkMood(3);
		petMood.setPlayMood(0);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

/**
 * Method for testing the method getfoodMood in PetMood
 */
public void testFoodMood(){
	assertEquals(2, petMood.getFoodMood());
	}
/**
 * Method for testing the method getPlayMood in PetMood
 */
public void testPlayMood(){
	assertEquals(0, petMood.getPlayMood());
	}
/**
 * Method for testing the method getWalkMood in PetMood
 */
public void testWalkMood(){
	assertEquals(3, petMood.getWalkMood());
	}
/**
 * Method for testing the method getSumMood in PetMood
 */
public void testSumMood(){
	assertEquals(5, petMood.getSumMood());
	}
}


