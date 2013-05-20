package edu.chl.dat255.sofiase.readyforapet;

import Model.Dog;
import Model.Pet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;

public class CreatePet extends Activity {

	private String petName;
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
		create.setOnClickListener(new OnClickListener() {

			public void onClick (View v){
				startActivity(new Intent(CreatePet.this, PetActivity.class));
				EditText setName = (EditText) findViewById(R.id.edit_pet_name);
				petName = setName.getText().toString();
				dog = new Dog(petName);
			}
		});
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
	
	
	/**
	 * Configurates the navigate Up button in this activity
	 *
	 * @param item - MenuItem
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}

