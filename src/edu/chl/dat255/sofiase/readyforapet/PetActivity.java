package edu.chl.dat255.sofiase.readyforapet;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import Model.Pet;
import Model.PetMood;
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
	private static final String LOG_TAG = "File status after method save";
	private Handler uiHandler = new Handler();
	//private Handler handler = new Handler();
	private ImageView dogBiscuit, dogPicture;
	private ProgressBar moodBar;
	private Pet dog;
	private String petName;
	private int petAge;

	//private static final String LOG_test = "currenttime";
	//private static final String LOG_test1 = "birthtime";
	//private static final String LOG_test2 = "playmood";


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
		//Getting the age of the pet
		petAge = (int) (PetMood.getCurrentTime() - dog.getBirthTime()) / 24;
		//Log.i(LOG_test, Long.toString(PetMood.getCurrentTime()));
		//Log.i(LOG_test1, Long.toString(dog.getBirthTime()));

		dogBiscuit = (ImageView) findViewById(R.id.dogbiscuit);
		dogBiscuit.setVisibility(View.GONE);


		dogPicture = (ImageView) findViewById(R.id.dogpicture);
		dogPicture.setVisibility(View.VISIBLE);
		changePicture();

		final Button play = (Button) findViewById(R.id.play);
		final Button walk = (Button) findViewById(R.id.walk);
		final Button eat = (Button) findViewById(R.id.eat);
		

		//Setting textview with welcome message
		petResponse = (TextView) findViewById(R.id.petresponse);
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
		showPetAge = (TextView) findViewById(R.id.petage);
		showPetAge.setText(petName + " is " + petAge + " days old.");
		petResponse.setVisibility(View.VISIBLE);

		//Decreasing the FoodMood depending on how much time has passed since last eat
		PetMood.setFoodMood(PetMood.getFoodMood() + PetMood.moodBarDecrease(PetMood.getLastEatTime(), PetMood.getCurrentTime()));
		//Decreasing the FoodMood depending on how much time has passed since last walk
		PetMood.setWalkMood(PetMood.getWalkMood() + PetMood.moodBarDecrease(PetMood.getLastWalkTime(), PetMood.getCurrentTime()));
		//Decreasing the FoodMood depending on how much time has passed since last play
		PetMood.setPlayMood(PetMood.getPlayMood() + PetMood.moodBarDecrease(PetMood.getLastPlayTime(), PetMood.getCurrentTime()));

		//Log.i(LOG_test, Integer.toString(PetMood.getFoodMood()));
		//Log.i(LOG_test1, Integer.toString(PetMood.getWalkMood()));
		//Log.i(LOG_test2, Integer.toString(PetMood.getPlayMood()));
		
		moodBar = (ProgressBar) findViewById(R.id.moodbar);
		moodBar.setProgress(PetMood.getSumMood()); 

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

				changePicture();

				//Updating the moodbar
				moodBar = (ProgressBar) findViewById(R.id.moodbar);
				moodBar.setProgress(PetMood.getSumMood());
			}
		}
				);


		// Making the play button
		//Button play = (Button) findViewById(R.id.play);
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
				changePicture();

			}
		}
				);


		// Making the walk button
		//Button walk = (Button) findViewById(R.id.walk);
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
				changePicture();
			}
		}
				);


		//Music
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
	 * Pauses music player when pausing activity
	 */
	public void onPause() {
		super.onPause();
		player.pause();


		try { 
			dog.save("pet_file.dat",PetActivity.this);
			File file = getBaseContext().getFileStreamPath("pet_file.dat");
			if(file.exists()){
				Log.i(LOG_TAG,"is saved on internal memory");
			}
			else{
				 Log.i(LOG_TAG,"is not saved on internal memory");
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
	 * Currently pauses music when exiting activity
	 */
	protected void onStop() {
		super.onStop();
		player.pause();
		//player = null;
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
	 * How the app navigates when clicking the backward button (OBS Vet ej om helt korrekt)
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


	private void changePicture(){
		if(PetMood.getWalkMood()<5 && PetMood.getFoodMood() > 3){
			dogPicture.setImageDrawable(getResources().getDrawable(R.drawable.dogpoop));
		}
		else if(PetMood.getSumMood() < 10){
			dogPicture.setImageDrawable(getResources().getDrawable(R.drawable.dogsad));
		}
		else{
			dogPicture.setImageDrawable(getResources().getDrawable(R.drawable.doghappy));
		}


	}


}
