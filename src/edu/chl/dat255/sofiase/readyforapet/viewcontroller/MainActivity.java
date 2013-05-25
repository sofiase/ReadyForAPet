package edu.chl.dat255.sofiase.readyforapet.viewcontroller;


import edu.chl.dat255.sofiase.readyforapet.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
				slideBetweenActivities(MainActivity.this, SelectGame.class);
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
				slideBetweenActivities(MainActivity.this, HowToPlayActivity.class);
			}
		}
				);


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
	
	private void slideBetweenActivities(Context context, Class<?> activity){
		try { 
		Intent newIntent = new Intent(context, activity); 
		startActivityForResult(newIntent, 0);
		//overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left ); 
		} catch(Exception ex) {
		}
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