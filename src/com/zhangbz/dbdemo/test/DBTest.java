package com.zhangbz.dbdemo.test;

import java.util.List;

import com.zhangbz.dbdemo.dao.DBHelper;
import com.zhangbz.dbdemo.dao.domain.News;
import com.zhangbz.dbdemo.dao.impl.NewsDaoImpl;

import android.test.AndroidTestCase;

public class DBTest extends AndroidTestCase {
	public void createTable() {
		DBHelper dbHelper = new DBHelper(getContext());
		dbHelper.getWritableDatabase();
	}
	
	public void testInsert() {
		NewsDaoImpl impl = new NewsDaoImpl(getContext());
		News m = new News();
		m.setTitle("测试标题一");
		m.setSummary("测试摘要一");
		impl.insert(m);
	}
	public void testDelete() {
		NewsDaoImpl impl = new NewsDaoImpl(getContext());
		impl.delete(0);
	}
	
	public void testUpdate() {
		NewsDaoImpl impl = new NewsDaoImpl(getContext());
		News m = new News();
		m.setId(2);
		m.setTitle("hm28N");
		m.setSummary("PP");
		impl.update(m);
	}
	
	public void testFindAll() {
		NewsDaoImpl impl = new NewsDaoImpl(getContext());
		List<News> findAll = impl.findAll();
		
	}
}
