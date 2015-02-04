package com.zhangbz.dbdemo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String NAME = "DBtest.db";
	
	private static final int START_VERSION = 1;
    private static final int HISTORY_VERSION = 2;
    private static final int CURRENT_VERSION = 3;
	//���ű����� + ���� + ժҪ
    public static final String TABLE_ID = "_id";
    public static final String TABLE_NEWS_NAME = "news"; //���ű���
    public static final String TABLE_NEWS_TITLE = "title";  //���ű���
    public static final String TABLE_NEWS_SUMMARY = "summary";//����ժҪ

	public DBHelper(Context context) {
		super(context, NAME, null, CURRENT_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// ����
		db.execSQL("CREATE TABLE " + TABLE_NEWS_NAME + " (" + TABLE_ID
				+ " integer primary key autoincrement, " + TABLE_NEWS_TITLE
				+ " varchar(50), " + TABLE_NEWS_SUMMARY + " VARCHAR(200))");
		onUpgrade(db, START_VERSION, CURRENT_VERSION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//����
		//���ݿ�汾����1.0��һ�ű��� 2.0 �����ű�
		//ģ�������û���
		//���û���1.0�û� ��û�����⣩
		//���û���
		
		//ϣ��������Ҫ���ǿ������û���ÿ���������ݿ������ʲô
		
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
			//���±�
		case 4:
			//ɾ����
			break;
		}
	}
}
