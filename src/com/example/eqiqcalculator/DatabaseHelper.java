package com.example.eqiqcalculator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	private Context context;
	//private AssetManager am;
	//private InputStream is;
	public static String DB_NAME = "EQIQ_database.db"; 
	private String DB_PATH;  
	
	public DatabaseHelper(Context context,String name,CursorFactory factory,int version){
		super(context,name,factory,version);
		this.context = context;
		DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
		try {
			copyDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// TODO Auto-generated method stub
	
	
}
private void copyDataBase() throws IOException {  
	  
	  InputStream myInput = context.getAssets().open(DB_NAME);  
	  String outFileName = DB_PATH + DB_NAME;  
	  OutputStream myOutput = new FileOutputStream(outFileName);  
	  byte[] buffer = new byte[1024];  
	  int length;  
	  while ((length = myInput.read(buffer)) > 0) {  
	   myOutput.write(buffer, 0, length);  
	  }  
	  
	  // Close the streams  
	  myOutput.flush();  
	  myOutput.close();  
	  myInput.close();  
	  
	 }  


@Override
public void onCreate(SQLiteDatabase sql) {
	/*try
	{
		am = context.getAssets();
		is = am.open("Question_Paper_EQ.txt");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}*/
	
	// TODO Auto-generated method stub
	/*String EQ="CREATE TABLE tbl_EQ(id int(3),Question text,Answer1 text,Answer2 text,Answer3 text,Answer4 text,Answer5 text)";
	sql.execSQL(EQ);
	String IQ="CREATE TABLE tbl_IQ(id int(3),Question text,Answer1 text,Answer2 text,Answer3 text,Answer4 text)";
	sql.execSQL(IQ);*/
}
public void onEQRetrieve(int id,String question,String answer_1,String answer_2,String answer_3,String answer_4,String answer_5)
{}

public void onIQRetrieve(int id,String question,String answer_1,String answer_2,String answer_3,String answer_4)
{
	
}


public void insertEQDB(int id,String question,String answer_1,String answer_2,String answer_3,String answer_4,String answer_5)
{
	/*ContentValues values=new ContentValues();
	for(id=1;id<=95;id++)
	values.put("id", id);
	values.put("Question","I have tended to achieve the things with hard work.");
	values.put("Answer1","Completely Agree");
	values.put("Answer2","Partially Agree");
	values.put("Answer3","partially disAgree");
	values.put("Answer4","Completely Disagree");
	values.put("Answer5","Not sure");
	SQLiteDatabase eqdb=this.getWritableDatabase();
	eqdb.insert("tbl_EQ", null, values);*/
	
}

public void insertIQDB(int id,String question,String answer_1,String answer_2,String answer_3,String answer_4)
{
	/*ContentValues values=new ContentValues();
	values.put("id", id);
	values.put("Question","");
	values.put("Answer1","");
	values.put("Answer2","");
	values.put("Answer3","");
	values.put("Answer4","");
	
	SQLiteDatabase iqdb=this.getWritableDatabase();
	iqdb.insert("tbl_IQ", null, values);*/
}
}