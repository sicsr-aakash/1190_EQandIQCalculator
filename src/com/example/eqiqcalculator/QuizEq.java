package com.example.eqiqcalculator;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
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

public class QuizEq extends Activity{
	static int times = 0;
	String KEY_SESSION_NAME="sessionStatus";
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	private EditText question;
	private EditText no;
	private Button next,stop;
	private RadioButton answer1,answer2,answer3,answer4,answer5;
	private RadioGroup answers;
	/*String DB_PATH="/data/data/com.example.eqiqcalculator/databases";
	String DB_NAME="EQIQ_database";*/
	SQLiteDatabase database;
	ArrayList<String> data=new ArrayList<String>();
	Cursor cursor;
	private String nul,grade;
	private int min=1,max=95,id,i=1,j;
	private double marks;
	Random r;
	public ArrayList<Integer> idArray;
	private ArrayList<String> checklist;
	private int answerId;
	//private int[] storedArray;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_eq);
		
		preferences = getSharedPreferences("PREF_NAME", MODE_PRIVATE);
		editor = preferences.edit();
		
		editor.putBoolean(KEY_SESSION_NAME, true);
		editor.commit();
		checklist=new ArrayList<String>();
		
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
		answer5=(RadioButton)findViewById(R.id.radio4);
		
		idArray = new ArrayList<Integer>();
		//Toast.makeText(getApplicationContext(), "application", Toast.LENGTH_LONG).show();
		DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext(),DatabaseHelper.DB_NAME, null, 1);
		database=dbHelper.getReadableDatabase();
		//Toast.makeText(getApplicationContext(), "get DB", Toast.LENGTH_SHORT).show();
		j=0;
		boolean flag = true;
		id=generateRandom();
		//Toast.makeText(getApplicationContext(), "get id="+id, Toast.LENGTH_SHORT).show();
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
        
        //Toast.makeText(getApplicationContext(), "first retrive", Toast.LENGTH_SHORT).show();
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(QuizEq.times < 25)
				{
					id=generateRandom();
				
					boolean flag = true;
					answerId=answers.getCheckedRadioButtonId();
					//Toast.makeText(getApplicationContext(), "Radio="+answerId, Toast.LENGTH_SHORT).show();
					if(answerId==-1)
					{
						//calculate(nul);
						checklist.add("Unanswered");
						
					}
					else
					{
						/*View radioButton = answers.findViewById(id);
					    //int radioId = answes.indexOfChild(radioButton);
					    //RadioButton btn = (RadioButton) answers.getChildAt(radioId);
						RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup1);
						//RadioButton btn = (RadioButton)findViewById(radioId);
					    //String selection =btn.getText().toString();*/
						String selection=((RadioButton)findViewById(answers.getCheckedRadioButtonId())).getText().toString();	
					    calculate(selection);
					    Toast.makeText(getApplicationContext(), "Radio is"+selection,Toast.LENGTH_SHORT);
						checklist.add("Answered");
					}
					do{
		        	
							if (!idArray.contains(id)) 
							{
								//storedArray[j]=idArray[j];
								onRetrive(id);
								idArray.add(id);
								flag = false;
								QuizEq.times++;
							}
							else 
							{
								id=generateRandom();
							}
					
					}while (flag);
	        
				}
				else{
					Toast.makeText(getApplicationContext(), "You have finished the quiz", Toast.LENGTH_SHORT).show();	
					getGrade(marks);
				//Toast.makeText(getApplicationContext(), "Your score is"+marks, Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(getApplicationContext(),CheckList.class);
				Bundle bundle=new Bundle();
				bundle.putStringArrayList("CheckList", checklist);
				intent.putExtras(bundle);
				startActivity(intent);
				QuizEq.times=0;
				finish();
				}
			}
		});
		
		
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder builder=new AlertDialog.Builder(QuizEq.this)
				.setTitle("Do you really want to quit?")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(QuizEq.this,MainActivity.class);
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
			cursor=database.rawQuery("SELECT * FROM EQ where id="+id, null);
			no.setText(Integer.toString(i));
			
			//cursor=database.rawQuery("SELECT * FROM EQ where id="+id, null);
			if(cursor.moveToFirst())
			{
				question.setText(cursor.getString(1).toString());
				answer1.setText(cursor.getString(2).toString());
				answer2.setText(cursor.getString(3).toString());
				answer3.setText(cursor.getString(4).toString());
				answer4.setText(cursor.getString(5).toString());
				answer5.setText(cursor.getString(6).toString());
				answers.clearCheck();
				cursor.close();
			}
			i++;
		}	
	}
	public void calculate(String selection)
	{
		cursor=database.rawQuery("SELECT * FROM EQ where id="+id, null);
		if(cursor.moveToFirst())
		{
			if(selection.equals(cursor.getString(2).toString()))
			{
				marks=marks+1;
			}
			else if(selection.equals(cursor.getString(3).toString()))
			{
				marks=marks+0.75;
			}
			else if(selection.equals(cursor.getString(4).toString()))
			{
				marks=marks+0.50;
			}
			else if(selection.equals(cursor.getString(5).toString()))
			{
				marks=marks+0.25;
			}
			else if(selection.equals(cursor.getString(6).toString()))
			{
				marks=marks+0;
			}
			else
			{
				marks=marks+0;
			}
		}
	}
	public void getGrade(double marks)
	{
		if(marks>=0 && marks<=8)
		{
			grade="Below Average";
			PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Grade", "Below Avarage").commit(); 
		}
		else if(marks>=9 && marks<=15)
		{
			grade="Average";
			PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Grade", "Avarage").commit(); 
		}
		else 
		{
			grade="Above Average";
			PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Grade", "Above Avarage").commit(); 
		}
	}
} 
	
	
