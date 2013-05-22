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

	private static final long serialVersionUID = 1L;
	private int hungerCounter;
	private int walkCounter;
	private int playCounter;
	private String name;

	public Pet (String petName, int hungerCounter, int walkCounter, int playCounter){
		this.name = petName;
		PetMood.setFoodMood(hungerCounter);
		PetMood.setWalkMood(walkCounter);
		PetMood.setPlayMood(playCounter);
	}

	/**
	 * 
	 * @return
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

			if(distance < 50){
				return "I want to walk more!";
			}
			if(distance > 50 && distance < 100){
				walkCounter = walkCounter + 1;
				PetMood.setWalkMood(walkCounter);
				return "Yeey! Great exercise!";
			}
			else if (distance < 200){
				walkCounter = walkCounter + 2; //Om den var 4 fr�n b�rjan blir det f�r mycket nu...
				PetMood.setWalkMood(walkCounter);
				return "Yeey! Great exercise!";
			}
			else if (distance < 300){
				walkCounter = walkCounter + 3;
				PetMood.setWalkMood(walkCounter);
				return "Yeey! Great exercise!";
			}
			else{
				walkCounter = walkCounter + 4;
				PetMood.setWalkMood(walkCounter);
				return "Yeey! Great exercise!";
			}
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
			return "toohungry";
		else if (playCounter < 5 ) {
			playCounter = playCounter + 1;
			PetMood.setPlayMood(playCounter);
			return "play";
		}	
		else{
			return "I'm tired! I want to rest!";
		}
	}

	
	/**
	 * Saves an instance of the class Pet and...
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
	 * Loads the saved instance of the class Pet and...
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
	}
}


