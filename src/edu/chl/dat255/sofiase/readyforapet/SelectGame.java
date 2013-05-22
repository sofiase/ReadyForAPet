package edu.chl.dat255.sofiase.readyforapet;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import Model.Pet;
import Model.PetMood;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SelectGame extends Activity implements Serializable {

	private static final long serialVersionUID = 1L;
	protected static final String LOG_load = null;
	private TextView warningMessage;


	Runnable makeTextGone = new Runnable(){

		/**
		 * run Method
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

		Button yes = (Button) findViewById(R.id.yes);
		yes.setVisibility(View.GONE);

		Button no = (Button) findViewById(R.id.no);
		no.setVisibility(View.GONE);


		//The continue button reacts to a click and starts PetActivity
		Button continuePreviousGame = (Button) findViewById(R.id.continuegame);
		continuePreviousGame.setOnClickListener(new OnClickListener() {

			/**
			 * Method onClick for the continue previous game button
			 * 
			 * @param v - View
			 */
			public void onClick (View v){

				try {
					Pet.load("pet_file.dat", SelectGame.this); 
					Log.i(LOG_load,Integer.toString(PetMood.getFoodMood()));
					//PetMood.load("petmood_file.dat", SelectGame.this); // lagt till för load
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

				if (CreatePet.getPet() != null){
					startActivity(new Intent(SelectGame.this, PetActivity.class));		
				}

				else{
					warningMessage = (TextView) findViewById(R.id.warningmessage);
					warningMessage.setText("Create a pet first!");

				}
			}
		}

				);


		//What happens when you button create new pet is pushed
		Button createNewPet = (Button) findViewById(R.id.createnewpet);
		createNewPet.setOnClickListener(new OnClickListener() 
		{	
			/**
			 * Method onClick for the create new pet button
			 * 
			 * @param v - View
			 */
			public void onClick (View v){
				if (CreatePet.getPet() != null){
					showWarningAlert();
				
				}
				else{
					startActivity(new Intent(SelectGame.this,CreatePet.class));
				}


			}
		}
				);
				
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
		
	}
