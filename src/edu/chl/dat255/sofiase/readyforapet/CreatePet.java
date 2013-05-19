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
import android.widget.TextView;


public class CreatePet extends Activity implements OnClickListener, Serializable {


	private static final long serialVersionUID = 1L;
	String petName; 
	private static Dog dog;
	TextView chooseAnotherName;

	Runnable makeTextGone = new Runnable(){

		/**
		 * run method
		 * 
		 */
		@Override
		public void run(){
			chooseAnotherName.setVisibility(View.GONE);

		}
	};

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

		chooseAnotherName = (TextView) findViewById(R.id.chooseanothername);
		chooseAnotherName.setTextColor(-1);
		chooseAnotherName.setVisibility(View.GONE);
		Button create = (Button) findViewById(R.id.puppy_settings);
		create.setOnClickListener(this);


	}
	/**
	 * onClick Method
	 * 
	 * When pressing the button create it checks whether the user 
	 * has typed something in and that the name isn't null.
	 * It also calls the method save in the class Pet
	 * 
	 */
	public void onClick (View v){

		EditText setName = (EditText) findViewById(R.id.edit_pet_name);
		petName = setName.getText().toString();
		if (petName.equalsIgnoreCase("null")){
			chooseAnotherName.setText("Choose another name!");	
			chooseAnotherName.setVisibility(View.VISIBLE);
		}
		else if(petName.equals("")){
			chooseAnotherName.setText("Give your pet a name!");	
			chooseAnotherName.setVisibility(View.VISIBLE);
		}	
		else {
			dog = new Dog(petName);
			startActivity(new Intent(CreatePet.this, PetActivity.class));
		



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
	 * setPet Method
	 * 
	 * sets the pet to a pet
	 */
	public static void setPet(Pet pet){
		dog = (Dog) pet;
	}

}

