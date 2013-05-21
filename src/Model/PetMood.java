package Model;

import java.io.Serializable;

public class PetMood implements Serializable {

	private static final long serialVersionUID = 1L;
	private static int foodMood;
	private static int playMood;
	private static int walkMood;
	private static long timeInterval = 30;
	
	/**
	 * Sets the mood of the pet depending on how much it has eaten.
	 * 
	 * @param foodnumber - int
	 */
	public static void setFoodMood (int foodnumber){
		foodMood = foodnumber;
	}

	/**
	 * Sets the mood of the pet depending on how much it has played.
	 * 
	 * @param playnumber - int
	 */
	public static void setPlayMood (int playnumber){
		playMood = playnumber;
	}

	/**
	 * Sets the mood of the pet depending on how much it has walked.
	 * 
	 * @param playnumber - int
	 */
	public static void setWalkMood(int walknumber){
		walkMood = walknumber;
	}
	
	/**
	 * Makes it possible for the pet to know how much the pet has already eaten.
	 * 
	 * @return foodMood - int
	 */
	public static int getFoodMood (){
		return foodMood;
	}

	/**
	 * Makes it possible for the pet to know how much the pet has already played.
	 * 
	 * @return playMood - int
	 */
	public static int getPlayMood (){
		return playMood;
	}

	/**
	 * Makes it possible for the pet to know how much the pet has already walked.
	 * 
	 * @return foodMood - int
	 */
	public static int getWalkMood (){
		return walkMood;
	}

	/**
	 * The sum of the mood of the pet that is shown in a moodbar.
	 * Later, add:
	 * return foodMood + walkMood + playMood + sleepMood
	 * Each an int with value between 0 - 5
	 * 
	 * @return the total mood of the pet
	 */
	public static int getSumMood (){
		return foodMood + playMood + walkMood;
	}
	
	/**
	 * Method for getting current unix time
	 *
	 * @return time
	 */
	public static long getCurrentTime (){
		return System.currentTimeMillis()/3600000L;
	}

	/**
	 * Method that calculates how much the bar will decrease
	 *
	 * long current = getCurrentTime();
	 * long previous = Saved time from last game for eat, play, walk and sleep.
	 * moodBarDecrease (previous, current);
	 *
	 * @param previousTime
	 * @param currentTime
	 * @return number to decrease the bar with
	 */
	public int moodBarDecrease (long previousTime, long currentTime){
		long difference = currentTime - previousTime;
		if (difference > timeInterval*5){
			return -5;
		}
		else if (difference > timeInterval*4){
			return -4;
		}
		else if (difference > timeInterval*3){
			return -3;
		}
		else if (difference > timeInterval*2){
			return -2;
		}
		else if (difference > timeInterval){
			return -1;
		}
		else{
			return 0;
		}
	}
}
	

