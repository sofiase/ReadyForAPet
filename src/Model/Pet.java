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
	private PetMood petMood = new PetMood();
	private int hungerCounter;
	private int walkCounter;
	private int playCounter;


	/**
	 * Method that increases mood bar while eating
	 *
	 * @return String with the pet's reaction 
	 */
	public String eat() {
		hungerCounter = petMood.getFoodMood();
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
	public String walk(int distance) {

		walkCounter = petMood.getWalkMood();
		hungerCounter = petMood.getFoodMood();
		playCounter = petMood.getPlayMood();

		if (hungerCounter < 3 && walkCounter < 5)
			return "I'm too hungry!";
		else if (walkCounter < 5) {
			if(distance < 50){
				return "I want to walk more!";
			}
			if(distance > 50 && distance < 100){
				walkCounter = walkCounter + 1;
				petMood.setWalkMood(walkCounter);
				return "Yeey! Great exercise!";
			}
			else if (distance < 200){
				walkCounter = walkCounter + 2; //Om den var 4 frŒn bšrjan blir det fšr mycket nu...
				petMood.setWalkMood(walkCounter);
				return "Yeey! Great exercise!";
			}
			else if (distance < 300){
				walkCounter = walkCounter + 3;
				petMood.setWalkMood(walkCounter);
				return "Yeey! Great exercise!";
			}
			else{
				walkCounter = walkCounter + 4;
				petMood.setWalkMood(walkCounter);
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
		walkCounter = petMood.getWalkMood();
		hungerCounter = petMood.getFoodMood();
		playCounter = petMood.getPlayMood();
		if (hungerCounter <3 && playCounter < 5)
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
	}

}


