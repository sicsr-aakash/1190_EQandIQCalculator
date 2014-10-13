package com.example.eqiqcalculator;

import android.app.Activity;
import android.os.Bundle;

public class EQIntro extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.introductioneq);
	}
	/*@Override 
	public void onBackPressed()
	{
	    super.onBackPressed();

	    if (tick != null){
	        if(tick.isPlaying())
	            tick.stop();

	        tick.release();
	        finish();
	    }
	}*/
	
	
}
