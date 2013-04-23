package edu.chl.dat255.sofiase.readyforapet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreatePet extends Activity {
	
	public final static String EXTRA_MESSAGE = "edu.chl.dat255.sofiase.readyforapet.NAME";
	//unique if the app interacts with other apps

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.createpet);

	}

	//HONEYCOMB - se android trainer: Starting another activity
	//skippade onOptionsItemsSelected(MenuItem item)


	public void saveSettings(View v){
		Intent intent = new Intent(CreatePet.this, PetActivity.class); //fšrut stod displayname men tror det Šr petactivity!
		EditText setName = (EditText) findViewById(R.id.edit_pet_name);
		String name = setName.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, name);
		startActivity(intent);
	}



}
