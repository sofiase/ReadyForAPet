package edu.chl.dat255.sofiase.readyforapet.model;

import java.io.Serializable;

/**
 * Class PetMood keeps track of the Pet's mood.
 * Pet has an instance of PetMood.
 *
 */
public class PetMood implements Serializable { 

	private static final long serialVersionUID = 1L;
	private int foodMood;
	private int playMood;
	private int walkMood;
	private int sleepMood;
	private long lastEatHour;
	private long lastWalkHour;
	private long lastPlayHour;
	private long lastSleepHour;
	private long timeInterval;

	public PetMood(int foodMood, int playMood, int walkMood, int sleepMood, long timeInterval){
		this.foodMood = foodMood;
		this.playMood = playMood;
		this.walkMood = walkMood;
		this.sleepMood = sleepMood;
		this.timeInterval = timeInterval;
	}

	/**
	 * Sets the mood of the pet depending on how much it has eaten. Mood is always a positive number.
	 * 
	 * @param foodnumber - int
	 */
	public void setFoodMood (int foodNumber){
		if (foodNumber >= 0){
			foodMood = foodNumber;
		}
		else{
			foodMood = 0;
		}
	}

	/**
	 * Sets the mood of the pet depending on how much it has played. Mood is always a positive number.
	 * 
	 * @param playnumber - int
	 */
	public void setPlayMood (int playNumber){
		if (playNumber >= 0){
			playMood = playNumber;
		}
		else{
			playMood = 0;
		}
	}

	/**
	 * Sets the mood of the pet depending on how much it has walked. Mood is always a positive number.
	 * 
	 * @param walknumber - int
	 */
	public void setWalkMood(int walkNumber){
		if (walkNumber >= 0){
			walkMood = walkNumber;
		}
		else{
			walkMood = 0;
		}
	}
	
	/**
	 * Sets the mood of the pet depending on how much it has sleeped. Mood is always a positive number.
	 * 
	 * @param sleepnumber - int
	 */
	public void setSleepMood(int sleepNumber){
		if (sleepNumber >= 0){
			sleepMood = sleepNumber;
		}
		else{
			sleepMood = 0;
		}
	}

	/**
	 * Makes it possible for the pet to know how much it has already eaten.
	 * 
	 * @return foodMood - int
	 */
	public int getFoodMood (){
		return foodMood;
	}

	/**
	 * Makes it possible for the pet to know how much it has already played.
	 * 
	 * @return playMood - int
	 */
	public int getPlayMood (){
		return playMood;
	}

	/**
	 * Makes it possible for the pet to know how much it has already walked.
	 * 
	 * @return foodMood - int
	 */
	public int getWalkMood (){
		return walkMood;
	}
	
	/**
	 * Makes it possible for the pet to know how much it has already sleeped.
	 * 
	 * @return SleepMood - int
	 */
	public int getSleepMood (){
		return sleepMood;
	}

	/**
	 * The sum of the pet's mood.
	 * foodMood, playMood, walkMood, sleepMood each an int with value between 0 - 5
	 * 
	 * @return the total mood of the pet
	 */
	public int getSumMood (){
		return foodMood + playMood + walkMood + sleepMood;
	}

	/**
	 * @param time - last time the pet has eaten
	 */
	public void setLastEatHour(long time){
		lastEatHour = time;
	}
	
	/**
	 * @param time - last time the pet has played
	 */
	public void setLastPlayHour(long time){
		lastPlayHour = time;
	}
	
	/**
	 * @param time - last time the pet has walked
	 */
	public void setLastWalkHour(long time){
		lastWalkHour = time;
	}
	
	/**
	 * @param time - last time the pet has slept
	 */
	public void setLastSleepHour(long time){
		lastSleepHour = time;
	}

	/**
	 * @return last time the pet has eaten
	 */
	public long getLastEatHour(){
		return lastEatHour;
	}
	
	/**
	 * @return last time the pet has eaten
	 */
	public long getLastPlayHour(){
		return lastPlayHour;
	}
	
	/**
	 * @return last time the pet has walked
	 */
	public long getLastWalkHour(){
		return lastWalkHour;
	}
	
	/**
	 * @return last time the pet has slept
	 */
	public long getLastSleepHour(){
		return lastSleepHour;
	}

	/**
	 * Method for getting current unix time.
	 *
	 * @return time expressed in hours
	 */
	public long getCurrentHour(){
		return System.currentTimeMillis()/3600000L;
	}

	/**
	 * Method that calculates how much the moodBar will decrease with time.
	 *
	 * @param previousTime - last time pet has eaten/played/walked/slept
	 * @param currentTime - the current time when method is called
	 * @return int number to decrease the moodBar with
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

