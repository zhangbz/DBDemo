package com.zhangbz.dbdemo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final int VERSION = 1;
	private static final String NAME = "DBtest.db";
	
	//���ű����� + ���� + ժҪ
    public static final String TABLE_ID = "_id";
    public static final String TABLE_NEWS_NAME = "news"; //���ű���
    public static final String TABLE_NEWS_TITLE = "title";  //���ű���
    public static final String TABLE_NEWS_SUMMARY = "summary";//����ժҪ

	public DBHelper(Context context) {
		super(context, NAME, null, VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// ����
		db.execSQL("CREATE TABLE " + TABLE_NEWS_NAME + " (" + TABLE_ID
				+ " integer primary key autoincrement, " + TABLE_NEWS_TITLE
				+ " varchar(50), " + TABLE_NEWS_SUMMARY + " VARCHAR(200))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
