package Model;


public class PetMood {

	private static int foodMood = 2;
	private static int playMood = 2;

	public PetMood(){	


	}

	// Sets the mood of the pet depending on how much it has eaten.
	public void setFoodMood (int foodnumber){
		foodMood = foodnumber;
	}

	// Sets the mood of the pet depending on how much it has played.
	public void setPlayMood (int playnumber){
		playMood = playnumber;
	}

	// Makes it possible for the pet to know how much the pet has already eaten.
	public int getFoodMood (){
		return foodMood;
	}

	// Makes it possible for the pet to know how much it has already played.
	public int getPlayMood (){
		return playMood;
	}

	// The sum of the mood of the pet that is shown in a moodbar.
	//Later, add:
	//return foodMood + walkMood + playMood + sleepMood
	public int getSumMood (){
		return foodMood + playMood;
	}

}
