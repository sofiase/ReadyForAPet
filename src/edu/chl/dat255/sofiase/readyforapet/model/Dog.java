package edu.chl.dat255.sofiase.readyforapet.model;

import java.io.Serializable;


public class Dog extends Pet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Dog(String name, int hungerCounter, int walkCounter, int playCounter) {
		super(name, hungerCounter, walkCounter, playCounter);
	}
	
	//Methods that applies only to dogs.

}