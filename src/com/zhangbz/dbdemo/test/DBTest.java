package com.zhangbz.dbdemo.test;

import com.zhangbz.dbdemo.dao.DBHelper;

import android.test.AndroidTestCase;

public class DBTest extends AndroidTestCase {
	public void createTable() {
		DBHelper dbHelper = new DBHelper(getContext());
		dbHelper.getWritableDatabase();
	}
}
