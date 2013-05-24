package edu.chl.dat255.sofiase.readyforapet;


import java.io.Serializable;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

/**
 * 
 */
public class Settings extends Activity implements Serializable{


	private static final long serialVersionUID = 1L;

	/**
	 * onCreate method
	 * 
	 * @param savedInstanceState - Bundle
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.settings);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
