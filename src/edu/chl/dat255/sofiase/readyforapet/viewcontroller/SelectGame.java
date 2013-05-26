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
import android.content.Context;
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

public class SelectGame extends Activity implements Serializable {

	private static final long serialVersionUID = 1L;
	private TextView warningMessage;
	//private Pet pet;
	private Dog dog;
	
	//Variables for tests
	private final String LOG_TAG = "Information about the file when loading";
	Runnable makeTextGone = new Runnable(){

		/**
		 * 
		 * TODO:add what the method does
		 */
		@Override
		public void run(){
			warningMessage.setVisibility(View.GONE);
		}
	};

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

		try {
			//pet = Pet.load("pet_file.dat", SelectGame.this);
			dog = (Dog) Pet.load("pet_file.dat", SelectGame.this);
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
					startActivity(new Intent(SelectGame.this, PetActivity.class));
					
					//Test to see if the file exist on internal memory when loading
					File file = getBaseContext().getFileStreamPath("pet_file.dat");
					if(file.exists()){
						Log.i(LOG_TAG,"is saved on internal memory");
					}
					else{
						Log.i(LOG_TAG,"is not saved on internal memory");
					}  
				} 

				else {
					Toast.makeText(SelectGame.this, "Create a pet first!", Toast.LENGTH_SHORT).show();
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
				if (dog != null && dog.isAlive()){
					showWarningAlert();
				}
				else{	
					startActivity(new Intent(SelectGame.this,CreatePet.class));
				}
			}
		});
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
				startActivity(new Intent(SelectGame.this, CreatePet.class));
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

	
	    @Override
	    protected void onResume() {
	        super.onResume();
	        if (dog == null){
	    		try {
	    			dog = (Dog) Pet.load("pet_file.dat", SelectGame.this);
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
	        // The activity has become visible (it is now "resumed").
	    }
	    @Override
	    protected void onPause() {
	        super.onPause();
	        // Another activity is taking focus (this activity is about to be "paused").
	    }
	    @Override
	    protected void onStop() {
	        super.onStop();
	        // The activity is no longer visible (it is now "stopped")
	    }
	    @Override
	    protected void onDestroy() {
	        super.onDestroy();
	        // The activity is about to be destroyed.
	    }
}
