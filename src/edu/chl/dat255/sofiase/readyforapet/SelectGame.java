package edu.chl.dat255.sofiase.readyforapet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class SelectGame extends Activity {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.selectgame);

		//Knappen continue reagerar vid klick och startar PetActivity
		Button continuePreviousGame = (Button) findViewById(R.id.continuegame);
		continuePreviousGame.setOnClickListener(new OnClickListener() {

			public void onClick (View v){
				startActivity(new Intent(SelectGame.this, PetActivity.class));
			}

		}
		);
		

		//Detta Šr fšr att knappen Create Pet ska skickas till aktiviteten CreatePet
		Button createNewPet = (Button) findViewById(R.id.createnewpet);
		createNewPet.setOnClickListener(new OnClickListener() {

			public void onClick (View v){
				startActivity(new Intent(SelectGame.this, CreatePet.class));
			}
		}
		);
	}
}
