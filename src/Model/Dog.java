package Model;


public class Dog extends Pet {

	private String name;
	
	public Dog(String name){
		this.name = name; 
	}

	public String getName() {
		return name;
	}
	
	// ... and additional functions that only applies to dogs
	
}