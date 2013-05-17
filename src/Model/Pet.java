package Model;

import java.text.DateFormat.Field;
import java.util.Calendar;

import android.content.Context;

public class Pet {

	PetMood petMood = new PetMood();
	private int hungerCounter;
	private int walkCounter;
	private int playCounter;


	/**
	 * Method that increases mood bar while eating
	 *
	 * @return String with the pet's reaction 
	 */
	public String eat() {
		//walkCounter = petMood.getWalkMood();
		hungerCounter = petMood.getFoodMood();
		//playCounter = petMood.getPlayMood();
		if (hungerCounter < 5) {
			hungerCounter = hungerCounter + 1;
			petMood.setFoodMood(hungerCounter);
			return "Yummie!";
		}	

		else{
			return "I am full";
		}
	}

	/**
	 * Method that increases mood bar while walking
	 * and decides that the dog can't walk when it is too hungry or too tired
	 *
	 * @return String with the pet's reaction 
	 */
	public String walk() {
		walkCounter = petMood.getWalkMood();
		hungerCounter = petMood.getFoodMood();
		playCounter = petMood.getPlayMood();
		if (hungerCounter < 3 && walkCounter < 5)
			return "I'm too hungry!";
		else if (playCounter + walkCounter > 6)
			return "I'm tired! I want to rest!";
		else if (walkCounter < 5) {
			walkCounter = walkCounter + 1;
			petMood.setWalkMood(walkCounter);
			return "Yeey! Great exercise!";
		}	
		else{
			return "I'm tired! I want to rest!";
		}
	}

	/**
	 * Method that increases mood bar while playing
	 * and decides that the dog can't play when it is too hungry or too tired
	 *
	 * @return String with the pet's reaction 
	 */
	public String play() { 
		walkCounter = petMood.getWalkMood();
		hungerCounter = petMood.getFoodMood();
		playCounter = petMood.getPlayMood();
		if (playCounter + walkCounter > 6) {
			return "I'm tired! I want to rest!";
		}
		else if (hungerCounter <3 && playCounter < 5)
			return "I'm too hungry!";
		else if (playCounter < 5 ) {
			playCounter = playCounter + 1;
			petMood.setPlayMood(playCounter);
			return "Yeey! Lots of fun!";
		}	
		else{
			return "I'm tired! I want to rest!";
		}
	}
	
	
	
}

