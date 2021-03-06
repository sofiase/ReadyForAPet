package edu.chl.dat255.sofiase.readyforapet.viewcontroller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import edu.chl.dat255.sofiase.readyforapet.R;
import edu.chl.dat255.sofiase.readyforapet.model.Dog;
import edu.chl.dat255.sofiase.readyforapet.model.Pet;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Class SelectGame, makes it possible to continue playing with the current pet or create a new one.
 * Loads the pet from internal memory if an existing pet exists.
 * Prompts the user if it tries to create a new pet when it already has one.
 * 
 * Copyright (C) 2013 Katrin Miettinen, Linnea Pettersson, Sofia Selin, Johanna Ydergard
 * 
 * Licensed under the MIT license. This file must only be used in accordance with the license. 
 *
 */
public class SelectGameActivity extends Activity implements Serializable {

	private static final long serialVersionUID = 1L;
	private TextView warningMessage;
	private Dog dog;

	//Variable for tests
	private final String LOG_TAG1 = "Information about the file when loading";


	/**
	 * onCreate method
	 * 
	 * @param savedInstanceState - Bundle
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.selectgame);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		if (dog == null){
			try {
				dog = (Dog) Pet.load("pet_file.dat", SelectGameActivity.this);
			} catch (FileNotFoundException e) {
				System.out.print("File not found ");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.print("IO Exception ");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.print("Class not found exception ");
				e.printStackTrace();
			}

			//Test to see if the file exist on internal memory when loading
			File file = getBaseContext().getFileStreamPath("pet_file.dat");
			if(file.exists()){
				Log.i(LOG_TAG1,"is saved on internal memory");
			}
			else{
				Log.i(LOG_TAG1,"is not saved on internal memory");
			}
		}

		//Continue button starts petActivity if there is an existing pet
		Button continuePreviousGame = (Button) findViewById(R.id.continuegame);
		continuePreviousGame.setOnClickListener(new OnClickListener() {

			/**
			 * Method onClick for the continue previous game button
			 * 
			 * @param v - View
			 */
			public void onClick (View v){

				if(dog != null){
					startActivity(new Intent(SelectGameActivity.this, PetActivity.class));
				} 

				else {
					Toast.makeText(SelectGameActivity.this, "Create a pet first!", Toast.LENGTH_SHORT).show();
				}
			}
		});

		//What happens when button create new pet is pushed
		Button createNewPet = (Button) findViewById(R.id.createnewpet);
		createNewPet.setOnClickListener(new OnClickListener() 
		{
			/**
			 * Method onClick for the create new pet button
			 * 
			 * @param v - View
			 */
			public void onClick (View v){
				//Show a warning alert for creating a new pet if user already has a pet that is still alive.
				if (dog != null && CreatePetActivity.getPet().isAlive()){
					showWarningAlert();
				}
				else{	
					startActivity(new Intent(SelectGameActivity.this, CreatePetActivity.class));
				}
			}
		});
	}

	Runnable makeTextGone = new Runnable(){
		@Override
		public void run(){
			warningMessage.setVisibility(View.GONE);
		}
	};

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


	/**
	 * Shows a warning alert when user tries to create a new pet but already has one
	 * 
	 */
	private void showWarningAlert(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage("You already have a pet. Are you sure you want to replace it?")
		.setCancelable(false)
		.setPositiveButton("Yes",
				new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				startActivity(new Intent(SelectGameActivity.this, CreatePetActivity.class));
			}
		});
		alertDialogBuilder.setNegativeButton("No",
				new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.cancel();
			}
		});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
	}

	/**
	 * Loads the saved file from the internal memory if dog is not yet defined.
	 * 
	 */
	@Override
	protected void onResume() {
		super.onResume();
		if (dog == null){
			try {
				dog = (Dog) Pet.load("pet_file.dat", SelectGameActivity.this);
			} catch (FileNotFoundException e) {
				System.out.print("File not found ");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.print("IO Exception ");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.print("Class not found exception ");
				e.printStackTrace();
			} 
		}
	}

}
