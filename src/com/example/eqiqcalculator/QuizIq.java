package com.example.eqiqcalculator;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizIq extends Activity{
	String KEY_SESSION_NAME="sessionStatus";
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	static int times = 0;
	private EditText question;
	private EditText no;
	private Button next,stop;
	private RadioButton answer1,answer2,answer3,answer4;
	private RadioGroup answers;
	SQLiteDatabase database;
	ArrayList<String> data=new ArrayList<String>();
	Cursor cursor;
	private String nul,pref;
	private int min=1,max=52,id,i=1,j,prevId;
	private double marks,iq,age;
	Random r;
	public ArrayList<Integer> idArray;
	private ArrayList<String> iqlist;
	private int answerId;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_iq);
		
		
		preferences = getSharedPreferences("PREF_NAME", MODE_PRIVATE);
		editor = preferences.edit();
		
		editor.putBoolean(KEY_SESSION_NAME, true);
		editor.commit();
		
		iqlist=new ArrayList<String>();
		
		question=(EditText)findViewById(R.id.editText2);
		no=(EditText)findViewById(R.id.editText1);
		next=(Button)findViewById(R.id.button4);
		//previous=(Button)findViewById(R.id.button2);
		stop=(Button)findViewById(R.id.button1);
		//submit=(Button)findViewById(R.id.button3);
		answers=(RadioGroup)findViewById(R.id.radioGroup1);
		answer1=(RadioButton)findViewById(R.id.radio0);
		answer2=(RadioButton)findViewById(R.id.radio1);
		answer3=(RadioButton)findViewById(R.id.radio2);
		answer4=(RadioButton)findViewById(R.id.radio3);
		
		idArray = new ArrayList<Integer>();
		
		DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext(),DatabaseHelper.DB_NAME, null, 1);
		database=dbHelper.getReadableDatabase();
		
		j=0;
		boolean flag = true;
		id=generateRandom();
		
		do{
        	
				if (!idArray.contains(id)) 
				{
					//storedArray[j]=idArray[j];
					this.onRetrive(id);
					idArray.add(id);
					flag = false;
				}
				else 
				{
					id=generateRandom();
				}
			
			}while (flag);
		
			next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(QuizIq.times < 25)
				{
					prevId=id;
					id=generateRandom();
				
					boolean flag = true;
					answerId=answers.getCheckedRadioButtonId();
					//Toast.makeText(getApplicationContext(), "Radio="+answerId, Toast.LENGTH_SHORT).show();
					if(answerId==-1)
					{
						//calculate(nul);
						iqlist.add("Unanswered");
						
					}
					else
					{
						String selection=((RadioButton)findViewById(answers.getCheckedRadioButtonId())).getText().toString();	
					    calculateMarks(selection);
					    
					    //Toast.makeText(getApplicationContext(), "Radio is"+selection,Toast.LENGTH_SHORT);
						iqlist.add("Answered");
					}
					do{
		        	
							if (!idArray.contains(id)) 
							{
								onRetrive(id);
								idArray.add(id);
								flag = false;
								QuizIq.times++;
							}
							else 
							{
								id=generateRandom();
							}
					
					}while (flag);
	        
				}
				else{
					Toast.makeText(getApplicationContext(), "You have finished the quiz", Toast.LENGTH_SHORT).show();	
					//getGrade(marks);
					calculateIq(marks);
					Intent intent=new Intent(getApplicationContext(),Iqlist.class);
					Bundle bundle=new Bundle();
					bundle.putStringArrayList("IqList",iqlist);
					intent.putExtras(bundle);
					startActivity(intent);
					QuizIq.times=0;
					finish();
				}
			}
		});
		
			stop.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					AlertDialog.Builder builder=new AlertDialog.Builder(QuizIq.this)
					.setTitle("Do you really want to quit?")
					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							Intent intent=new Intent(QuizIq.this,MainActivity.class);
							startActivity(intent);
							finish();
						}
					})
					.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							
						}
					});
					AlertDialog dialog=builder.create();
					dialog.show();
					
							
				}
			});
	
	}
	
	public int generateRandom()
	{
		r=new Random();
		int id=r.nextInt(max-min)+min;
		return id;
	}
	
	public void onRetrive(int id)
	{
		if(i<=25)
		{
			cursor=database.rawQuery("SELECT * FROM IQ where id="+id, null);
			no.setText(Integer.toString(i));
			
			//cursor=database.rawQuery("SELECT * FROM EQ where id="+id, null);
			if(cursor.moveToFirst())
			{
				question.setText(cursor.getString(1).toString());
				answer1.setText(cursor.getString(2).toString());
				answer2.setText(cursor.getString(3).toString());
				answer3.setText(cursor.getString(4).toString());
				answer4.setText(cursor.getString(5).toString());
				answers.clearCheck();
				cursor.close();
			}
			i++;
		}	
	}
	public void calculateMarks(String selection)
	{
		cursor=database.rawQuery("SELECT * FROM IQ where id="+prevId, null);
		if(cursor.moveToFirst())
		{
			if(selection.equals(cursor.getString(6).toString()))
			{
				marks=marks+1;
			}
			else
			{
				marks=marks+0;
			}
		}
	}
	
	public double calculateIq(double marks)
	{
		pref=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Age","");
		Toast.makeText(getApplicationContext(), pref, Toast.LENGTH_LONG).show();;
		age=Integer.parseInt(pref);
		iq=((marks/age)*100)+25;
		iq=(int)iq;
		PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("IQ",Double.toString(iq)).commit();
		return iq;
	}
}
