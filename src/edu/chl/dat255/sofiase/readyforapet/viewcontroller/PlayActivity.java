package edu.chl.dat255.sofiase.readyforapet.viewcontroller;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import edu.chl.dat255.sofiase.readyforapet.R;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Class PlayActiviy, where the user is able to take a photo of a dogs face or use a standard photo to make the dog dance.
 * When done dancing the user is sent to PetActivity
 *
 * Copyright (C) 2013 Katrin Miettinen, Linnea Pettersson, Sofia Selin, Johanna Ydergard
 * 
 * Licensed under the MIT license. This file must only be used in accordance with the license. 
 *
 */
public class PlayActivity extends Activity {
	private Button useStandard, takePhoto, dogPlay;
	private ImageView dogFace, dogBody, welcomeDog;
	private Timer timer;
	private Bitmap bm; 
	private PackageManager pm;
	
	//Variables for playing music in Pet Activity
	private MediaPlayer player;
	private AssetFileDescriptor afd;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		dogFace = (ImageView) findViewById(R.id.dogface);
		takePhoto = (Button) findViewById(R.id.takephoto);
		useStandard = (Button) findViewById(R.id.usestandard);
		dogPlay = (Button) findViewById(R.id.dogplay);
		dogBody = (ImageView) findViewById(R.id.dogbody);
		welcomeDog = (ImageView) findViewById(R.id.welcomedog);

		//Checks if the device has a camera, and adjusts the view after the result
		pm = PlayActivity.this.getPackageManager();
		if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			dogPlay.setVisibility(View.GONE);
			dogFace.setVisibility(View.GONE);
			takePhoto.setVisibility(View.VISIBLE);
			useStandard.setVisibility(View.VISIBLE);
			welcomeDog.setVisibility(View.VISIBLE);
		}
		else{
			dogPlay.setVisibility(View.VISIBLE);
			welcomeDog.setVisibility(View.VISIBLE);
			dogFace.setVisibility(View.GONE);
			takePhoto.setVisibility(View.GONE);
			useStandard.setVisibility(View.GONE);
		}

		takePhoto.setOnClickListener(new OnClickListener() {
			/**
			 * Making it able to take your own background picture
			 *
			 * @param v - View
			 */
			@Override
			public void onClick (View v){
				Intent pictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(pictureIntent, 0);
			}
		}
				);


		useStandard.setOnClickListener(new OnClickListener() {
			/**
			 * Making it able to use the standard picture 
			 *
			 * @param v - View
			 */
			@Override
			public void onClick (View v){
				dogPlay.setVisibility(View.VISIBLE);
				takePhoto.setVisibility(View.GONE);
				useStandard.setVisibility(View.GONE);
			}
		}
				);

		dogPlay.setOnClickListener(new OnClickListener() {
			/**
			 * Making it able to take your own background picture
			 *
			 * @param v - View
			 */
			@Override
			public void onClick (View v){

				//Dance music starts when dog starts playing
				try {
					afd = getAssets().openFd("dancemusic.m4a");
					player = new MediaPlayer();
					player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),afd.getLength());
					player.setLooping(true);
					player.prepare();
					player.start();
				} catch (IOException e) {
					e.printStackTrace();
				}

				// Makes the buttons disappear and the pictures appear
				takePhoto.setVisibility(View.GONE);
				useStandard.setVisibility(View.GONE);
				dogPlay.setVisibility(View.GONE);
				welcomeDog.setVisibility(View.GONE);

				//Starts animation to illustrate the dog dancing
				dogBody.setBackgroundResource(R.anim.animation4);
				final AnimationDrawable anim = (AnimationDrawable) dogBody.getBackground();
				anim.start();

				//Makes the animation stop and end the activity after 20 seconds.
				TimerTask timertask = new TimerTask() {
					@Override
					public void run() {
						anim.stop();

						//Sending a result to PetActivity
						PlayActivity.this.setResult(1);
						PlayActivity.this.finish();

						//If a picture was taken the memory is reclaimed right after it's finished displaying
						if (bm != null) {
							bm.recycle();
							bm = null;
							System.gc();

						}
					}
				};
				timer = new Timer();
				timer.schedule(timertask, 15000);
			}
		}
				);
	}

	//To handle which view to display
	Runnable makeTextGone = new Runnable(){
		@Override
		public void run(){
			takePhoto.setVisibility(View.GONE);
			useStandard.setVisibility(View.GONE);
			welcomeDog.setVisibility(View.VISIBLE);
		}
	};

	/**
	 * Method for making a bitmap image a circle
	 */
	public static Bitmap makeCircle (Bitmap bitmap) {
		Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		BitmapShader shader = new BitmapShader (bitmap, TileMode.CLAMP, TileMode.CLAMP);
		Paint paint = new Paint();
		paint.setShader(shader);
		Canvas c = new Canvas(circleBitmap);
		c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getWidth()/2, paint);
		return circleBitmap;
	}

	/**
	 * Method for what should happen when a photo is taken
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		takePhoto.setVisibility(View.GONE);
		useStandard.setVisibility(View.GONE);
		dogPlay.setVisibility(View.VISIBLE);
		
		super.onActivityResult(requestCode, resultCode, data);

		 //Checking if the the camera is cancelled before picture is taken
			if(resultCode != RESULT_CANCELED){
				dogFace.setVisibility(View.VISIBLE);

				// Making the picture circular
				bm = (Bitmap) data.getExtras().get("data");
				dogFace.setImageBitmap(makeCircle(bm));
				dogFace.setVisibility(View.VISIBLE);
			}
			else{
				dogFace.setVisibility(View.GONE);

		}

	}
	  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play, menu);
		return true;
	}

	/**
	 * Starts music player when resuming activity
	 *
	 */
	@Override
	protected void onResume() {
		super.onResume();
		if(player != null){
			player.start();
		}
	}

	/**
	 * Pauses music player when pausing activity
	 *
	 */
	@Override
	protected void onPause() {
		super.onPause();
		if(player != null){
			player.pause();
		}
	}

	/**
	 * Pauses music player when stopping activity
	 *
	 */
	@Override
	protected void onStop() {
		super.onStop();
		if(player != null){
			player.pause();
		}
	}

}

