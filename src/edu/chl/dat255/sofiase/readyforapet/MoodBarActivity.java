package edu.chl.dat255.sofiase.readyforapet;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class MoodBarActivity extends Activity{

	private ProgressBar moodBar;
	private int moodStatus = 0;
	private Handler moodHandler = new Handler();
	private PetMood petmood = new PetMood();

	protected void onCreate (Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.petactivity);

		moodBar = (ProgressBar) findViewById(R.id.moodbar);

		// Start lengthy operation in a background thread
		new Thread(new Runnable() {
			public void run() {
				while (moodStatus > 0) {
					moodStatus = petmood.getSumMood();

					// Updating the progress bar
					moodHandler.post(new Runnable() {
						public void run() {
							moodBar.setProgress(moodStatus);
						}
					});
				}
			}
		}).start();
	}


}


