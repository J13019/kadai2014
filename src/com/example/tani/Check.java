package com.example.tani;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Check extends Activity{
	static  SQLiteDatabase mydb;
	private Integer[] data;
	private int i =0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check);
		view();
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
					cr.getString(0);
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
            	add();
            	view();

            }
        });
	}
	private void add() throws SQLException{

		Database hlpr = new Database(getApplicationContext());
		StringBuilder builder=new StringBuilder();
		mydb = hlpr.getWritableDatabase();

		Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);

		String sub = (String)spinner1.getSelectedItem();
		DatePicker datePicker1 = (DatePicker)findViewById(R.id.datePicker1);
        String day = builder.append(datePicker1.getYear()+"年").append(datePicker1.getMonth()+1+"月").append(datePicker1.getDayOfMonth()+"日").toString();

		ContentValues values = new ContentValues();

		values.put("day", day);
		values.put("sub", sub);


		long rowID = mydb.insert("day",null, values);

		if(rowID == -1){
			hlpr.close();
		}hlpr.close();

	}
	private void view(){
		Database hlpr = new Database(getApplicationContext());
		mydb = hlpr.getWritableDatabase();
		ListView listView = (ListView)findViewById(R.id.listView1);
		try{
			Cursor cr = mydb.rawQuery("Select * From day", null);
			cr.moveToFirst();
			if(cr.getCount() > 0){
				data = new Integer[cr.getCount()];
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
				for(int cnt = 0; cnt < cr.getCount(); cnt++){
					data[cnt] = cr.getInt(0);
					adapter.add("科目名:" + cr.getString(1) + ", 日付:" + cr.getString(2));
					cr.moveToNext();
					listView.setAdapter(adapter);
				}
			}else listView.setAdapter(null);
		}finally{
			mydb.close();
	}
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
			public boolean onItemLongClick(AdapterView<?>parent, View view, int position, long id){
			delete(data[(int)id]);
			return false;
	}
	});
	}
		private void delete(int id){
			Database db=new Database(this);
			SQLiteDatabase sdb=db.getWritableDatabase();
			final Toast toast_s=Toast.makeText(this, "削除しました。", Toast.LENGTH_LONG);
			final Toast toast_f=Toast.makeText(this, "削除できませんでした。", Toast.LENGTH_LONG);
			int ret;
			try{
				ret = sdb.delete("day", "id = "+ id, null);
				view();
			}finally{
				db.close();
			}
			toast_s.setGravity(Gravity.CENTER, 0, 0);
			toast_s.show();

		}
}

