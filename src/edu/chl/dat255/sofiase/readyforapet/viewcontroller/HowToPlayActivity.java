package edu.chl.dat255.sofiase.readyforapet.viewcontroller;


import java.io.Serializable;

import edu.chl.dat255.sofiase.readyforapet.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * 
 */
public class HowToPlayActivity extends Activity implements Serializable{


	private static final long serialVersionUID = 1L;
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
		
		instructions.setText("Create a pet and name it by selecting play in the main menu. \n" +
				"Take care of your pet and make it happier by feeding, walking and playing with it every day. " + 
				"When walking the pet you need to actually take a walk - all to prove that you are ready to take care of a real pet. \n" + 
				"But watch out! If you leave it unattended for more than two days it is going to die!");
		
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
	
		
		@Override
	    protected void onStart() {
	        super.onStart();
	        // The activity is about to become visible.
	    }
	    @Override
	    protected void onResume() {
	        super.onResume();
	        // The activity has become visible (it is now "resumed").
	    }
	    @Override
	    protected void onPause() {
	        super.onPause();
	        // Another activity is taking focus (this activity is about to be "paused").
	    }
	    @Override
	    protected void onStop() {
	        super.onStop();
	        // The activity is no longer visible (it is now "stopped")
	    }
	    @Override
	    protected void onDestroy() {
	        super.onDestroy();
	        // The activity is about to be destroyed.
	    }
	 
}
