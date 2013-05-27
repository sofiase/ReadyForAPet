package edu.chl.dat255.sofiase.readyforapet.viewcontroller;

import java.io.IOException;
import java.io.Serializable;
import edu.chl.dat255.sofiase.readyforapet.R;
import edu.chl.dat255.sofiase.readyforapet.model.Dog;
import edu.chl.dat255.sofiase.readyforapet.model.Pet;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Class CreatePet let's the user create a pet with a new name.
 *
 * Copyright (C) 2013 Katrin Miettinen, Linnea Pettersson, Sofia Selin, Johanna Ydergard
 * 
 * Licensed under the MIT license. This file must only be used in accordance with the license. 
 *
 */
public class CreatePet extends Activity implements OnClickListener,Serializable {  

	private static final long serialVersionUID = 1L;
	private String petName;

	private static Dog dog;
	private MediaPlayer player;
	private AssetFileDescriptor afd;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.createpet);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		Button create = (Button) findViewById(R.id.puppy_settings);
		create.setOnClickListener(this);
		//Dog barks when activity is started
		try {
			afd = getAssets().openFd("dogbark.m4a");
			player = new MediaPlayer();
			player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),afd.getLength());
			player.setLooping(true);
			player.prepare();
			player.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

		/**
		 * TimerTask timertask = new TimerTask() {
					@Override
					public void run() {
						player.paus();//eller stop?

					}

				};

				timer = new Timer();
				timer.schedule(timertask, 2000);

		 */
		
		
	}

	/**
	 * When pressing the button create it checks whether the user has typed a name, and then creates an instance of the Pet class.
	 * 
	 */
	public void onClick (View v){
		EditText setName = (EditText) findViewById(R.id.edit_pet_name);
		petName = setName.getText().toString();
		
		//Starts a dog barking sound when a new pet is created
		try {
		afd = getAssets().openFd("dogbarks.wav");
		player = new MediaPlayer();
		player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),afd.getLength());
		player.prepare();
		player.start();

		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//Makes sure the user has typed in a name
		if (petName.equals("")){
			Toast.makeText(this, "Give your pet a name!", Toast.LENGTH_SHORT).show();
		}
		
		else {
			//Creating a new dog with total initial startmood 8
			dog = new Dog(petName, 2, 2, 2, 2);
			startActivity(new Intent(CreatePet.this, PetActivity.class));
		}
	}

	/**
	 * Makes the created pet available to other classes.
	 *
	 * @return dog - an instance of the class Dog
	 */
	public static Pet getPet(){
		return dog;
	}
	
	public static void setPet(Pet pet){
		dog = (Dog) pet;
	}
	
	
	/**
	 * Configuring the navigate Up button in this activity.
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

