package edu.chl.dat255.sofiase.readyforapet;

public class PetMood {
	
	private static int foodMood = 3;
	private static int totalMood;
	
	public static void PetMood(){
		
		// I PetActivity skapas en pinne av formatet 
		// PetMood moodpinne = new PetMood();
		
		
		
		
	}

	// Sets the mood of the pet depending on how much it has eaten.
	public void setFoodMood (int foodnumber){
		foodMood = foodnumber;
	}
	
	// Makes it possible for the pet to know how much the pet has already eaten.
	public int getFoodMood (){
		return foodMood;
	}
	
	// The sum of the mood of the pet that is shown in a moodbar.
	public int getSumMood (){
		return totalMood = foodMood;
	}
	
}
