package edu.chl.dat255.sofiase.readyforapet;

public class Dog implements Pet {

	String name;
	
	public Dog(String name){
		this.name = name; 
	}
	
	@Override
	public String eat(PetMood petMood) {
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
	
	@Override
	public String play(PetMood petMood) {
		int playCounter = petMood.getPlayMood();
		if(playCounter < 5){
			playCounter = playCounter + 1;
			petMood.setPlayMood(playCounter);
			return "Yeey! I want to play";
		}	
			else{
				return "I'm tired! I want to rest";
			}
		
		
	}
	
	
}
