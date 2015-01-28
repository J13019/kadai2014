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
import android.widget.Toast;

public class Check extends Activity{
	static  SQLiteDatabase mydb;
	private Integer[] data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check);

		Button Ibtn = (Button)findViewById(R.id.button1);
        Ibtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	add();
            	view();


            }
        });

	}
	private void add() throws SQLException{

		// TODO 自動生成されたメソッド・スタブ
		EditText editText = (EditText)findViewById(R.id.editText1);
		EditText editText2 = (EditText)findViewById(R.id.editText2);
		Databese2 db2 = new Databese2(this);
		SQLiteDatabase sdb2 = db2.getWritableDatabase();
		String sub = editText.getText().toString();
		String day = editText2.getText().toString();


		ContentValues values = new ContentValues();
		values.put("sub", sub);
		values.put("day", day);

		long rowID = sdb2.insert("tani2",null, values);
		editText.setText("");
		if(rowID == -1){
			db2.close();
		}db2.close();
	}

	private void view(){

		Databese2 hlpr = new Databese2(getApplicationContext());
		mydb = hlpr.getWritableDatabase();
		ListView listView = (ListView)findViewById(R.id.listView1);
		try{
			Cursor cr = mydb.rawQuery("Select * From tani2 Order By id desc", null);
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
			Databese2 db=new Databese2(this);
			SQLiteDatabase sdb=db.getWritableDatabase();
			final Toast toast_s=Toast.makeText(this, "削除しました。", Toast.LENGTH_LONG);
			final Toast toast_f=Toast.makeText(this, "削除できませんでした。", Toast.LENGTH_LONG);
			int ret;
			try{
				ret = sdb.delete("tani2", "id = "+ id, null);
				view();
			}finally{
				db.close();
			}
			toast_s.setGravity(Gravity.CENTER, 0, 0);
			toast_s.show();
			
		}
	}

