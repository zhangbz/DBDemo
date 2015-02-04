package com.zhangbz.dbdemo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String NAME = "DBtest.db";
	
	private static final int START_VERSION = 1;
    private static final int HISTORY_VERSION = 2;
    private static final int CURRENT_VERSION = 3;
	//新闻表：主键 + 标题 + 摘要
    public static final String TABLE_ID = "_id";
    public static final String TABLE_NEWS_NAME = "news"; //新闻表名
    public static final String TABLE_NEWS_TITLE = "title";  //新闻标题
    public static final String TABLE_NEWS_SUMMARY = "summary";//新闻摘要

	public DBHelper(Context context) {
		super(context, NAME, null, CURRENT_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 建表
		db.execSQL("CREATE TABLE " + TABLE_NEWS_NAME + " (" + TABLE_ID
				+ " integer primary key autoincrement, " + TABLE_NEWS_TITLE
				+ " varchar(50), " + TABLE_NEWS_SUMMARY + " VARCHAR(200))");
		onUpgrade(db, START_VERSION, CURRENT_VERSION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//更新
		//数据库版本管理：1.0（一张表）， 2.0 （两张表）
		//模拟两种用户：
		//老用户：1.0用户 （没有问题）
		//新用户：
		
		//希望：不需要总是考虑老用户，每次升级数据库操作是什么
		
		switch (oldVersion) {
		case START_VERSION:
			db.execSQL("CREATE TABLE " + "book" + " (" + TABLE_ID
					+ " integer primary key autoincrement, " + TABLE_NEWS_TITLE
					+ " varchar(50), " + TABLE_NEWS_SUMMARY + " VARCHAR(200))");
		case HISTORY_VERSION:
			db.execSQL("CREATE TABLE " + "class" + " (" + TABLE_ID
					+ " integer primary key autoincrement, " + TABLE_NEWS_TITLE
					+ " varchar(50), " + TABLE_NEWS_SUMMARY + " VARCHAR(200))");
		case 3:
			//更新表
		case 4:
			//删除表
			break;
		}
	}
}
