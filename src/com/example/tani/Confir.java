package com.example.tani;



import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Confir extends Activity{
	static  SQLiteDatabase mydb;
	private Integer[] data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confir);
		view();



	}



	private void view(){

		Database hlpr = new Database(getApplicationContext());
		mydb = hlpr.getWritableDatabase();
		ListView listView = (ListView)findViewById(R.id.listView1);
		try{
			Cursor cr = mydb.rawQuery("Select * From score Order By id desc", null);
			cr.moveToFirst();
			if(cr.getCount() > 0){
				data = new Integer[cr.getCount()];
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
				for(int cnt = 0; cnt < cr.getCount(); cnt++){
					data[cnt] = cr.getInt(0);
					adapter.add("科目名:" + cr.getString(1) + ", A:" + cr.getString(2) + ", B:"+cr.getString(3)+ ", 合否:"+cr.getString(4));
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
				ret = sdb.delete("score", "id = "+ id, null);
				view();
			}finally{
				db.close();
			}

			toast_s.setGravity(Gravity.CENTER, 0, 0);
			toast_s.show();



		    }
		}

