package edu.chl.dat255.sofiase.readyforapet.viewcontroller;


import edu.chl.dat255.sofiase.readyforapet.R;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;

/**
 * Class SleepActivity.
 * Let's the pet sleep and measures how long it has slept and sends that time back to PetActivity.
 *
 * Copyright (C) 2013 Katrin Miettinen, Linnea Pettersson, Sofia Selin, Johanna Ydergard
 * 
 * Licensed under the MIT license. This file must only be used in accordance with the license. 
 *
 */
public class SleepActivity extends Activity {

	private Button startSleeping;
	private Button stopSleeping;
	private long startHour;
	private int sleepHours;
	private Handler uiHandler = new Handler();
	private ImageView sleepingDog;
	private AnimationDrawable anim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sleep);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		//Show the Up button in the action bar.
		setupActionBar();

		startSleeping = (Button) findViewById(R.id.startsleeping);
		stopSleeping = (Button) findViewById(R.id.stopsleeping);
		sleepingDog = (ImageView) findViewById(R.id.sleepingdog);
		stopSleeping.setEnabled(false);


		startSleeping.setOnClickListener(new OnClickListener() {
			/**
			 * Method onClick for the start sleeping button
			 * 
			 * @param v - View
			 */
			public void onClick (View v){

				stopSleeping.setEnabled(true);
				startSleeping.setEnabled(false);
				
				//Getting the time the pet starts to sleep
				startHour = CreatePet.getPet().getPetMood().getCurrentHour();

				sleepingDog.setVisibility(View.VISIBLE);
				sleepingDog.setBackgroundResource(R.anim.animation5);
				anim = (AnimationDrawable) sleepingDog.getBackground(); 
				anim.start();
				uiHandler.postDelayed(makeViewStop, 12000);

			}
		}
				);


		stopSleeping.setOnClickListener(new OnClickListener() {

			/**
			 * Method onClick for the stop sleeping
			 * 
			 * @param v - View
			 */
			public void onClick (View v){
				
				//Calculating for how long the dog has slept
				if (startHour != 0){
					sleepHours = (int) ((CreatePet.getPet().getPetMood().getCurrentHour()) - startHour);
				}

				//Setting a resultCode with the distance walked that is sent to PetActivity
				SleepActivity.this.setResult(sleepHours);
				SleepActivity.this.finish();

			}
		}
				);


	}
	
	Runnable makeViewStop = new Runnable(){
		@Override
		public void run(){
			anim.stop();
			//sleepingDog.setVisibility(View.GONE);
		}
	};

	/*
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.sleep, menu);
		return true;
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
