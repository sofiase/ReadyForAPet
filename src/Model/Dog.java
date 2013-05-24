package Model;

import java.io.Serializable;




public class Dog extends Pet implements Serializable {

	public Dog(String name, int hungerCounter, int walkCounter, int playCounter, long petBirthTime) {
		super(name, hungerCounter, walkCounter, playCounter, petBirthTime);
	}

	private static final long serialVersionUID = 1L;
	//private String name;

	
	@Override
	public String getName() {
		return super.getName();
	}
	
	/**
	 * Method that makes the dog name available to all classes.
	 * 
	 * @return name - String
	 */
	//public void setName(String name) {
		//super.setName(name);
//	}	

}