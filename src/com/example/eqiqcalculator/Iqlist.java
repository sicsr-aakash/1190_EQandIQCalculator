package com.example.eqiqcalculator;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class Iqlist extends ActionBarActivity {
	private ListView listview;
	private Button submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_iqlist);

		Bundle bundle=getIntent().getExtras();
		ArrayList<String> iqlist=bundle.getStringArrayList("IqList");
		listview=(ListView)findViewById(R.id.iqlist);
		CustomAdapter ca=new CustomAdapter(getApplicationContext(),iqlist);
		listview.setAdapter(ca);
		
		submit=(Button)findViewById(R.id.submit);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),ResultIq.class);
				startActivity(intent);
				finish();
			}
		});
	}
		
	

}
