package com.example.tani;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Update extends Activity{

	static  SQLiteDatabase mydb;
	private Integer[] data;
	private String i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);
		Database hlpr = new Database(getApplicationContext());
		mydb = hlpr.getWritableDatabase();
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		try{
			Cursor cr = mydb.rawQuery("Select * From score Order By id desc", null);
			cr.moveToFirst();
			if(cr.getCount() > 0){
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
				for(int cnt = 0; cnt < cr.getCount(); cnt++){

					adapter.add(cr.getString(1) );
					cr.moveToNext();
					spinner.setAdapter(adapter);
				}
			}else spinner.setAdapter(null);
		}finally{
			mydb.close();

		}
		Button Ibtn = (Button)findViewById(R.id.button1);
        Ibtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v){
            	update();

            }
        });
	}
	private void update(){
		Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
		Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
		Spinner spinner3 = (Spinner)findViewById(R.id.spinner3);
		Spinner spinner4 = (Spinner)findViewById(R.id.spinner4);
		Database hlpr = new Database(getApplicationContext());
		mydb = hlpr.getWritableDatabase();
		String sub = (String)spinner1.getSelectedItem();
		String A = (String)spinner2.getSelectedItem();
		String B = (String)spinner3.getSelectedItem();
		String S = (String)spinner4.getSelectedItem();
		ContentValues values = new ContentValues();
		values.put("a", A);
		values.put("b", B);
		values.put("s", S);
		mydb.update("score", values, "sub =sub", null);
		mydb.close();
		 Toast toast = Toast.makeText(this, "更新完了しました。",Toast.LENGTH_LONG);
		 toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show ();



	}

}
