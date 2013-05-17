package edu.chl.dat255.sofiase.readyforapet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import Model.Dog;
import Model.Pet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;

public class CreatePet extends Activity implements OnClickListener, Serializable {


	private static final long serialVersionUID = 1L;
	String petName; 
	private static Dog dog;

	/**
	 * onCreate Method
	 * 
	 *
	 * @param savedInstanceState - Bundle
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.createpet);


		Button create = (Button) findViewById(R.id.puppy_settings);
		create.setOnClickListener(this);
	}

	public void onClick (View v){
		startActivity(new Intent(CreatePet.this, PetActivity.class));
		EditText setName = (EditText) findViewById(R.id.edit_pet_name);
		petName = setName.getText().toString();
		dog = new Dog(petName);

		try {
			dog.save("pet_file.dat", this);
		} catch (FileNotFoundException e) {
			System.out.print("File not found kastad i CreatePet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.print("IOException kastad i CreatePet");
			e.printStackTrace();
		}
	}

	/**
	 * getPet Method
	 * 
	 * makes the created pet available to other classes
	 *
	 * @return dog - an instance of the class Dog
	 */
	public static Pet getPet(){
		return dog;
	}
	
	/**
	 * getPet Method
	 * 
	 * makes the created pet available to other classes
	 *
	 * @return dog - an instance of the class Dog
	 */
	public static void setPet(Pet pet){
		dog = (Dog) pet;
	}

}

