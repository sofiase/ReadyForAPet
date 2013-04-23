package edu.chl.dat255.sofiase.readyforapet;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		Button play = (Button) findViewById(R.id.play);
		play.setOnClickListener(new OnClickListener() {

			public void onClick (View v){
				startActivity(new Intent(MainActivity.this, SelectGame.class));
			}
		}
				);


		Button settings = (Button) findViewById(R.id.settings);
		settings.setOnClickListener(new OnClickListener() {

			public void onClick (View v){
				startActivity(new Intent(MainActivity.this, Settings.class));
			}
		}
				);


		Button quit = (Button) findViewById(R.id.quit);
		quit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				System.exit(0);
			}
		}
				);
		



	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
