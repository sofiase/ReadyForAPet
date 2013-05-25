package edu.chl.dat255.sofiase.readyforapet.viewcontroller;

import java.util.Timer;
import java.util.TimerTask;
import edu.chl.dat255.sofiase.readyforapet.R;
import edu.chl.dat255.sofiase.readyforapet.util.LocationHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WalkActivity extends Activity{

	private TextView displayDistance;
	private ImageView dogPrints;
	private int delay = 0;
	private int period = 30000;
	private int distance = 0;
	private Timer timer;
	private Handler handler = new Handler();
	private Handler uiHandler = new Handler();
	private LocationHelper location;

	Runnable makeViewGone = new Runnable(){
		@Override
		public void run(){
			dogPrints.setVisibility(View.GONE);
		}
	};

	/**
	 * On Create method
	 * 
	 * @param savedInstanceState - bundle
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.walkactivity);
		location = new LocationHelper(this);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		dogPrints = (ImageView) findViewById(R.id.dogprints);

		//Checking if the GPS is enabled, else let the user start GPS if wanted.
		if (location.gpsEnabled()){
			Toast.makeText(this, "GPS is Enabled on your devide", Toast.LENGTH_SHORT).show();
		}
		else{
			showGPSDisabledAlert();
		}

		Button startWalking = (Button) findViewById(R.id.startwalking);

		startWalking.setOnClickListener(new OnClickListener() {

			/**
			 * Method onClick for the start walking button
			 * 
			 * @param v - View
			 */
			public void onClick (View v){
				try{
					timer = new Timer();
					timer.schedule(myTimerTask, delay, period);
				} 
				catch (Exception e){
					e.printStackTrace();
				}
				dogPrints.setVisibility(View.VISIBLE);
				dogPrints.setBackgroundResource(R.anim.animation3);
				AnimationDrawable anim = (AnimationDrawable) dogPrints.getBackground(); 
				anim.start();
				uiHandler.postDelayed(makeViewGone, 7000);
			}
		}

				);

		Button stopWalking = (Button) findViewById(R.id.stopwalking);
		stopWalking.setOnClickListener(new OnClickListener() {

			/**
			 * Method onClick for the stop walking button
			 * 
			 * @param v - View
			 */
			public void onClick (View v){
				distance = (int) Math.round(location.getDistance());
				
				//Turn off GPS
				location.killLocationServices();
				
				//If timer is not initiated, the timer should not be stopped
				if (timer != null){
				timer.cancel();
				}
				
				//setting a resultCode with the distance walked that is sent to PetActivity
				WalkActivity.this.setResult(distance);
				WalkActivity.this.finish();


			}
		}
				);

	}


	TimerTask myTimerTask = new TimerTask() {
		@Override
		public void run() {
			handler.post(new Runnable() {
				@Override
				public void run() {
					displayDistance = (TextView) findViewById(R.id.distance);
					displayDistance.setText("You have walked " + Math.round(location.getDistance()) + " meters so far.");
				}
			});

		}

	};


	/**
	 * If GPS is turned off, lets the user either choose to enable GPS or cancel.
	 * 
	 */
	private void showGPSDisabledAlert(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage("GPS is disabled on your device. Would you like to enable it?")
		.setCancelable(false)
		.setPositiveButton("Go to Settings Page To Enable GPS",
				new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				Intent callGPSSettingIntent = new Intent(
						android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(callGPSSettingIntent);
			}
		});
		alertDialogBuilder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.cancel();
			}
		});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
	}


	/**
	 *
	 * 
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.finish();
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
