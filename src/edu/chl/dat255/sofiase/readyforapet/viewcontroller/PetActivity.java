package edu.chl.dat255.sofiase.readyforapet.viewcontroller;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;


import edu.chl.dat255.sofiase.readyforapet.R;
import edu.chl.dat255.sofiase.readyforapet.model.Pet;
import edu.chl.dat255.sofiase.readyforapet.model.PetMood;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PetActivity extends Activity implements Serializable{ 

	private static final long serialVersionUID = 1L;
	private TextView petResponse;
	private TextView showPetAge;
	private Handler uiHandler = new Handler();
	private ImageView dogBiscuit, dogPicture;
	private ProgressBar moodBar;
	private Pet dog;
	private String petName;
	private int petAge;
	private final String LOG_TAG1 = "Information about the file when saving";
	private final String LOG_TAG2 = "Information about the file when deleting";


	//Variables for playing music in Pet Activity
	private MediaPlayer player;
	private AssetFileDescriptor afd;

	Runnable makeTextGone = new Runnable(){

		/**
		 * run method
		 * 
		 */
		@Override
		public void run(){
			petResponse.setVisibility(View.GONE);
			dogBiscuit.setVisibility(View.GONE);

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
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		//Recieving the new or saved pet
		dog = CreatePet.getPet();
		//Getting the pet name
		petName = dog.getName();

		final Button play = (Button) findViewById(R.id.play);
		final Button walk = (Button) findViewById(R.id.walk);
		final Button eat = (Button) findViewById(R.id.eat);

		showPetAge = (TextView) findViewById(R.id.petage);
		petResponse = (TextView) findViewById(R.id.petresponse);
		dogBiscuit = (ImageView) findViewById(R.id.dogbiscuit);
		dogPicture = (ImageView) findViewById(R.id.dogpicture);
		dogBiscuit.setVisibility(View.GONE);
		dogPicture.setVisibility(View.VISIBLE);

		//Getting the age of the pet if it has not already died
		//petAge = (int) (PetMood.getCurrentHour() - dog.getBirthHour()) / 24;
		petAge = (int) (PetMood.getCurrentHour() - dog.getBirthHour());


		//Setting textview with welcome message
		petResponse.setText("Hello, my name is " + petName + "!");
		petResponse.setVisibility(View.VISIBLE);
		uiHandler.postDelayed(makeTextGone, 2000);	
		play.setEnabled(false);
		eat.setEnabled(false);
		walk.setEnabled(false);
		new Handler().postDelayed(new Runnable() { 
			@Override
			public void run() {
				eat.setEnabled(true);
				walk.setEnabled(true);
				play.setEnabled(true);
			}
		}, 2000);


		//Setting textview with current age of the pet
		showPetAge.setText(petName + " is " + petAge + " days old.");
		petResponse.setVisibility(View.VISIBLE);

		//Changing the picture and enabling/disabling buttons depending on mood
		changePicture(play, eat, walk);

		//Decreasing the FoodMood depending on how much time has passed since last eat, walk and play
		PetMood.setFoodMood(PetMood.getFoodMood() + PetMood.moodBarDecrease(PetMood.getLastEatHour(), PetMood.getCurrentHour()));
		PetMood.setWalkMood(PetMood.getWalkMood() + PetMood.moodBarDecrease(PetMood.getLastWalkHour(), PetMood.getCurrentHour()));
		PetMood.setPlayMood(PetMood.getPlayMood() + PetMood.moodBarDecrease(PetMood.getLastPlayHour(), PetMood.getCurrentHour()));

		moodBar = (ProgressBar) findViewById(R.id.moodbar);
		moodBar.setProgress(PetMood.getSumMood()); 

		// What happens when pushing the eat button
		eat.setOnClickListener(new OnClickListener() {
			/**
			 * Making the dog feel less hungry if it is hungry and 
			 * else give the message i'm full
			 * Also shows a picture of a bone when eating
			 *
			 * @param v - View
			 */
			@Override
			public void onClick (View v){

				petResponse = (TextView) findViewById(R.id.petresponse);

				if(dog.eat()=="eat"){
					play.setEnabled(false);
					eat.setEnabled(false);
					walk.setEnabled(false);
					new Handler().postDelayed(new Runnable() { 
						@Override
						public void run() {
							eat.setEnabled(true);
							walk.setEnabled(true);
							play.setEnabled(true);
						}
					}, 10000);

					petResponse.setText("Yummie!");
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 10000);
					dogBiscuit.setVisibility(View.VISIBLE);
					dogBiscuit.setBackgroundResource(R.anim.animation);
					final AnimationDrawable anim = (AnimationDrawable) dogBiscuit.getBackground(); 
					anim.start();	
					uiHandler.postDelayed(makeTextGone, 10000);

					/** is this needed??
					TimerTask timertask = new TimerTask() {
						@Override
						public void run() {
							anim.stop();

						}

					};

					timer = new Timer();
					timer.schedule(timertask, 10000);



					 */
				}
				else{
					petResponse.setText("I'm full!");
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 5000);
				}

				//Updating the moodbar
				moodBar = (ProgressBar) findViewById(R.id.moodbar);
				moodBar.setProgress(PetMood.getSumMood());

				changePicture(play, eat, walk);
			}
		}
				);


		// What happens when pushing the play button
		play.setOnClickListener(new OnClickListener() {

			/**
			 * Making the dog feel happier when it plays
			 *
			 * @param v - View
			 */
			@Override
			public void onClick (View v){

				if(dog.play() == "play"){
					play.setEnabled(false);
					eat.setEnabled(false);
					walk.setEnabled(false);
					new Handler().postDelayed(new Runnable() { //vaf�r kan man inte l�gga i samma run egentligen?
						@Override
						public void run() {
							eat.setEnabled(true);
							walk.setEnabled(true);
							play.setEnabled(true);
						}
					}, 5000);
					startActivity(new Intent(PetActivity.this, PlayActivity.class));
					petResponse.setText("Yeey! Lots of fun!");
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 2000);
					final Animation anim = AnimationUtils.loadAnimation(PetActivity.this, R.anim.animation1);
					dogPicture.startAnimation(anim);

					//f�r att bbyta aktivitet


					//Updating the moodbar
					moodBar = (ProgressBar) findViewById(R.id.moodbar);
					moodBar.setProgress(PetMood.getSumMood());
				}
				else if(dog.play() == "toohungry"){
					petResponse.setText("I'm too hungry!");
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 2000);
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 2000);
				}

				else{

					petResponse.setText("I'm tired! I want to rest!");
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 2000);
				}
				changePicture(play, eat, walk);

			}
		}
				);


		// What happens when pushing the walk button
		walk.setOnClickListener(new OnClickListener() {

			/**
			 * Making the dog feel happier when it walks
			 *
			 * @param v - View
			 */
			@Override
			public void onClick (View v){

				// Moving to the WalkActivity class if foodmood is high enough and petmood is below 5.
				if((PetMood.getFoodMood() < 3 && PetMood.getWalkMood() < 5) || PetMood.getFoodMood() > 5){
					petResponse = (TextView) findViewById(R.id.petresponse);
					petResponse.setText(dog.walk(0));
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 2000);
				}
				else{
					PetActivity.this.startActivityForResult(new Intent(PetActivity.this, WalkActivity.class), 1);
				}
				changePicture(play, eat, walk);
			}
		}
				);


		//Setting up the music in the Activity
		try {
			afd = getAssets().openFd("readyforapetsong4.m4v");
			player = new MediaPlayer();
			player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),afd.getLength());
			player.setLooping(true);
			player.prepare();
			player.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Recieves a resultCode that includes the distance a person has walked when quitting WalkActivity and resuming PetActivity.
	 * 
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		petResponse = (TextView) findViewById(R.id.petresponse);

		//Gets the dog's response and sets the value the moodbar should have after taking a walk
		petResponse.setText(dog.walk(resultCode));
		petResponse.setVisibility(View.VISIBLE);
		uiHandler.postDelayed(makeTextGone, 2000);

		// Updating the moodbar after taking a walk
		moodBar = (ProgressBar) findViewById(R.id.moodbar);
		moodBar.setProgress(PetMood.getSumMood());
	}

	/**
	 * Method onPause for the activity
	 * 
	 * Pauses music player and saves the pet when pausing activity
	 */
	public void onPause() {
		super.onPause();
		player.pause();


		try { 
			dog.save("pet_file.dat",PetActivity.this);
			//Test to see if the file is saved
			File file = getBaseContext().getFileStreamPath("pet_file.dat");
			if(file.exists()){
				Log.i(LOG_TAG1,"is saved on internal memory");
			}
			else{
				Log.i(LOG_TAG1,"is not saved on internal memory");
			}  

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	@Override
	protected void onStart() {
		super.onStart();
		// The activity is about to become visible.
	}


	/**
	 * Method onResume for the activity
	 * 
	 * Starts music player when resuming activity
	 */
	public void onResume() {
		super.onResume();
		player.start();
	}


	/**
	 * Method onStop for the activity
	 * 
	 * Pauses the music when exiting activity
	 */
	protected void onStop() {
		super.onStop();
		player.stop();
		try {
			player.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// The activity is about to be destroyed.
	}


	//TODO Add better comments for this method
	/**
	 * Method onOptionsItemSelected 
	 * 
	 * How the app navigates when clicking the backward button
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
	 * Method called to change image depeding on the pet's mood.
	 * 
	 * @param play - Button
	 * @param eat - Button
	 * @param walk - Button
	 */
	private void changePicture(Button play, Button eat, Button walk){
		if ((PetMood.getCurrentHour() - PetMood.getLastEatHour() > 48) || PetMood.getCurrentHour() - PetMood.getLastWalkHour() > 48){
			dogPicture.setImageDrawable(getResources().getDrawable(R.drawable.dogdead));
			final Animation anim = AnimationUtils.loadAnimation(PetActivity.this, R.anim.animation1);
			dogPicture.startAnimation(anim);
			killPet(play, eat, walk);
			//Test to see if the file is deleted
			File file = getBaseContext().getFileStreamPath("pet_file.dat");
			if(file.exists()){
				Log.i(LOG_TAG2,"is still saved on internal memory");
			}
			else{
				Log.i(LOG_TAG2,"is deleted from on internal memory");
			}  
			
		}
		else if(PetMood.getWalkMood()<5 && PetMood.getFoodMood() > 3){
			dogPicture.setImageDrawable(getResources().getDrawable(R.drawable.dogpoop));
		}
		else if(PetMood.getSumMood() < 10){
			dogPicture.setImageDrawable(getResources().getDrawable(R.drawable.dogsad));
		}
		else{
			dogPicture.setImageDrawable(getResources().getDrawable(R.drawable.doghappy));
		}
	}


	/**
	 * Method called when dog dies.  Sets text, disables buttons and deletes saved file.
	 * 
	 * @param play - Button
	 * @param eat - Button
	 * @param walk - Button
	 */
	private void killPet(Button play, Button eat, Button walk){
		petResponse.setText(petName + " has unfortunately died!");
		showPetAge.setVisibility(View.GONE);

		//Disabling buttons
		play.setEnabled(false);
		eat.setEnabled(false);
		walk.setEnabled(false);

		//Deleting the existing saved dog
		deleteFile("pet_file.dat");
	}

}
