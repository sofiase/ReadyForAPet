package Model;

import java.io.Serializable;


public class Dog extends Pet implements Serializable {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private String name;

	/**
	 * Constructor that sets the name of the dog.
	 *
	 * @param name - String
	 */
	public Dog(String name){
		this.name = name; 
	}


	/**
	 * Method that makes the dog name available to all classes.
	 * 
	 * @return name - String
	 */
	public String getName() {
		return name;
	}

	// ... and additional functions that only applies to dogs
	

}