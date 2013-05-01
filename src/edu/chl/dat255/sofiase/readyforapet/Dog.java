package edu.chl.dat255.sofiase.readyforapet;

public class Dog implements Pet {

	String name;
	
	public Dog(String name){
		this.name = name;
	}
	
	@Override
	public String eat() {
		int hungerCounter = petMood.getFoodMood();
		if(hungerCounter < 5){
			hungerCounter = hungerCounter + 1;
			petMood.setFoodMood(hungerCounter);
			return "Yummie!";
		}	
			else{
				return "I am full";
			}
		
		
	}
	
	
}
