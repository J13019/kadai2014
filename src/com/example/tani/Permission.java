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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Permission extends Activity{
	static  SQLiteDatabase mydb;
	private Integer[] data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.permission);

		Button Ibtn = (Button)findViewById(R.id.button1);
	        Ibtn.setOnClickListener(new OnClickListener() {
	            public void onClick(View v){
	            	add();
	            	view();

	            }
	        });
	}
	private void add() throws SQLException{

		// TODO 自動生成されたメソッド・スタブ
		EditText editText = (EditText)findViewById(R.id.editText1);
		Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
		Database3 db3 = new Database3(this);
		SQLiteDatabase sdb3 = db3.getWritableDatabase();
		String sub = editText.getText().toString();
		String per = (String)spinner1.getSelectedItem();



		ContentValues values = new ContentValues();
		values.put("sub", sub);
		values.put("per", per);

		long rowID = sdb3.insert("tani3",null, values);
		editText.setText("");
		if(rowID == -1){
			db3.close();
		}db3.close();
	}

	private void view(){

		Database3 hlpr = new Database3(getApplicationContext());
		mydb = hlpr.getWritableDatabase();
		ListView listView = (ListView)findViewById(R.id.listView1);
		try{
			Cursor cr = mydb.rawQuery("Select * From tani3 Order By id desc", null);
			cr.moveToFirst();
			if(cr.getCount() > 0){
				data = new Integer[cr.getCount()];
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
				for(int cnt = 0; cnt < cr.getCount(); cnt++){
					data[cnt] = cr.getInt(0);
					adapter.add("科目名:" + cr.getString(1) + ", 持ち込み許可:" + cr.getString(2));
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
			Database3 db=new Database3(this);
			SQLiteDatabase sdb=db.getWritableDatabase();
			final Toast toast_s=Toast.makeText(this, "削除しました。", Toast.LENGTH_LONG);
			final Toast toast_f=Toast.makeText(this, "削除できませんでした。", Toast.LENGTH_LONG);
			int ret;
			try{
				ret = sdb.delete("tani3", "id = "+ id, null);
				view();
			}finally{
				db.close();
			}
			toast_s.setGravity(Gravity.CENTER, 0, 0);
			toast_s.show();

		}
	}
