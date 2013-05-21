package edu.chl.dat255.sofiase.readyforapet;

import java.io.IOException;
import java.io.Serializable;
import Model.Pet;
import Model.PetMood;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PetActivity extends Activity implements Serializable{ 

	private static final long serialVersionUID = 1L;
	private static final String LOG_test = null;
	private TextView petResponse; 
	private Handler uiHandler = new Handler();
	private ImageView dogBiscuit;//kolla  TODO
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

		petResponse = (TextView) findViewById(R.id.petresponse);
		petResponse.setVisibility(View.GONE);

		dogBiscuit = (ImageView) findViewById(R.id.dogbiscuit);
		dogBiscuit.setVisibility(View.GONE);

		dog = CreatePet.getPet();
		String petName = dog.getName(); 

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
				petResponse.setText(dog.eat());
				petResponse.setVisibility(View.VISIBLE);
				uiHandler.postDelayed(makeTextGone, 2000);
				dogBiscuit.setVisibility(View.VISIBLE);//temporary solution must make picture dissapear when dog is full
				uiHandler.postDelayed(makeTextGone, 2000);

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

				petResponse = (TextView) findViewById(R.id.petresponse);
				petResponse.setText(dog.play());
				petResponse.setVisibility(View.VISIBLE);
				uiHandler.postDelayed(makeTextGone, 2000);

				//Updating the moodbar
				moodBar = (ProgressBar) findViewById(R.id.moodbar);
				moodBar.setProgress(PetMood.getSumMood());
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

				petResponse = (TextView) findViewById(R.id.petresponse);
				petResponse.setText(dog.walk());
				petResponse.setVisibility(View.VISIBLE);
				uiHandler.postDelayed(makeTextGone, 2000);

				// Updating the moodbar
				moodBar = (ProgressBar) findViewById(R.id.moodbar);
				moodBar.setProgress(PetMood.getSumMood());
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
