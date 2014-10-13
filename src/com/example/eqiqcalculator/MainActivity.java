package com.example.eqiqcalculator;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.support.v7.app.ActionBar;

public class MainActivity extends Activity {
 private Button intro;
 private Button attempt;
 private Button help;
 private Button exit;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);

		ActionBar actionbar=getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		
		intro=(Button)findViewById(R.id.button1);
		attempt=(Button)findViewById(R.id.button2);
		help=(Button)findViewById(R.id.button3);
		exit=(Button)findViewById(R.id.button4);
		
		
		intro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
				builder.setMessage("Select Type")
						.setTitle("Select")
						.setPositiveButton("EQ",new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent=new Intent(MainActivity.this,EQIntro.class);
								startActivity(intent);
								
								
								
							}
						})
						.setNegativeButton("IQ",new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent=new Intent(MainActivity.this,IQIntro.class);
								startActivity(intent);
								
								
							}
							
						});
						AlertDialog dialog=builder.create();
						dialog.show();
				
			}
		});
		
		//preferences = getSharedPreferences("PREF_NAME", MODE_PRIVATE);
		//Intent intent;
		
		attempt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
				builder.setMessage("Select Quiz Type")
						.setTitle("Select Quiz")
						.setPositiveButton("EQ",new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent=new Intent(MainActivity.this,QuizEq.class);
								startActivity(intent);
								finish();
								
								
							}
						})
						.setNegativeButton("IQ",new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent=new Intent(MainActivity.this,BirthDate.class);
								startActivity(intent);
								finish();
								
							}
							
						});
				AlertDialog dialog=builder.create();
				dialog.show();
		
			}
		});
		
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
				builder.setMessage("Do you want to Exit??")
						.setTitle("Quit Application")
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								finish();
								
								
							}
						})
						.setNegativeButton("No",new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent=new Intent(MainActivity.this,MainActivity.class);
								startActivity(intent);
								finish();
								
								
							}
							
						});
				AlertDialog dialog=builder.create();
				dialog.show();
		
			
		
				
			}
		});
		
		help.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(MainActivity.this,Help.class);
				startActivity(intent);
				
			}
		});
	}
	
	
}
