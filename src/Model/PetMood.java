package Model;

import java.io.Serializable;

public class PetMood implements Serializable {

	private static final long serialVersionUID = 1L;
	private static int foodMood;
	private static int playMood;
	private static int walkMood;
	private static long lastEatHour;
	private static long lastWalkHour;
	private static long lastPlayHour;
	private static long timeInterval = 2;


	public PetMood(){
	}

	/**
	 * Sets the mood of the pet depending on how much it has eaten.
	 * 
	 * @param foodnumber - int
	 */
	public static void setFoodMood (int foodnumber){
		if (foodnumber >= 0){
			foodMood = foodnumber;
		}
		else{
			foodMood = 0;
		}
	}

	/**
	 * Sets the mood of the pet depending on how much it has played.
	 * 
	 * @param playnumber - int
	 */
	public static void setPlayMood (int playnumber){
		if (playnumber >= 0){
			playMood = playnumber;
		}
		else{
			playMood = 0;
		}
	}

	/**
	 * Sets the mood of the pet depending on how much it has walked.
	 * 
	 * @param playnumber - int
	 */
	public static void setWalkMood(int walknumber){
		if (walknumber >= 0){
			walkMood = walknumber;
		}
		else{
			walkMood = 0;
		}
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
	 * 
	 * @param time - last time the dog has eaten
	 */
	public static void setLastEatHour(long time){
		lastEatHour = time;
	}
	
	/**
	 * 
	 * @param time - last time the dog has eaten
	 */
	public static void setLastPlayHour(long time){
		lastPlayHour = time;
	}
	
	/**
	 * 
	 * @param time - last time the dog has eaten
	 */
	public static void setLastWalkHour(long time){
		lastWalkHour = time;
	}

	/**
	 * 
	 * @return last time the dog has eaten
	 */
	public static long getLastEatHour(){
		return lastEatHour;
	}
	
	/**
	 * 
	 * @return last time the dog has eaten
	 */
	public static long getLastPlayHour(){
		return lastPlayHour;
	}
	
	/**
	 * 
	 * @return last time the dog has eaten
	 */
	public static long getLastWalkHour(){
		return lastWalkHour;
	}

	/**
	 * Method for getting current unix time
	 *
	 * @return time in hour
	 */
	public static long getCurrentHour(){
		return System.currentTimeMillis()/3600000L;
	}

	/**
	 * Method that calculates how much the bar will decrease
	 *
	 * @param previousTime
	 * @param currentTime
	 * @return number to decrease the bar with
	 */
	public static int moodBarDecrease (long previousTime, long currentTime){
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

