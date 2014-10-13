package com.example.eqiqcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BirthDate extends Activity {
	public EditText day,month,year;
	private Button start;
	private static int currentYear=2014;
	int birthYear,age;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.birthdate);
		
		day=(EditText)findViewById(R.id.editText1);
		month=(EditText)findViewById(R.id.editText2);
		year=(EditText)findViewById(R.id.editText3);
		start=(Button)findViewById(R.id.button1);
		
		
		//Toast.makeText(getApplicationContext(), bdate, Toast.LENGTH_SHORT).show();
		
		
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if ((day.getText().toString()).equals("") || (month.getText().toString()).equals("") || (year.getText().toString()).equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please Enter Your Birthdate", Toast.LENGTH_SHORT).show();
				}
				else{
					// TODO Auto-generated method stub
					String bdate=(day.getText().toString()+"-"+month.getText().toString()+"-"+year.getText().toString()).toString();
					birthYear=Integer.parseInt(year.getText().toString());
					age=currentYear-birthYear;
				//Toast.makeText(getApplicationContext(),"age is"+age, Toast.LENGTH_SHORT).show();
					PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Age",Integer.toString(age)).commit();
				//Toast.makeText(getApplicationContext(), bdate, Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(BirthDate.this,QuizIq.class);
					startActivity(intent);
					finish();
				}
			}
		});
	}
	
	

}
