package edu.chl.dat255.sofiase.readyforapet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;

public class CreatePet extends Activity {
	
	public final static String EXTRA_MESSAGE = "edu.chl.dat255.sofiase.readyforapet.NAME";
	//unique if the app interacts with other apps
	String name; 
	static Dog dog; 
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.createpet);
		dog = new Dog(name);
		
	}

	//HONEYCOMB - se android trainer: Starting another activity
	//skippade onOptionsItemsSelected(MenuItem item)


	public void saveSettings(View v){
		Intent intent = new Intent(CreatePet.this, PetActivity.class); //förut stod displayname men tror det är petactivity!
		EditText setName = (EditText) findViewById(R.id.edit_pet_name);
		name = setName.getText().toString();
		//ändra här för att slippa putta name vidare
		intent.putExtra(EXTRA_MESSAGE, name);
		startActivity(intent);
	}
	
	

	public static Pet getPet(){
		return dog;
	}

}
