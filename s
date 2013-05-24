[1mdiff --cc src/edu/chl/dat255/sofiase/readyforapet/PetActivity.java[m
[1mindex 7390c9d,86c59c4..0000000[m
[1m--- a/src/edu/chl/dat255/sofiase/readyforapet/PetActivity.java[m
[1m+++ b/src/edu/chl/dat255/sofiase/readyforapet/PetActivity.java[m
[36m@@@ -1,9 -1,10 +1,11 @@@[m
  package edu.chl.dat255.sofiase.readyforapet;[m
  [m
  [m
[32m +import java.io.File;[m
  import java.io.IOException;[m
  import java.io.Serializable;[m
[32m+ import java.lang.reflect.Array;[m
[32m+ [m
  import Model.Pet;[m
  import Model.PetMood;[m
  import android.app.Activity;[m
[36m@@@ -330,17 -318,8 +320,22 @@@[m [mpublic class PetActivity extends Activi[m
  		super.onPause();[m
  		player.pause();[m
  [m
[32m++<<<<<<< HEAD[m
[32m +[m
[32m +		try { [m
[32m +			dog.save("pet_file.dat",PetActivity.this);[m
[32m +			File file = getBaseContext().getFileStreamPath("pet_file.dat");[m
[32m +			if(file.exists()){[m
[32m +				Log.i(LOG_TAG,"is saved on internal memory");[m
[32m +			}[m
[32m +			else{[m
[32m +				 Log.i(LOG_TAG,"is not saved on internal memory");[m
[32m +			}  [m
[32m +[m
[32m++=======[m
[32m+ 		try {[m
[32m+ 			dog.save("pet_file.dat", PetActivity.this);[m
[32m++>>>>>>> 32a2757aa95e4b4ad56c35bcaa8f68c251164aac[m
  		} catch (IOException e) {[m
  			e.printStackTrace();[m
  		}[m
[1mdiff --cc src/edu/chl/dat255/sofiase/readyforapet/SelectGame.java[m
[1mindex 73d8dc9,7ff6a9c..0000000[m
[1m--- a/src/edu/chl/dat255/sofiase/readyforapet/SelectGame.java[m
[1m+++ b/src/edu/chl/dat255/sofiase/readyforapet/SelectGame.java[m
[36m@@@ -71,8 -61,7 +66,12 @@@[m [mpublic class SelectGame extends Activit[m
  			public void onClick (View v){[m
  [m
  				try {[m
[32m++<<<<<<< HEAD[m
[32m +					Pet.load("pet_file.dat", SelectGame.this); [m
[32m +					Log.i(LOG_load,Integer.toString(PetMood.getFoodMood()));					[m
[32m++=======[m
[32m+ 					Pet.load("pet_file.dat", SelectGame.this);[m
[32m++>>>>>>> 32a2757aa95e4b4ad56c35bcaa8f68c251164aac[m
  				} catch (FileNotFoundException e) {[m
  					System.out.print("File not found ");[m
  					e.printStackTrace();[m
[36m@@@ -85,22 -74,11 +84,21 @@@[m
  				} [m
  [m
  				if (CreatePet.getPet() != null){[m
[31m -					startActivity(new Intent(SelectGame.this, PetActivity.class));		[m
[31m -				}[m
[32m +					startActivity(new Intent(SelectGame.this, PetActivity.class));	[m
[32m +				[m
[32m +						String fname = "pet_file.dat";[m
[32m +						File file = getBaseContext().getFileStreamPath(fname);[m
[32m +						if(file.exists()){[m
[32m +							Log.i(LOG_TAG,"is saved on internal memory");[m
[32m +						}[m
[32m +						else{[m
[32m +							 Log.i(LOG_TAG,"is not saved on internal memory");[m
[32m +						}  [m
[32m +						}  [m
[32m +				[m
  [m
  				else{[m
[31m- 					warningMessage = (TextView) findViewById(R.id.warningmessage);[m
[31m- 					warningMessage.setText("Create a pet first!");[m
[32m+ 					Toast.makeText(SelectGame.this, "Create a pet first!", Toast.LENGTH_SHORT).show();[m
  [m
  				}[m
  			}[m
[1mdiff --git a/ReadyForAPetTesterTest/src/model/DogTest.java b/ReadyForAPetTesterTest/src/model/DogTest.java[m
[1mdeleted file mode 100644[m
[1mindex 1f79738..0000000[m
[1m--- a/ReadyForAPetTesterTest/src/model/DogTest.java[m
[1m+++ /dev/null[m
[36m@@ -1,38 +0,0 @@[m
[31m-package model;[m
[31m-[m
[31m-[m
[31m-import junit.framework.TestCase;[m
[31m-import Model.Dog;[m
[31m-[m
[31m-public class DogTest extends TestCase {[m
[31m-	Dog tdog = new Dog("olle",2,2,2);[m
[31m-	[m
[31m-	/*public DogTest() {[m
[31m-		[m
[31m-	}*/ [m
[31m-	[m
[31m-	[m
[31m-	protected void setUp() throws Exception{[m
[31m-		super.setUp();[m
[31m-		[m
[31m-		[m
[31m-		[m
[31m-	}[m
[31m-[m
[31m-	protected void tearDown() throws Exception {[m
[31m-		super.tearDown();[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * testMethodgetName Method [m
[31m-	 *  	[m
[31m-	 *  Test for the method getName in the class Dog [m
[31m-	 */[m
[31m-[m
[31m-	public void testMethodgetName () {[m
[31m-		assertEquals("olle",tdog.getName());}[m
[31m-	[m
[31m-[m
[31m-} [m
[31m-[m
[31m-[m
[1mdiff --git a/ReadyForAPetTesterTest/src/model/PetMoodTest.java b/ReadyForAPetTesterTest/src/model/PetMoodTest.java[m
[1mdeleted file mode 100644[m
[1mindex 48d2498..0000000[m
[1m--- a/ReadyForAPetTesterTest/src/model/PetMoodTest.java[m
[1m+++ /dev/null[m
[36m@@ -1,46 +0,0 @@[m
[31m-package model;[m
[31m-[m
[31m-import junit.framework.TestCase;[m
[31m-import Model.PetMood;[m
[31m-[m
[31m-public class PetMoodTest extends TestCase {[m
[31m-PetMood petmood = new PetMood();[m
[31m- [m
[31m- protected void setUp() throws Exception {[m
[31m-		super.setUp();[m
[31m-		PetMood.setFoodMood(2);[m
[31m-		PetMood.setWalkMood(3);[m
[31m-		PetMood.setPlayMood(0);[m
[31m-	}[m
[31m-[m
[31m-	protected void tearDown() throws Exception {[m
[31m-		super.tearDown();[m
[31m-	}[m
[31m-[m
[31m-/**[m
[31m- * Method for testing the method getfoodMood in PetMood[m
[31m- */[m
[31m-public void testInitialFoodMood(){[m
[31m-	assertEquals(2, PetMood.getFoodMood());[m
[31m-	}[m
[31m-/**[m
[31m- * Method for testing the method getPlayMood in PetMood[m
[31m- */[m
[31m-public void testInitialPlayMood(){[m
[31m-	assertEquals(0, PetMood.getPlayMood());[m
[31m-	}[m
[31m-/**[m
[31m- * Method for testing the method getWalkMood in PetMood[m
[31m- */[m
[31m-public void testInitialWalkMood(){[m
[31m-	assertEquals(3, PetMood.getWalkMood());[m
[31m-	}[m
[31m-/**[m
[31m- * Method for testing the method getSumMood in PetMood[m
[31m- */[m
[31m-public void testInitialSumMood(){[m
[31m-	assertEquals(5, PetMood.getSumMood());[m
[31m-	}[m
[31m-}[m
[31m-[m
[31m-[m
[1mdiff --git a/res/layout/activity_test.xml b/res/layout/activity_test.xml[m
[1mdeleted file mode 100644[m
[1mindex 3671a94..0000000[m
[1m--- a/res/layout/activity_test.xml[m
[1m+++ /dev/null[m
[36m@@ -1,16 +0,0 @@[m
[31m-<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"[m
[31m-    xmlns:tools="http://schemas.android.com/tools"[m
[31m-    android:layout_width="match_parent"[m
[31m-    android:layout_height="match_parent"[m
[31m-    android:paddingBottom="@dimen/activity_vertical_margin"[m
[31m-    android:paddingLeft="@dimen/activity_horizontal_margin"[m
[31m-    android:paddingRight="@dimen/activity_horizontal_margin"[m
[31m-    android:paddingTop="@dimen/activity_vertical_margin"[m
[31m-    tools:context=".Test" >[m
[31m-[m
[31m-    <TextView[m
[31m-        android:layout_width="wrap_content"[m
[31m-        android:layout_height="wrap_content"[m
[31m-        android:text="@string/hello_world" />[m
[31m-[m
[31m-</RelativeLayout>[m
\ No newline at end of file[m
[1mdiff --git a/src/edu/chl/dat255/sofiase/readyforapet/Dog.java b/src/edu/chl/dat255/sofiase/readyforapet/Dog.java[m
[1mdeleted file mode 100644[m
[1mindex 54174f2..0000000[m
[1m--- a/src/edu/chl/dat255/sofiase/readyforapet/Dog.java[m
[1m+++ /dev/null[m
[36m@@ -1,42 +0,0 @@[m
[31m-package edu.chl.dat255.sofiase.readyforapet;[m
[31m-[m
[31m-public class Dog implements Pet {[m
[31m-[m
[31m-	String name;[m
[31m-	[m
[31m-	public Dog(String name){[m
[31m-		this.name = name; [m
[31m-	}[m
[31m-	[m
[31m-	@Override[m
[31m-	public String eat(PetMood petMood) {[m
[31m-		int hungerCounter = petMood.getFoodMood();[m
[31m-		if(hungerCounter < 5){[m
[31m-			hungerCounter = hungerCounter + 1;[m
[31m-			petMood.setFoodMood(hungerCounter);[m
[31m-			return "Yummie!";[m
[31m-		}	[m
[31m-			else{[m
[31m-				return "I am full";[m
[31m-			}[m
[31m-		[m
[31m-		[m
[31m-	}[m
[31m-	[m
[31m-	@Override[m
[31m-	public String play(PetMood petMood) {[m
[31m-		int playCounter = petMood.getPlayMood();[m
[31m-		if(playCounter < 5){[m
[31m-			playCounter = playCounter + 1;[m
[31m-			petMood.setPlayMood(playCounter);[m
[31m-			return "Yeey! I want to play";[m
[31m-		}	[m
[31m-			else{[m
[31m-				return "I'm tired! I want to rest";[m
[31m-			}[m
[31m-		[m
[31m-		[m
[31m-	}[m
[31m-	[m
[31m-	[m
[31m-}[m
[1mdiff --git a/src/edu/chl/dat255/sofiase/readyforapet/MoodBarActivity.java b/src/edu/chl/dat255/sofiase/readyforapet/MoodBarActivity.java[m
[1mdeleted file mode 100644[m
[1mindex 921bab2..0000000[m
[1m--- a/src/edu/chl/dat255/sofiase/readyforapet/MoodBarActivity.java[m
[1m+++ /dev/null[m
[36m@@ -1,42 +0,0 @@[m
[31m-package edu.chl.dat255.sofiase.readyforapet;[m
[31m-[m
[31m-import android.app.Activity;[m
[31m-import android.os.Bundle;[m
[31m-import android.os.Handler;[m
[31m-import android.widget.ProgressBar;[m
[31m-[m
[31m-public class MoodBarActivity extends Activity{[m
[31m-[m
[31m-	private ProgressBar moodBar;[m
[31m-	private int moodStatus = 0;[m
[31m-	private Handler moodHandler = new Handler();[m
[31m-	private PetMood petmood = new PetMood();[m
[31m-[m
[31m-	protected void onCreate (Bundle savedInstanceState) {[m
[31m-[m
[31m-		super.onCreate(savedInstanceState);[m
[31m-		setContentView(R.layout.petactivity);[m
[31m-[m
[31m-		moodBar = (ProgressBar) findViewById(R.id.moodbar);[m
[31m-[m
[31m-		// Start lengthy operation in a background thread[m
[31m-		new Thread(new Runnable() {[m
[31m-			public void run() {[m
[31m-				while (moodStatus > 0) {[m
[31m-					moodStatus = petmood.getSumMood();[m
[31m-[m
[31m-					// Updating the progress bar[m
[31m-					moodHandler.post(new Runnable() {[m
[31m-						public void run() {[m
[31m-							moodBar.setProgress(moodStatus);[m
[31m-						}[m
[31m-					});[m
[31m-				}[m
[31m-			}[m
[31m-		}).start();[m
[31m-	}[m
[31m-[m
[31m-[m
[31m-}[m
[31m-[m
[31m-[m
[1mdiff --git a/src/edu/chl/dat255/sofiase/readyforapet/Pet.java b/src/edu/chl/dat255/sofiase/readyforapet/Pet.java[m
[1mdeleted file mode 100644[m
[1mindex 1d8b5be..0000000[m
[1m--- a/src/edu/chl/dat255/sofiase/readyforapet/Pet.java[m
[1m+++ /dev/null[m
[36m@@ -1,12 +0,0 @@[m
[31m-package edu.chl.dat255.sofiase.readyforapet;[m
[31m-[m
[31m-public interface Pet {[m
[31m-	 [m
[31m-	//public static void setName(){[m
[31m-		[m
[31m-	//}[m
[31m-	String eat(PetMood petMood);[m
[31m-	[m
[31m-	String play(PetMood petMood);[m
[31m-	[m
[31m-}[m
[1mdiff --git a/src/edu/chl/dat255/sofiase/readyforapet/PetMood.java b/src/edu/chl/dat255/sofiase/readyforapet/PetMood.java[m
[1mdeleted file mode 100644[m
[1mindex 205ddf8..0000000[m
[1m--- a/src/edu/chl/dat255/sofiase/readyforapet/PetMood.java[m
[1m+++ /dev/null[m
[36m@@ -1,41 +0,0 @@[m
[31m-package edu.chl.dat255.sofiase.readyforapet;[m
[31m-[m
[31m-[m
[31m-public class PetMood {[m
[31m-[m
[31m-	private static int foodMood = 2;[m
[31m-	private static int playMood = 2;[m
[31m-[m
[31m-	public PetMood(){	[m
[31m-[m
[31m-[m
[31m-	}[m
[31m-[m
[31m-	// Sets the mood of the pet depending on how much it has eaten.[m
[31m-	public void setFoodMood (int foodnumber){[m
[31m-		foodMood = foodnumber;[m
[31m-	}[m
[31m-[m
[31m-	// Sets the mood of the pet depending on how much it has played.[m
[31m-	public void setPlayMood (int playnumber){[m
[31m-		playMood = playnumber;[m
[31m-	}[m
[31m-[m
[31m-	// Makes it possible for the pet to know how much the pet has already eaten.[m
[31m-	public int getFoodMood (){[m
[31m-		return foodMood;[m
[31m-	}[m
[31m-[m
[31m-	// Makes it possible for the pet to know how much it has already played.[m
[31m-	public int getPlayMood (){[m
[31m-		return playMood;[m
[31m-	}[m
[31m-[m
[31m-	// The sum of the mood of the pet that is shown in a moodbar.[m
[31m-	//Later, add:[m
[31m-	//return foodMood + walkMood + playMood + sleepMood[m
[31m-	public int getSumMood (){[m
[31m-		return foodMood + playMood;[m
[31m-	}[m
[31m-[m
[31m-}[m
