package edu.chl.dat255.sofiase.readyforapet.viewcontroller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import edu.chl.dat255.sofiase.readyforapet.R;
import edu.chl.dat255.sofiase.readyforapet.model.Dog;
import edu.chl.dat255.sofiase.readyforapet.model.Pet;
import edu.chl.dat255.sofiase.readyforapet.model.PetMood;
import android.app.Activity;
import android.content.Context;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PetActivity extends Activity implements Serializable{ 

	private static final long serialVersionUID = 1L;
	private TextView petResponse, showPetAge;
	private Handler uiHandler = new Handler();
	private ImageView dogBiscuit, dogPicture;
	private ProgressBar moodBar;
	//private Pet dog;
	private Dog dog;
	private String petName;
	private int petAge;
	private CheckBox musicCheckBox;
	private PetMood petMood;
	private Button play;
	private Button walk;
	private Button eat;

	//Variables for LogCat outputs
	private static final String LOG_test = "pet last walk";
	private static final String LOG_test1 = "pet last current";
	private static final String LOG_test2 = "pet last eat";
	private final String LOG_TAG1 = "Information about the file when saving";
	private final String LOG_TAG2 = "Information about the file when deleting";

	//Variables for playing music in Pet Activity
	private MediaPlayer player;
	private AssetFileDescriptor afd;


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

		//Receiving the new or saved pet
		//dog = CreatePet.getPet();
		if (CreatePet.getPet() != null){
			dog = (Dog) CreatePet.getPet();
		}
		else{
			try {
				dog = (Dog) Pet.load("pet_file.dat", PetActivity.this);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//Getting the petMood
		petMood = dog.getPetMood();

		//Getting the pet name
		petName = dog.getName();


		
		play = (Button) findViewById(R.id.play);
		walk = (Button) findViewById(R.id.walk);
		eat = (Button) findViewById(R.id.eat);


		showPetAge = (TextView) findViewById(R.id.petage);
		petResponse = (TextView) findViewById(R.id.petresponse);
		dogBiscuit = (ImageView) findViewById(R.id.dogbiscuit);
		dogPicture = (ImageView) findViewById(R.id.dogpicture);
		dogBiscuit.setVisibility(View.GONE);
		dogPicture.setVisibility(View.VISIBLE);

		//Music
		try {
			afd = getAssets().openFd("readyforapetsong5.mov");
			player = new MediaPlayer();
			player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			player.setLooping(true);
			player.prepare();
			player.start();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		addListenerOnMusic();
		musicCheckBox.setChecked(true);

		//Getting the age of the pet if it has not already died
		//petAge = (int) (petMood.getCurrentHour() - dog.getBirthHour()) / 24;
		petAge = (int) (petMood.getCurrentHour() - dog.getBirthHour());

		//Setting textview with welcome message
		petResponse.setText("Hello, my name is " + petName + "!");
		petResponse.setVisibility(View.VISIBLE);
		uiHandler.postDelayed(makeTextGone, 1000);

		//Setting textview with current age of the pet
		showPetAge.setText(petName + " is " + petAge + " days old.");
		petResponse.setVisibility(View.VISIBLE);

		//Changing the picture and enabling/disabling buttons depending on mood
		changePicture();

		//Decreasing the FoodMood depending on how much time has passed since last eat, walk and play
		petMood.setFoodMood(petMood.getFoodMood() + petMood.moodBarDecrease(petMood.getLastEatHour(), petMood.getCurrentHour()));
		petMood.setWalkMood(petMood.getWalkMood() + petMood.moodBarDecrease(petMood.getLastWalkHour(), petMood.getCurrentHour()));
		petMood.setPlayMood(petMood.getPlayMood() + petMood.moodBarDecrease(petMood.getLastPlayHour(), petMood.getCurrentHour()));

		moodBar = (ProgressBar) findViewById(R.id.moodbar);
		moodBar.setProgress(petMood.getSumMood()); 


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

				if(petMood.getFoodMood() < 5){
					play.setEnabled(false);
					eat.setEnabled(false);
					walk.setEnabled(false);
					new Handler().postDelayed(new Runnable() { 
						@Override
						public void run() {
							eat.setEnabled(true);
							walk.setEnabled(true);
							play.setEnabled(true);
							changePicture();
						}
					}, 10000);

					petResponse.setText(dog.eat());
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 10000);
					dogBiscuit.setVisibility(View.VISIBLE);
					dogBiscuit.setBackgroundResource(R.anim.animation);
					final AnimationDrawable anim = (AnimationDrawable) dogBiscuit.getBackground(); 
					anim.start();	
					uiHandler.postDelayed(makeTextGone, 10000);

				/**
					//is this needed??
					TimerTask timertask = new TimerTask() {
						@Override
						public void run() {
							anim.stop();
						}
					};

					timer = new Timer();
					timer.schedule(timertask, 10000);*/
				}
				
				
					
				else{
					petResponse.setText(dog.eat());
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 5000);
				}
				//Updating the moodbar
				moodBar = (ProgressBar) findViewById(R.id.moodbar);
				moodBar.setProgress(petMood.getSumMood());

				//changePicture(play, eat, walk);
			}
		});

		// What happens when pushing the play button
		play.setOnClickListener(new OnClickListener() {

			/**
			 * Making the dog feel happier when it plays
			 *
			 * @param v - View
			 */
			@Override
			public void onClick (View v){
				//Contnuing to playActivity only of the dog has not died
				if((petMood.getPlayMood() < 5 && petMood.getFoodMood() >= 3) && dog.isAlive()){
					//Opening PlayActivity and recieves a requestCode when resuming this activity
					PetActivity.this.startActivityForResult(new Intent(PetActivity.this, PlayActivity.class), 0);
				}
				else{
					petResponse.setText(dog.play());
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 2000);
				}
				changePicture();

			}
		});

		//What happens when pushing the walk button
		walk.setOnClickListener(new OnClickListener() {
			/**
			 * Making the dog feel happier when it walks
			 *
			 * @param v - View
			 */
			@Override
			public void onClick (View v){

				petResponse = (TextView) findViewById(R.id.petresponse);

				// Moving to the WalkActivity class if foodmood is high enough and petMood is below 5.
				if(((petMood.getFoodMood() < 3 && petMood.getWalkMood() < 5) || petMood.getFoodMood() > 5)){
					petResponse.setText(dog.walk(0));
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 2000);
				}

				else if (dog.isAlive()){
					//Opening PlayActivity and recieves a requestCode when resuming this activity
					PetActivity.this.startActivityForResult(new Intent(PetActivity.this, WalkActivity.class), 1);
				}

				else{
					petResponse.setText(dog.walk(0));
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 2000);
				}
				changePicture();
			}
		});
	}
	
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
	 * Recieves a requestCode when resuming PetActivity from either PlayActivity or WalkActivity.
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		petResponse = (TextView) findViewById(R.id.petresponse);

		//When coming from the PlayActivity and the dog is done playing.
		if(requestCode == 0 && resultCode == 1){
			//Gets the dog's response and sets the value the moodbar should have after playing
			petResponse.setText(dog.play());
			petResponse.setVisibility(View.VISIBLE);
			uiHandler.postDelayed(makeTextGone, 2000);

			//Updating the moodbar after playing
			moodBar = (ProgressBar) findViewById(R.id.moodbar);
			moodBar.setProgress(petMood.getSumMood());
		}

		//When coming from the WalkActivity
		else if (requestCode == 1){
			//Gets the dog's response and sets the value the moodbar should have after taking a walk
			petResponse.setText(dog.walk(resultCode));
			petResponse.setVisibility(View.VISIBLE);
			uiHandler.postDelayed(makeTextGone, 2000);

			// Updating the moodbar after taking a walk
			moodBar = (ProgressBar) findViewById(R.id.moodbar);
			moodBar.setProgress(petMood.getSumMood());
		}

	}

	public void addListenerOnMusic() {
		musicCheckBox = (CheckBox) findViewById(R.id.checkbox1);
		musicCheckBox.setOnClickListener(new OnClickListener() {
			@Override

			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					player.start();	
				}
				else {
					player.pause();
				}
			}
		});
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
	
	/**
	 * Method onResume for the activity
	 * 
	 * Starts music player when resuming activity
	 */
	public void onResume() {
		super.onResume();
		player.start();
		musicCheckBox.setChecked(true);
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
		player.stop();

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
	//private void changePicture(Button play, Button eat, Button walk){
	private void changePicture(){
		Log.i(LOG_test, Long.toString(petMood.getLastWalkHour()));
		Log.i(LOG_test1, Long.toString(petMood.getCurrentHour()));
		Log.i(LOG_test2, Long.toString(petMood.getLastEatHour()));
		//Kills the pet if it has died
		if (!dog.isAlive()){
			dogPicture.setImageDrawable(getResources().getDrawable(R.drawable.dogdead));
			final Animation anim = AnimationUtils.loadAnimation(PetActivity.this, R.anim.animation1);
			dogPicture.startAnimation(anim);
			killPet();

			//Test to see if the file is deleted
			File file = getBaseContext().getFileStreamPath("pet_file.dat");

			if(file.exists()){
				Log.i(LOG_TAG2,"is still saved on internal memory");
			}
			else{
				Log.i(LOG_TAG2,"is deleted from on internal memory");
			}  

		}
		else if(petMood.getWalkMood() < 4 && petMood.getFoodMood() > 3){
			dogPicture.setImageDrawable(getResources().getDrawable(R.drawable.dogpoop));
		}
		else if(petMood.getSumMood() < 7){
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
	private void killPet(){
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

