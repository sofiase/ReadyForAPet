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




public class SelectGame extends Activity implements Serializable {
	private static final long serialVersionUID = 1L;
	

	TextView warningMessage;
	Button yes, no;
	
	Runnable makeTextGone = new Runnable(){

		
		/**
		 * run Method
		 * 
		 * TODO:add what the method does
		 */
		@Override
		public void run(){
			yes.setVisibility(View.GONE);
			no.setVisibility(View.GONE);
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
		createNewPet.setOnClickListener(new OnClickListener() {	
			/**
			 * Method onClick for the create new pet button
			 * 
			 * @param v - View
			 */
			public void onClick (View v){
				if (CreatePet.getPet() != null){
					
					warningMessage = (TextView) findViewById(R.id.warningmessage);
					warningMessage.setText("Are you sure you want to create a new pet and delete your old one?");
					
					Button yes = (Button) findViewById(R.id.yes);
					yes.setVisibility(View.VISIBLE);
					yes.setOnClickListener(new OnClickListener() {
				
						public void onClick (View v){
							startActivity(new Intent(SelectGame.this, CreatePet.class));
							//delete old pet from file here later
							}
						}
					);
					Button no = (Button) findViewById(R.id.no);
					no.setVisibility(View.VISIBLE);
					no.setOnClickListener(new OnClickListener() {
						
						public void onClick (View v){
							startActivity(new Intent(SelectGame.this, SelectGame.class));//kan man g�ra s�? man kan inte no yes och no annars utan att def knapparna igen?
						}
					}
					);
				}
				else{
					startActivity(new Intent(SelectGame.this,CreatePet.class));
				}
						
				
			}
		}
		);
	}
}
