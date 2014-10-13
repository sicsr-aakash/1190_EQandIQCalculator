package com.example.eqiqcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ResultEQ extends Activity {
	private EditText result;
	private Button back;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resulteq);
		
		result=(EditText)findViewById(R.id.editText1);
		back=(Button)findViewById(R.id.button1);
		result.setText(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Grade",""));
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent);
				finish();
				
			}
		});
	}
}
