package edu.chl.dat255.sofiase.readyforapet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import Model.Pet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class SelectGame extends Activity implements Serializable {// la till för att objektet måste vara serializible
   /**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	TextView failMessage;
 //  String FILENAME = "pet_file.dat";// lgts till för nullpointerexep
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
			/*	try {
					Pet.load("pet_file.dat",SelectGame.this);
					} catch (FileNotFoundException e) {
					System.out.print("File not found kastad i SelectGame");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.print("IO Exception kastad i SelectGame");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.out.print("Class not found exception kastad i SelectGame");
					e.printStackTrace();
				} */
				
				if (CreatePet.getPet() != null){
					startActivity(new Intent(SelectGame.this, PetActivity.class));		
				}
				
				else{
					failMessage = (TextView) findViewById(R.id.failMessage);
					failMessage.setText("Create a pet first!");
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
}
