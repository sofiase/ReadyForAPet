package edu.chl.dat255.sofiase.readyforapet.model;

import java.io.Serializable;

/**
 * Class Dog is a subclass to class Pet and should implement properties that only applies to dogs.
 * General animal characteristics are specified in class Pet.
 * 
 */
public class Dog extends Pet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Dog(String petName, int foodMood, int playMood, int walkMood, int sleepMood) {
		super(petName, foodMood, playMood, walkMood, sleepMood);
	}
	
	//Methods that applies only to dogs.

}