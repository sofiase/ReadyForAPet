package edu.chl.dat255.sofiase.readyforapet.test.model;

import junit.framework.TestCase;
import edu.chl.dat255.sofiase.readyforapet.model.*;

/**
 * Class for testing methods in class PetMood. 
 * 
 * Copyright (C) 2013 Katrin Miettinen, Linnea Pettersson, Sofia Selin, Johanna Ydergard
 * 
 * Licensed under the MIT license. This file must only be used in accordance with the license. 
 *
 */

public class PetMoodTest extends TestCase {
	PetMood petMood = new PetMood(2,3,0,1,30);
 
 protected void setUp() throws Exception {
		super.setUp();
		petMood.setFoodMood(2);
		petMood.setWalkMood(3);
		petMood.setPlayMood(0);
		petMood.setSleepMood(1);
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
 * Method for testing the method getSleepMood in PetMood
 */
public void testSleepMood(){
	assertEquals(1, petMood.getSleepMood());
	}

/**
 * Method for testing the method getSumMood in PetMood
 */
public void testSumMood(){
	assertEquals(6, petMood.getSumMood());
	}
}


