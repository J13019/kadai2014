package com.example.tani;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databese2 extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "tani.db2";
	private static final int DATABASE_VERSION = 2;
	private static final String TABLE_NAME="tani2";
	private static final String ID ="id";
	private static final String SUBJECT ="sub";
	private static final String DAY = "day";

	Databese2(Context context){


		super(context,DATABASE_NAME,null,DATABASE_VERSION);

	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		String query="create table "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+SUBJECT+" TEXT,"+DAY+" TEXT);";
		db.execSQL(query);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("drop table if exists " + TABLE_NAME);
		onCreate(db);

	}

}

