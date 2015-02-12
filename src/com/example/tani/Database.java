package com.example.tani;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "tani.db";
	private static final int DATABASE_VERSION = 2;
	private static final String TABLE_NAME="score";
	private static final String ID ="id";
	private static final String SUBJECT ="sub";
	private static final String A = "a";
	private static final String B = "b";
	private static final String SUCCES = "s";
	private static final String TABLE_NAME2="day";
	private static final String DAY = "day";
	private static final String TABLE_NAME3="per";
	private static final String PERMISSION= "per";



	Database(Context context){


		super(context,DATABASE_NAME,null,DATABASE_VERSION);

	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		String query="create table "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+SUBJECT+" TEX,"+A+" TEXT,"+B+" TEXT,"+SUCCES+" TEXT);";
		db.execSQL(query);
		String query2="create table "+TABLE_NAME2+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+SUBJECT+" TEXT,"+DAY+" INTEGR);";
		db.execSQL(query2);
		String query3="create table "+TABLE_NAME3+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+SUBJECT+" TEXT,"+PERMISSION+" TEXT);";
		db.execSQL(query3);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("drop table if exists " + TABLE_NAME);
		onCreate(db);
		db.execSQL("drop table if exists " + TABLE_NAME2);
		onCreate(db);
		db.execSQL("drop table if exists " + TABLE_NAME3);
		onCreate(db);

	}



}
