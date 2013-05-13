package edu.chl.dat255.sofiase.readyforapet;


import Model.Dog;
import Model.Pet;
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

public class PetActivity extends Activity {

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

		// Get the pet name from the intent 
		//Intent nameintent = getIntent();
		//String petname = nameintent.getStringExtra(dog.getName());

		String petName = dog.getName();
		petgreeting = (TextView) findViewById(R.id.petgreeting);

		if(petName != null){
			petgreeting.setText("Hello, my name is " + petName + "!");		
		}

		else{
			petgreeting.setText("Hi buddy, I've missed you!");	
		}


		//Även detta togs bort när jag bytte textruta
		//Set the pet greeting as the activity layout
		//setContentView(petgreeting);


		petgreeting = (TextView) findViewById(R.id.petgreeting);
		uiHandler.postDelayed(makeTextGone, 5000);	


		//Food
		// Making the eat button
		Button eat = (Button) findViewById(R.id.eat);
		eat.setOnClickListener(new OnClickListener() {

			//making the dog feel less hungry if it is hungry and else give the message i'm full for 5 sek
			@Override
			public void onClick (View v){
				//PetMood petMoodInActivity = new PetMood();
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
		//Play
		// Making the play button
		Button play = (Button) findViewById(R.id.play);
		play.setOnClickListener(new OnClickListener() {


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







