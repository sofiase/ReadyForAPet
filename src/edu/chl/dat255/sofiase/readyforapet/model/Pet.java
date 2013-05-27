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

/**
 * Class Pet implements characteristics that are general to all pets.
 * Should be used as a superclass for other animals in further development.
 *
 */
public class Pet implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private long petBirthHour;
	private PetMood petMood;

	/*Test variables
	private static final String LOG_test = "save food";
	private static final String LOG_test1 = "save walk";
	private static final String LOG_test2 = "save play";
	private static final String LOG_test4 = "save sleep";
	private static final String LOG_test3 = "save birth";*/
	
	public Pet (String petName, int foodMood, int playMood, int walkMood, int sleepMood){
		this.name = petName;
		petMood = new PetMood(foodMood, playMood, walkMood, sleepMood, 2);
		this.petBirthHour = petMood.getCurrentHour();
		petMood.setLastEatHour(petMood.getCurrentHour());
		petMood.setLastPlayHour(petMood.getCurrentHour());
		petMood.setLastWalkHour(petMood.getCurrentHour());
		petMood.setLastSleepHour(petMood.getCurrentHour());
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
	 * Method that increases mood bar when the pet eats.
	 *
	 * @return String with the pet's reaction 
	 */
	public String eat() {
		if (petMood.getFoodMood() < 5){
			//Increase the foodmood
			petMood.setFoodMood(petMood.getFoodMood() + 1);
			//Save the last time the pet has eaten
			petMood.setLastEatHour(petMood.getCurrentHour());
			return "Yummie!";
		}	
		else{
			return "I'm full!";
		}
	}

	/**
	 * Method that increases mood bar while walking if it is not too hungry or too tired.
	 *
	 * @param distance - how long the pet has walked
	 * @return String with the pet's reaction 
	 */
	
	public String walk(int distance) {
		if (petMood.getFoodMood() < 3 && petMood.getWalkMood() < 5) {
			return "I'm too hungry!";
		}
		else if (petMood.getWalkMood() < 5) {
			if(distance < 10){
				return "I want to walk more!";
			}
			if(distance > 10 && distance < 30){
				//Increase the walkmood
				petMood.setWalkMood(petMood.getWalkMood() + 1);
				//Save the last time the pet has walked
				petMood.setLastWalkHour(petMood.getCurrentHour());
			}
			else if (distance < 50){
				//Increase the walkmood
				petMood.setWalkMood(petMood.getWalkMood() + 2);
				//Save the last time the pet has walked
				petMood.setLastWalkHour(petMood.getCurrentHour());
			}
			else if (distance < 70){
				//Increase the walkmood
				petMood.setWalkMood(petMood.getWalkMood() + 3);
				//Save the last time the pet has walked
				petMood.setLastWalkHour(petMood.getCurrentHour());
			}
			else if (distance < 90){
				//Increase the walkmood
				petMood.setWalkMood(petMood.getWalkMood() + 4);
				//Save the last time the pet has walked
				petMood.setLastWalkHour(petMood.getCurrentHour());
			}
			else{
				//Increase the walkmood
				petMood.setWalkMood(petMood.getWalkMood() + 5);
				//Save the last time the pet has walked
				petMood.setLastWalkHour(petMood.getCurrentHour());
			}
			if (petMood.getWalkMood() > 5){
				petMood.setWalkMood(5);
			}
			return "Yeey! Great exercise!";
		}	
		else{
			return "I'm tired! I want to rest!";
		}
	}

	/**
	 * Method that increases mood bar while playing if it is not too hungry or too tired.
	 *
	 * @return String with the pet's reaction 
	 */
	public String play() {
		if (petMood.getFoodMood() < 3 && petMood.getPlayMood() < 5){
			return "I'm too hungry!";
		}
		else if (petMood.getPlayMood() < 5) {
			//Increase the playmood
			petMood.setPlayMood(petMood.getPlayMood() + 1);
			//Save the last time the pet has played
			petMood.setLastPlayHour(petMood.getCurrentHour());
			return "Yeey! Lots of fun!";
		}	
		else{
			return "I'm tired! I want to rest!";
		}
	}

	/**
	 * Method that increases moodbar depending on how long the pet has sleeped.
	 *
	 * @param hours - how long the pet has sleeped
	 * @return String with the pet's reaction 
	 */
	public String sleep(long hours) {
		if (petMood.getSleepMood() < 5){
			if (hours < 0.2){
				return "I want to sleep more!";
			}
			if (hours < 0.5){
				//Increase the sleepmood
				petMood.setSleepMood(petMood.getSleepMood() + 1);
				//Save the last time the pet has sleeped
				petMood.setLastSleepHour(petMood.getCurrentHour());
			}
			else if (hours > 1){
				//Increase the sleepmood
				petMood.setSleepMood(petMood.getSleepMood() + 3);
				//Save the last time the pet has sleeped
				petMood.setLastSleepHour(petMood.getCurrentHour());
			}
			else{
				//Increase the sleepmood
				petMood.setSleepMood(petMood.getSleepMood() + 5);
				//Save the last time the pet has sleeped
				petMood.setLastSleepHour(petMood.getCurrentHour());
			}
			if (petMood.getSleepMood() > 5){
				petMood.setSleepMood(5);
			}
			return "Good morning!";
		}
		else{
			return "I'm not sleepy!\nI want to do something else";
		}
	}


	/**
	 * Makes the hour of birth available to all classes.
	 * 
	 * @return petBirthHour
	 */
	public long getBirthHour(){
		return petBirthHour;
	}

	/**
	 * Makes the pet's mood available to all classes.
	 * 
	 * @return petMood
	 */
	public PetMood getPetMood(){
		return petMood;
	}

	/**
	 * Method that returns the pet's alive status.
	 * 
	 * @return true if pet is alive, false if it is dead
	 */
	public boolean isAlive(){
		//Test
		//Log.i(LOG_test, Long.toString(petMood.getCurrentHour()));
		//Log.i(LOG_test1, Long.toString(petMood.getLastEatHour()));
		//Log.i(LOG_test2, Long.toString(petMood.getLastWalkHour()));
		return !((petMood.getCurrentHour() - petMood.getLastEatHour() > 48) || (petMood.getCurrentHour() - petMood.getLastWalkHour() > 48));
	}


	/**
	 * Saves an instance of the class Pet to the internal memory.
	 * 
	 * @param FILENAME - the name of the saved file
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
	 * Loads the saved instance of the class Pet.
	 * 
	 * @param FILENAME - the name of the saved file
	 * @param context
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Pet load(String FILENAME, Context context) throws FileNotFoundException, IOException, ClassNotFoundException{
		FileInputStream fis = context.openFileInput(FILENAME);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Pet pet = (Pet) ois.readObject();
		ois.close();
		CreatePet.setPet(pet);

		/*Test
		Log.i(LOG_test, Long.toString(pet.getPetMood().getLastEatHour()));
		Log.i(LOG_test1, Long.toString(pet.getPetMood().getLastWalkHour()));
		Log.i(LOG_test2, Long.toString(pet.getPetMood().getLastPlayHour()));
		Log.i(LOG_test4, Long.toString(pet.getPetMood().getLastSleepHour()));
		Log.i(LOG_test3, Long.toString(pet.petBirthHour));*/
		return pet;
	}
}
