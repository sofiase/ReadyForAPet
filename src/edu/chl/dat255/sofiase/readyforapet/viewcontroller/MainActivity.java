package edu.chl.dat255.sofiase.readyforapet.viewcontroller;


import edu.chl.dat255.sofiase.readyforapet.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * MainActivity displays the main menu with options Play, How to play and Quit.
 *
 * Copyright (C) 2013 Katrin Miettinen, Linnea Pettersson, Sofia Selin, Johanna Ydergard
 * 
 * Licensed under the MIT license. This file must only be used in accordance with the license. 
 *
 */
public class MainActivity extends Activity {

	/**
	 * Method for handling the orientation change 
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig); 
	}
	

	/**
	 * Method onCreate for the MainActivity class
	 * 
	 * @param savedInstanceState - Bundle
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		Button play = (Button) findViewById(R.id.play);
		play.setOnClickListener(new OnClickListener() {

			
			public void onClick (View v){ 
				startActivity(new Intent(MainActivity.this, SelectGameActivity.class));
			}
		}
		);

		Button settings = (Button) findViewById(R.id.howtoplay);
		settings.setOnClickListener(new OnClickListener() {
			/**
			 * Method onClick for the settings button
			 * 
			 * @param v - View
			 */
			public void onClick (View v){
				startActivity(new Intent(MainActivity.this, HowToPlayActivity.class));
			}
		});

		Button quit = (Button) findViewById(R.id.quit);
		quit.setOnClickListener(new OnClickListener() {
			/**
			 * Method onClick for the quit button
			 * 
			 * @param v - View
			 */
			@Override
			public void onClick(View v) {	
				finish();
				System.exit(0);
			}
		}
		);
	}	


	/**
	 * Inflate the menu; this adds items to the action bar if it is present
	 * 
	 * @param menu - Menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}