package edu.chl.dat255.sofiase.readyforapet.model;

import java.io.Serializable;




public class Dog extends Pet implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	
	public Dog(String name, int hungerCounter, int walkCounter, int playCounter, long petBirthTime) {
		super(name, hungerCounter, walkCounter, playCounter, petBirthTime);
	}


}