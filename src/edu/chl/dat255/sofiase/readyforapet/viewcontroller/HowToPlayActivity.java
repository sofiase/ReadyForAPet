package edu.chl.dat255.sofiase.readyforapet.viewcontroller;

import edu.chl.dat255.sofiase.readyforapet.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


/**
 * HowToPlayActivity instructs the user on how to play ReadyForAPet
 * 
 * Copyright (C) 2013 Katrin Miettinen, Linnea Pettersson, Sofia Selin, Johanna Ydergard
 * 
 * Licensed under the MIT license. This file must only be used in accordance with the license. 
 * 
 */

public class HowToPlayActivity extends Activity{

	private TextView instructions;
	
	/**
	 * onCreate method
	 * 
	 * @param savedInstanceState - Bundle
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.howtoplayactivity);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		instructions = (TextView) findViewById(R.id.howtoplay);
		instructions.setVisibility(View.VISIBLE);
		
		instructions.setText("Create a pet and name it by selecting play in the main menu. \n\n" +
				"Take care of your pet and make it happier by feeding, walking and playing with it every day. " + 
				"When walking the pet you need to actually take a walk - all to prove that you are ready to take care of a real pet. \n\n" + 
				"But watch out! If you leave it unattended for more than two days it is going to die..");	
	}		

	/**
	 * Configurates the navigate Up button in this activity
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
