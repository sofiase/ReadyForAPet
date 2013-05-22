package Model;

import java.io.Serializable;




public class Dog extends Pet implements Serializable {

	public Dog(String name, int hungerCounter, int walkCounter, int playCounter) {
		super(name, hungerCounter, walkCounter, playCounter);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	private String name;

	/**
	 * Method that makes the dog name available to all classes.
	 * 
	 * @return name - String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Method that makes the dog name available to all classes.
	 * 
	 * @return name - String
	 */
	public void setName(String name) {
		this.name = name;
	}	

}