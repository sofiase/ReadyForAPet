package edu.chl.dat255.sofiase.readyforapet.viewcontroller;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import edu.chl.dat255.sofiase.readyforapet.R;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
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

import android.content.pm.PackageManager;



public class PlayActivity extends Activity {

	private Button useStandard, takePhoto, dogPlay;
	private ImageView dogFace, dogBody, welcomeDog;
	private Timer timer;
	private Bitmap bm;
	//Variables for playing music in Pet Activity
	private MediaPlayer player;
	private AssetFileDescriptor afd;
	private PackageManager pm;


	Runnable makeTextGone = new Runnable(){

		@Override
		public void run(){
			takePhoto.setVisibility(View.GONE);	
			useStandard.setVisibility(View.GONE);
			welcomeDog.setVisibility(View.VISIBLE);

		}
	};


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
			 * Making it able to take your own background picture
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

				takePhoto.setVisibility(View.GONE);	
				useStandard.setVisibility(View.GONE);
				dogPlay.setVisibility(View.GONE);
				welcomeDog.setVisibility(View.GONE);
				dogBody.setBackgroundResource(R.anim.animation4);
				final AnimationDrawable anim = (AnimationDrawable) dogBody.getBackground(); 
				anim.start();	
				TimerTask timertask = new TimerTask() {
					@Override
					public void run() {
						anim.stop();

						//SKICKA MED ETT RESULT TILL PETACTIVITY
						PlayActivity.this.setResult(1);
						PlayActivity.this.finish();

						//If there a picture was taken the memory is reclaimed as soon right after it's finished displaying
						if (bm!=null) {
							bm.recycle();
						}

					}

				};

				timer = new Timer();
				timer.schedule(timertask, 20000);

			}
		}
				);

	}

	/**
	 * Method for that checks if the advice has a camera
	 */
	public static boolean isIntentAvailable (Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		java.util.List<android.content.pm.ResolveInfo> list =
				packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	/**
	 * Method for making the image a circle
	 */
	public static Bitmap makeCircle (Bitmap bitmap) {

		Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		BitmapShader shader = new BitmapShader (bitmap,  TileMode.CLAMP, TileMode.CLAMP);
		Paint paint = new Paint();
		paint.setShader(shader);
		Canvas c = new Canvas(circleBitmap);
		c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getWidth()/2, paint);
		return circleBitmap;
	}

	/**
	 * Method for what should happen when you have taken a photo
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		takePhoto.setVisibility(View.GONE);	
		useStandard.setVisibility(View.GONE);
		dogPlay.setVisibility(View.VISIBLE);


		super.onActivityResult(requestCode, resultCode, data);
		dogFace.setVisibility(View.VISIBLE);
		// Making the picture circle
		bm = (Bitmap) data.getExtras().get("data");
		dogFace.setImageBitmap(makeCircle(bm));


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(player!=null){
			player.start();  
		}
		// The activity has become visible (it is now "resumed").
	}
	@Override
	protected void onPause() {
		super.onPause();
		if(player!=null){
			player.pause();
		}

		// Another activity is taking focus (this activity is about to be "paused").
	}
	@Override
	protected void onStop() {
		super.onStop();
		if(player!=null){
			player.pause(); 
		}

		// The activity is no longer visible (it is now "stopped")
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// The activity is about to be destroyed.
	}

}


