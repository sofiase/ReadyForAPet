package Model;


public class Pet {
	
	PetMood petMood = new PetMood();
	
	public String eat() {
		int hungerCounter = petMood.getFoodMood();
		if (hungerCounter < 5) {
			hungerCounter = hungerCounter + 1;
			petMood.setFoodMood(hungerCounter);
			return "Yummie!";
		}	
			else{
				return "I am full";
			}
		
		
	}

	public String play() {
		int playCounter = petMood.getPlayMood();
		if (playCounter < 5) {
			playCounter = playCounter + 1;
			petMood.setPlayMood(playCounter);
			return "Yeey! I want to play";
		}	
			else{
				return "I'm tired! I want to rest";
			}
		
		
	}
	
}
