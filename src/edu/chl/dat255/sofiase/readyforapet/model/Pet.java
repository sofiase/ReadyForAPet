package edu.chl.dat255.sofiase.readyforapet.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import edu.chl.dat255.sofiase.readyforapet.viewcontroller.CreatePet;
import android.content.Context;
import android.util.Log;

public class Pet implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private int hungerCounter;
	private int walkCounter;
	private int playCounter;
	private long petBirthHour;
	private long lastEatHour;
	private long lastWalkHour;
	private long lastPlayHour;
	
	private static final String LOG_test = "last food";
	private static final String LOG_test1 = "last eathour";
	private static final String LOG_test2 = "last play";

	public Pet (String petName, int hungerCounter, int walkCounter, int playCounter, long petBirthHour){
		this.name = petName;
		PetMood.setFoodMood(hungerCounter);
		PetMood.setWalkMood(walkCounter);
		PetMood.setPlayMood(playCounter);
		this.petBirthHour = petBirthHour;
		this.lastEatHour = petBirthHour;
		this.lastWalkHour = petBirthHour;
		this.lastPlayHour = petBirthHour;
		PetMood.setLastEatHour(petBirthHour);
		PetMood.setLastWalkHour(petBirthHour);
		PetMood.setLastPlayHour(petBirthHour);
	}

	/**
	 * Method that makes the dog name available to all classes.
	 * 
	 * @return name - String
	 */
	public String getName(){
		return name;
	}

	/**
	 * Method that increases mood bar while eating
	 *
	 * @return String with the pet's reaction 
	 */

	public String eat() {
		hungerCounter = PetMood.getFoodMood();
		if (hungerCounter < 5) {
			hungerCounter = hungerCounter + 1;
			PetMood.setFoodMood(hungerCounter);
			//Save the last time the pet has eaten
			lastEatHour = PetMood.getCurrentHour();
			return "eat";
		}	
		else{
			return "full";
		}
	}


	/**
	 * Method that increases mood bar while walking
	 * and decides that the dog can't walk when it is too hungry or too tired
	 *
	 * @return String with the pet's reaction 
	 */

	public String walk(int distance) {

		walkCounter = PetMood.getWalkMood();
		hungerCounter = PetMood.getFoodMood();
		playCounter = PetMood.getPlayMood();

		if (hungerCounter < 3 && walkCounter < 5)
			return "I'm too hungry!";
		else if (walkCounter < 5) {
			if(distance < 5){
				return "I want to walk more!";
			}
			if(distance > 5 && distance < 10){
				walkCounter = walkCounter + 1;
				//Save the last time the pet has walked
				lastWalkHour = PetMood.getCurrentHour();
			}
			else if (distance < 20){
				walkCounter = walkCounter + 2;
				//Save the last time the pet has walked
				lastWalkHour = PetMood.getCurrentHour();
			}
			else if (distance < 30){
				walkCounter = walkCounter + 3;
				//Save the last time the pet has walked
				lastWalkHour = PetMood.getCurrentHour();
			}
			else{
				walkCounter = walkCounter + 4;
				//Save the last time the pet has walked
				lastWalkHour = PetMood.getCurrentHour();
			}
			PetMood.setWalkMood(walkCounter);
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
		walkCounter = PetMood.getWalkMood();
		hungerCounter = PetMood.getFoodMood();
		playCounter = PetMood.getPlayMood();
		if (hungerCounter < 3 && playCounter < 5)
			return "I'm too hungry!";
		else if (playCounter < 5) {
			playCounter = playCounter + 1;
			PetMood.setPlayMood(playCounter);
			//Save the last time the pet has played
			lastPlayHour = PetMood.getCurrentHour();
			return "Yeey! Lots of fun!";
		}	
		else{
			return "I'm tired! I want to rest!";
		}
	}

	public long getBirthHour(){
		return petBirthHour;
	}


	/**
	 * Saves an instance of the class Pet
	 * 
	 * @param FILENAME
	 * @param context
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void save(String FILENAME, Context context) throws FileNotFoundException, IOException{
		FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
		ObjectOutputStream savedPet = new ObjectOutputStream(fos);
		savedPet.writeObject(this);
		savedPet.close();
	}


	/**
	 * Loads the saved instance of the class Pet and sets the mood and the time
	 * 
	 * @param FILENAME
	 * @param context
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void load(String FILENAME, Context context) throws FileNotFoundException, IOException, ClassNotFoundException{
		FileInputStream fis = context.openFileInput(FILENAME);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Pet pet = (Pet) ois.readObject();
		ois.close();
		CreatePet.setPet(pet);
		PetMood.setFoodMood(pet.hungerCounter);
		PetMood.setWalkMood(pet.walkCounter);
		PetMood.setPlayMood(pet.playCounter);
		PetMood.setLastEatHour(pet.lastEatHour);
		PetMood.setLastWalkHour(pet.lastWalkHour);
		PetMood.setLastPlayHour(pet.lastPlayHour);
		
		//Test
		//Log.i(LOG_test, Long.toString(pet.lastEatHour));
		//Log.i(LOG_test1, Long.toString(pet.lastWalkHour));
		//Log.i(LOG_test2, Long.toString(pet.lastPlayHour));
	}
}


