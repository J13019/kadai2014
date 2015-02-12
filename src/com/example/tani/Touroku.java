package com.example.tani;

import android.app.Activity;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Touroku extends Activity{
	static  SQLiteDatabase mydb;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.touroku);

	        Button Ibtn = (Button)findViewById(R.id.button1);
	        Ibtn.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // Sub 画面を起動

	            	add();


	            }
			});


		}


private void add() throws SQLException{

	// TODO 自動生成されたメソッド・スタブ
	EditText editText = (EditText)findViewById(R.id.editText1);
	Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
	Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
	Spinner spinner3 = (Spinner)findViewById(R.id.spinner3);
	Database db = new Database(this);
	SQLiteDatabase sdb = db.getWritableDatabase();
	String sub = editText.getText().toString();
	String A = (String)spinner1.getSelectedItem();
	String B = (String)spinner2.getSelectedItem();
	String S = (String)spinner3.getSelectedItem();




	ContentValues values = new ContentValues();
	values.put("sub", sub);
	values.put("a", A);
	values.put("b", B);
	values.put("s", S);
	long rowID = sdb.insert("score",null, values);
	editText.setText("");
	if(rowID == -1){
		db.close();
	}db.close();

	 Toast toast = Toast.makeText(this, "登録完了しました。",Toast.LENGTH_LONG);
	 toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show ();

}
}
