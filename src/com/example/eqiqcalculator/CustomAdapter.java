package com.example.eqiqcalculator;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter {
	private Context context;
	private ArrayList<String> list;
	

	public CustomAdapter(Context context,List objects) {
		super(context, R.layout.row, objects);
		this.context=context;
		this.list=(ArrayList<String>) objects;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position,View view, ViewGroup parent)
	{
		LayoutInflater inflator= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView=inflator.inflate(R.layout.row,null);
		TextView id=(TextView)rowView.findViewById(R.id.id);
		TextView verify=(TextView)rowView.findViewById(R.id.verify);
		id.setText(Integer.toString(position));
		verify.setText(list.get(position));
		return rowView;
	}

}
