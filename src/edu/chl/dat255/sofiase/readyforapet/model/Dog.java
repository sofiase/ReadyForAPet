package edu.chl.dat255.sofiase.readyforapet.model;

import java.io.Serializable;


public class Dog extends Pet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Dog(String petName, int foodMood, int playMood, int walkMood, int sleepMood) {
		super(petName, foodMood, playMood, walkMood, sleepMood);
	}
	
	//Methods that applies only to dogs.

}