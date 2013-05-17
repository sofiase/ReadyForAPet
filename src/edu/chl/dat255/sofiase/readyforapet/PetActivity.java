package edu.chl.dat255.sofiase.readyforapet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import Model.Dog;
import Model.PetMood;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PetActivity extends Activity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TextView petgreeting, respondingOnEat, respondingOnPlay;
	Handler uiHandler = new Handler();

	private ProgressBar moodBar;

	private PetMood petMood = new PetMood();
	private Dog dog = (Dog) CreatePet.getPet();



	Runnable makeTextGone = new Runnable(){

		@Override
		public void run(){
			petgreeting.setVisibility(View.GONE);
		}
	};

	/**
	 * onCreate Method
	 *
	 * @param savedInstanceState - Bundle
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.petactivity);
		

		respondingOnEat = (TextView) findViewById(R.id.pet_response);
		respondingOnEat.setVisibility(View.GONE);

		String petName = dog.getName();
		

		petgreeting = (TextView) findViewById(R.id.petgreeting);

		if(petName != null){
			petgreeting.setText("Hello, my name is " + petName + "!");		
		}

		else{
			try{
				BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput("pet_name_file")));
				String earlierName; 
				StringBuffer stringBuffer = new StringBuffer();
				while((earlierName = inputReader.readLine()) != null){
					stringBuffer.append(earlierName + "\n");
				}
			   petgreeting.setText("Hi" + stringBuffer.toString() + ", I have missed you!");
			}
			catch(IOException e){
				e.printStackTrace();
			}
				
		}

		petgreeting = (TextView) findViewById(R.id.petgreeting);
		uiHandler.postDelayed(makeTextGone, 5000);	


		// Making the eat button
		Button eat = (Button) findViewById(R.id.eat);
		eat.setOnClickListener(new OnClickListener() {

			/**
			 * Making the dog feel less hungry if it is hungry and else give the message i'm full
			 *
			 * @param v - View
			 */
			@Override
			public void onClick (View v){
				respondingOnEat = (TextView) findViewById(R.id.pet_response);

				respondingOnEat.setText(dog.eat());
				respondingOnEat.setVisibility(View.VISIBLE);
				//uiHandler.postDelayed(makeTextGone, 5000);	

				// Updating the moodbar
				moodBar = (ProgressBar) findViewById(R.id.moodbar);
				moodBar.setProgress(petMood.getSumMood());
			}
		}
				);


		// Making the play button
		Button play = (Button) findViewById(R.id.play);
		play.setOnClickListener(new OnClickListener() {



			/**
			 * Making the dog feel happier when it plays
			 *
			 * @param v - View
			 */
			@Override
			public void onClick (View v){

				respondingOnPlay = (TextView) findViewById(R.id.pet_response);
				respondingOnPlay.setText(dog.play());
				respondingOnPlay.setVisibility(View.VISIBLE);
				//uiHandler.postDelayed(makeTextGone, 5000);

				// Updating the moodbar
				moodBar = (ProgressBar) findViewById(R.id.moodbar);
				moodBar.setProgress(petMood.getSumMood());
			}
		}
				);			

	}

	/**
	 * Making the dog feel less hungry if it is hungry and else give the message i'm full
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







