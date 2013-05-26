package edu.chl.dat255.sofiase.readyforapet.model;

import java.io.Serializable;

public class PetMood implements Serializable { 

	private static final long serialVersionUID = 1L;
	private int foodMood;
	private int playMood;
	private int walkMood;
	private long lastEatHour;
	private long lastWalkHour;
	private long lastPlayHour;
	private long timeInterval;


	public PetMood(int foodMood, int playMood, int walkMood, long timeInterval){
		this.foodMood = foodMood;
		this.playMood = playMood;
		this.walkMood = walkMood;
		this.timeInterval = timeInterval;
	}

	/**
	 * Sets the mood of the pet depending on how much it has eaten.
	 * 
	 * @param foodnumber - int
	 */
	public void setFoodMood (int foodnumber){
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
	public void setPlayMood (int playnumber){
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
	public void setWalkMood(int walknumber){
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
	 * Each an int with value between 0 - 5
	 * 
	 * @return the total mood of the pet
	 */
	public int getSumMood (){
		return foodMood + playMood + walkMood;
	}

	/**
	 * 
	 * @param time - last time the dog has eaten
	 */
	public void setLastEatHour(long time){
		lastEatHour = time;
	}
	
	/**
	 * 
	 * @param time - last time the dog has eaten
	 */
	public void setLastPlayHour(long time){
		lastPlayHour = time;
	}
	
	/**
	 * 
	 * @param time - last time the dog has eaten
	 */
	public void setLastWalkHour(long time){
		lastWalkHour = time;
	}

	/**
	 * 
	 * @return last time the dog has eaten
	 */
	public long getLastEatHour(){
		return lastEatHour;
	}
	
	/**
	 * 
	 * @return last time the dog has eaten
	 */
	public long getLastPlayHour(){
		return lastPlayHour;
	}
	
	/**
	 * 
	 * @return last time the dog has eaten
	 */
	public long getLastWalkHour(){
		return lastWalkHour;
	}
	

	/**
	 * Method for getting current unix time
	 *
	 * @return time in hour
	 */
	public long getCurrentHour(){
		//return System.currentTimeMillis()/3600000L;
		return System.currentTimeMillis()/1000L;
	}

	/**
	 * Method that calculates how much the bar will decrease
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

