package edu.chl.dat255.sofiase.readyforapet;


import java.io.IOException;
import java.io.Serializable;
import Model.Pet;
import Model.PetMood;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
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
	private static final String LOG_test = null;
	private TextView petResponse; 
	private Handler uiHandler = new Handler();
	private ImageView dogBiscuit, dogPicture;
	private ProgressBar moodBar;
	private Pet dog;




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

		//Katrins gamla kod:
		//petResponse = (TextView) findViewById(R.id.petresponse);
		//petResponse.setVisibility(View.GONE);

		//dogBiscuit = (ImageView) findViewById(R.id.dogbiscuit);
		//dogBiscuit.setVisibility(View.GONE);

		dog = CreatePet.getPet();
		String petName = dog.getName(); 

		dogBiscuit = (ImageView) findViewById(R.id.dogbiscuit);
		dogBiscuit.setVisibility(View.GONE);


		dogPicture = (ImageView) findViewById(R.id.dogpicture);//kolla om detta beh�vs
		dogPicture.setVisibility(View.VISIBLE);
		changePicture();
		//Sofias kod:
		//Dog pet = (Dog) CreatePet.getPet();
		//String petName = pet.getName();




		//Making welcome message
		petResponse = (TextView) findViewById(R.id.petresponse);

		petResponse.setText("Hello, my name is " + petName + "!");		
		petResponse.setVisibility(View.VISIBLE);
		uiHandler.postDelayed(makeTextGone, 2000);	

		moodBar = (ProgressBar) findViewById(R.id.moodbar);
		moodBar.setProgress(PetMood.getSumMood()); 
		Button eat = (Button) findViewById(R.id.eat);
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

					petResponse.setText("Yummie!");
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 10000);
					dogBiscuit.setVisibility(View.VISIBLE);
					dogBiscuit.setBackgroundResource(R.anim.animation);
					AnimationDrawable anim = (AnimationDrawable) dogBiscuit.getBackground(); 
					anim.start();	//varf�r funkar det inte andra gg man trycker?
					uiHandler.postDelayed(makeTextGone, 10000);

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
		Button play = (Button) findViewById(R.id.play);
		play.setOnClickListener(new OnClickListener() {

			/**
			 * Making the dog feel happier when it plays
			 *
			 * @param v - View
			 */
			@Override
			public void onClick (View v){

				if(dog.play() == "play"){
					//petResponse = (TextView) findViewById(R.id.petresponse);
					petResponse.setText("Yeey! Lots of fun!");
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 2000);
					Animation anim = AnimationUtils.loadAnimation(PetActivity.this, R.anim.animation1);
					dogPicture.startAnimation(anim);
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
					//petResponse = (TextView) findViewById(R.id.petresponse);
					petResponse.setText("I'm tired! I want to rest!");
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 2000);
				}
				changePicture();

			}
		}
				);


		// Making the walk button
		Button walk = (Button) findViewById(R.id.walk);
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

		try { //lagt till för att spara dogs mood
			//Dog savedog = new Dog(dog.getName(),petMood.getFoodMood(), petMood.getWalkMood(),petMood.getPlayMood());
			dog.save("pet_file.dat",PetActivity.this);
			Log.i(LOG_test,Integer.toString(PetMood.getFoodMood()));
			//petMood.save("petmood_file.dat", PetActivity.this); //eventuellt??
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	/**
	 * Method onResume for the activity
	 * 
	 * Starts music player when resuming activity
	 */
	public void onResume() {
		super.onResume();
		player.start(); // H�r kommer eroormeddelandet!!
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
