package Model;


public class PetMood {

	private static int foodMood = 2;
	private static int playMood = 2;
	private static int walkMood = 2;

	public PetMood(){
	}

	/**
	 * Sets the mood of the pet depending on how much it has eaten.
	 * 
	 * @param foodnumber - int
	 */
	public void setFoodMood (int foodnumber){
		foodMood = foodnumber;
	}

	/**
	 * Sets the mood of the pet depending on how much it has played.
	 * 
	 * @param playnumber - int
	 */
	public void setPlayMood (int playnumber){
		playMood = playnumber;
	}
	
	/**
	 * Sets the mood of the pet depending on how much it has played.
	 * 
	 * @param playnumber - int
	 */
	public void setWalkMood(int walknumber){
		walkMood = walknumber;
	}

	/**
	 * Makes it possible for the pet to know how much the pet has already eaten.
	 * 
	 * @return foodMood - int
	 */
	public int getFoodMood (){
		return foodMood;
	}

	/**
	 * Makes it possible for the pet to know how much the pet has already played.
	 * 
	 * @return playMood - int
	 */
	public int getPlayMood (){
		return playMood;
	}
	
	/**
	 * Makes it possible for the pet to know how much the pet has already walked.
	 * 
	 * @return foodMood - int
	 */
	public int getWalkMood (){
		return walkMood;
	}

	/**
	 * The sum of the mood of the pet that is shown in a moodbar.
	 * Later, add:
	 * return foodMood + walkMood + playMood + sleepMood
	 * Each an int with value between 0-5
	 * 
	 * @return the total mood of the pet
	 */
	public int getSumMood (){
		return foodMood + playMood + walkMood;
	}

}
