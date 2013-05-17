package edu.chl.dat255.sofiase.readyforapet;

import java.io.Serializable;

import android.app.Activity;
import android.os.Bundle;

public class Settings extends Activity implements Serializable{
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	/**
	 * onCreate method
	 * 
	 * @param savedInstanceState - Bundle
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView(R.layout.settings);
	}


}
