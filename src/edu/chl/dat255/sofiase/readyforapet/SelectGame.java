package edu.chl.dat255.sofiase.readyforapet;

import Model.Dog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class SelectGame extends Activity {
	private TextView failmessage;

	/**
	 * onCreate method
	 * 
	 * @param savedInstanceState - Bundle
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.selectgame);

		//The continue button reacts to a click and starts PetActivity
		Button continuePreviousGame = (Button) findViewById(R.id.continuegame);
		continuePreviousGame.setOnClickListener(new OnClickListener() {

			/**
			 * Method onClick for the continue previous game button
			 * 
			 * @param v - View
			 */
			public void onClick (View v){
				if (CreatePet.getPet() != null){ 
					startActivity(new Intent(SelectGame.this, PetActivity.class));
				}
				else{
					failmessage = (TextView) findViewById(R.id.failmessage);
					failmessage.setText("Create a pet first!");
				}
					
			}

		}
		);
		

		//To send the button CreateNewPet to the activity CreatePet
		Button createNewPet = (Button) findViewById(R.id.createnewpet);
		createNewPet.setOnClickListener(new OnClickListener() {
			
			/**
			 * Method onClick for the create new pet button
			 * 
			 * @param v - View
			 */
			public void onClick (View v){
				startActivity(new Intent(SelectGame.this, CreatePet.class));
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
		
	}
