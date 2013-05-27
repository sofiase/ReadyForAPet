package edu.chl.dat255.sofiase.readyforapet.viewcontroller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import edu.chl.dat255.sofiase.readyforapet.R;
import edu.chl.dat255.sofiase.readyforapet.model.Pet;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePet extends Activity implements OnClickListener, Serializable {

	private static final long serialVersionUID = 1L;
	private String petName;
	private static Pet dog;
	private MediaPlayer player;
	private AssetFileDescriptor afd;
	//private Timer timer; behövs det en timer eller kör den bara en gång by default?

	/**
	 * onCreate Method
	 *
	 * @param savedInstanceState - Bundle
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.createpet);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				
		Button create = (Button) findViewById(R.id.puppy_settings);
		create.setOnClickListener(this);
		//Dog barks when activity is started
		try {
			afd = getAssets().openFd("dogbark.m4a");
			player = new MediaPlayer();
			player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),afd.getLength());
			player.setLooping(true);
			player.prepare();
			player.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

		/**
		 * TimerTask timertask = new TimerTask() {
					@Override
					public void run() {
						player.paus();//eller stop?

					}

				};

				timer = new Timer();
				timer.schedule(timertask, 2000);

		 */
		
		
	}

	/**
	 * onClick Method
	 * 
	 * When pressing the button create it checks whether the user has typed a name, and then creates an instance of the Pet class.
	 * 
	 */
	public void onClick (View v){
		EditText setName = (EditText) findViewById(R.id.edit_pet_name);
		petName = setName.getText().toString();
		if (petName.equals("")){
			Toast.makeText(this, "Give your pet a name!", Toast.LENGTH_SHORT).show();
		}
		
		else {
			dog = new Pet(petName, 0, 0, 0);
			startActivity(new Intent(CreatePet.this, PetActivity.class));
		}
	}

	/**
	 * getPet Method
	 * 
	 * makes the created pet available to other classes
	 *
	 * @return dog - an instance of the class Dog
	 */
	public static Pet getPet(){
		return dog;
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
        player.start();
       
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        player.pause();//eller stop?
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        player.pause();//eller stop?
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }

	
}

