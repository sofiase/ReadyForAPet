package edu.chl.dat255.sofiase.readyforapet;



import java.io.IOException;
import java.io.Serializable;
import Model.Dog;
import Model.PetMood;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
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
	private Handler uiHandler = new Handler();
	private ImageView dogBiscuit, dogPicture;
	private ProgressBar moodBar;
	private PetMood petMood = new PetMood();
	private Dog dog = (Dog) CreatePet.getPet();


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

		dogBiscuit = (ImageView) findViewById(R.id.dogbiscuit);
		dogBiscuit.setVisibility(View.GONE);


		dogPicture = (ImageView) findViewById(R.id.dogpicture);//kolla om detta behšvs
		dogPicture.setVisibility(View.VISIBLE);


		Dog pet = (Dog) CreatePet.getPet();
		String petName = pet.getName();

		//Making welcome message
		petResponse = (TextView) findViewById(R.id.petresponse);

		petResponse.setText("Hello, my name is " + petName + "!");		
		petResponse.setVisibility(View.VISIBLE);
		uiHandler.postDelayed(makeTextGone, 2000);	

		moodBar = (ProgressBar) findViewById(R.id.moodbar);
		moodBar.setProgress(petMood.getSumMood());

		// Making the eat button
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
					anim.start();	//varfšr funkar det inte andra gg man trycker?
					uiHandler.postDelayed(makeTextGone, 10000);

				}
				else{
					petResponse.setText("I'm full!");
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 5000);
				}



				//Updating the moodbar
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
				if(dog.play()=="play"){


					petResponse = (TextView) findViewById(R.id.petresponse);
					petResponse.setText("Yeey! Lots of fun!");
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 2000);
					Animation anim = AnimationUtils.loadAnimation(PetActivity.this, R.anim.animation1);
					dogPicture.startAnimation(anim);
					//Updating the moodbar
					moodBar = (ProgressBar) findViewById(R.id.moodbar);
					moodBar.setProgress(petMood.getSumMood());
				}
				else{
					petResponse = (TextView) findViewById(R.id.petresponse);
					petResponse.setText("I'm tired! I want to rest!");
					petResponse.setVisibility(View.VISIBLE);
					uiHandler.postDelayed(makeTextGone, 2000);
				}
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

				int startMood = petMood.getSumMood();

				petResponse = (TextView) findViewById(R.id.petresponse);
				petResponse.setText(dog.walk());
				petResponse.setVisibility(View.VISIBLE);
				uiHandler.postDelayed(makeTextGone, 2000);

				// Moving to the WalkActivity class if foodmood is high enough
				if(startMood < petMood.getSumMood()){
				startActivity(new Intent(PetActivity.this, WalkActivity.class));
				}
				
				// Updating the moodbar
				moodBar = (ProgressBar) findViewById(R.id.moodbar);
				moodBar.setProgress(petMood.getSumMood());
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
	 * Method onPause for the activity
	 * 
	 * Pauses music player when pausing activity
	 */
	public void onPause() {
		super.onPause();
		player.pause();
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
	 * Stops music when exiting activity
	 */
	protected void onStop() {
		super.onStop();
		player.stop();
		player = null;
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

}
