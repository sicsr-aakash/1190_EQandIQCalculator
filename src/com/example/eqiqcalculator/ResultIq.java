package com.example.eqiqcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ResultIq extends Activity{
	
	private EditText result;
	private Button back;
	String iq;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultiq);
		result=(EditText)findViewById(R.id.editText1);
		back=(Button)findViewById(R.id.button1);
		iq=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("IQ","");
		result.setText(iq);
		back.setOnClickListener(new OnClickListener() {
						
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ResultIq.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

}
