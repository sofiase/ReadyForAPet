package edu.chl.dat255.sofiase.readyforapet;


import java.io.Serializable;
import android.app.Activity;
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
