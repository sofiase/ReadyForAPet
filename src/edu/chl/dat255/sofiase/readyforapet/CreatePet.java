package edu.chl.dat255.sofiase.readyforapet;

import Model.Dog;
import Model.Pet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;

public class CreatePet extends Activity {
	
	String name; 
	private static Dog dog; 

	/**
	 * onCreate Method
	 * 
	 * eftersom att hunden skapas här och vi inte har SPARAT spelet så fungerar inte eat() när man går via Continue game vi måste lösa så att spelet sparas!
	 *
	 * @param savedInstanceState - Bundle
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.createpet);
		dog = new Dog(name);
	}

	/**
	 * saveSettings Method
	 *
	 * @param v - View
	 */
	public void saveSettings(View v){
		Intent intent = new Intent(CreatePet.this, PetActivity.class);
		EditText setName = (EditText) findViewById(R.id.edit_pet_name);
		name = setName.getText().toString();
		startActivity(intent);
	}
	

	/**
	 * getPet Method
	 * 
	 * makes the created pet avaliable to other classes
	 *
	 * @return dog - an instance of the class Dog
	 */
	public static Pet getPet(){
		return dog;
	}

}
