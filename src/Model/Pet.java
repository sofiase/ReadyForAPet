package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import edu.chl.dat255.sofiase.readyforapet.CreatePet;
import android.content.Context;


public class Pet implements Serializable{

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	PetMood petMood = new PetMood();
	


	/**
	 * Method that increases mood bar while eating
	 *
	 * @return String with the pet's reaction 
	 */
	public String eat() {
		int hungerCounter = petMood.getFoodMood();
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
	 * Method that increases mood bar while playing
	 *
	 * @return String with the pet's reaction 
	 */
	public String play() {
		int playCounter = petMood.getPlayMood();
		if (playCounter < 5) {
			playCounter = playCounter + 1;
			petMood.setPlayMood(playCounter);
			return "Yeey! I want to play";
		}	
		else{
			return "I'm tired! I want to rest";
		}
	}

	/*public void save(String FILENAME, Context context) throws FileNotFoundException, IOException{
		FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
		ObjectOutputStream savedPet = new ObjectOutputStream(fos);
		savedPet.writeObject(context.getApplicationContext());
		savedPet.close();
	}

	public static Pet load(String FILENAME, Context context) throws FileNotFoundException, IOException, ClassNotFoundException{
		FileInputStream fis = context.openFileInput(FILENAME);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Pet pet = (Pet) ois.readObject();
		ois.close();
		CreatePet.setPet(pet);
		return pet;
	} */
}

