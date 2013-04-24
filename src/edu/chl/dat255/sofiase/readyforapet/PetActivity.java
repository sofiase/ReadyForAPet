package edu.chl.dat255.sofiase.readyforapet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class PetActivity extends Activity {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.petactivity);
		
		// Get the pet name from the intent
		Intent nameintent = getIntent();
		String petname = nameintent.getStringExtra(CreatePet.EXTRA_MESSAGE);
		
		//Create the text view
		TextView petgreeting = new TextView(this);
		petgreeting.setTextSize(40);
		
		if(petname != null){
			
			petgreeting.setText("Hello, my name is " + petname + "!");
	
		}
		
		else{
		
			petgreeting.setText(" Hi budy, I've missed you!");
			
		}
		
		//Set the pet greeting as the activity layout
		setContentView(petgreeting);
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
	//"Add the title string" - 75% av scrollern

}
