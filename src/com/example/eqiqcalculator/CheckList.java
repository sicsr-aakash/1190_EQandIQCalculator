package com.example.eqiqcalculator;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
//import com.example.eqiqcalculator.QuizEq;

public class CheckList extends ActionBarActivity {

	public int marks=0,ques,answerId;
	public String grade;
	private ListView listview;
	private Button submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checklist);
		Bundle bundle=getIntent().getExtras();
		ArrayList<String> checklist=bundle.getStringArrayList("CheckList");
		listview=(ListView)findViewById(R.id.checklist);
		CustomAdapter ca=new CustomAdapter(getApplicationContext(),checklist);
		listview.setAdapter(ca);
		
		submit=(Button)findViewById(R.id.button1);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),ResultEQ.class);
				startActivity(intent);
				finish();
				
			}
		});
	}

	

}
