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

		/*Det nedan antar jag att vi behšver gšra fšr att kunna fšlja knappen create pet!?
		Button continePrevoisGame = (Button) findViewById(R.id.createnewpet);
		createNewPet.setOnClickListener(new OnClickListener() {

			public void onClick (View v){
				startActivity(new Intent(SelectGame.this, CreatePet.class));
			}

}*/

		//Det nedan antar jag att vi behšver gšra fšr att kunna fšlja knappen create pet!?
		Button createNewPet = (Button) findViewById(R.id.createnewpet);
		createNewPet.setOnClickListener(new OnClickListener() {

			public void onClick (View v){
				startActivity(new Intent(SelectGame.this, CreatePet.class));
			}
		}
				);
	}

}
