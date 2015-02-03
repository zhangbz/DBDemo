package com.zhangbz.dbdemo.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhangbz.dbdemo.dao.DBHelper;
import com.zhangbz.dbdemo.dao.NewsDao;
import com.zhangbz.dbdemo.dao.base.DAOSupport;
import com.zhangbz.dbdemo.dao.domain.News;
//问题一：表名的获取
//问题二：如何将实体中的数据，按照对应关系导入到数据库表中 
//问题三：将数据表中列的数据，按照对应关系导入到实体中
//问题四：明确实体中的主键，获取到主键中封装的值
//问题五：实体的对象创建
public class NewsDaoImpl extends DAOSupport<News> implements NewsDao {

	public NewsDaoImpl(Context context) {
		super(context);

	}

//	@Override
//	public long insert(News news) {
//		//问题一：表名的获取
//		//问题二：如何将实体中的数据，按照对应关系导入到数据库表中 
//		ContentValues values = new ContentValues();
//		values.put(DBHelper.TABLE_NEWS_TITLE, news.getTitle());
//		// 此处省略n行代码
//
//		return db.insert(DBHelper.TABLE_NEWS_TITLE, null, values);
//	}
//
//	@Override
//	public int delete(int id) {
//		// 问题：表名的获取
//		return db.delete(DBHelper.TABLE_NEWS_NAME, DBHelper.TABLE_ID + " =?",
//				new String[] { id + "" });
//	}
//
//	@Override
//	public int update(News news) {
//		//问题一：表名的获取
//		//问题二：如何将实体中的数据，按照对应关系导入到数据库表中 
//		//问题三：明确实体中的主键，获取到主键中封装的值
//		ContentValues values = new ContentValues();
//		values.put(DBHelper.TABLE_NEWS_TITLE, news.getTitle());
//		// 此处省略n行代码
//		return db.update(DBHelper.TABLE_NEWS_NAME, values, DBHelper.TABLE_ID
//				+ " =?", new String[] { news.getId() + "" });
//	}
//
//	@Override
//	public List<News> findAll() {
//		//问题一：表名的获取
//		//问题二：实体的对象的创建
//		//问题三：将数据表中烈的数据，按照对应关系导入到实体中
//		List<News> result = null;//List<M>
//		Cursor cursor = db.query(DBHelper.TABLE_NEWS_NAME, null, null, null, null, null, null);
//		
//		if (cursor != null) {
//			result = new ArrayList<News> ();
//			while (cursor.moveToNext()) {
//				News news = new News();
//				
//				int columnIndex = cursor.getColumnIndex(DBHelper.TABLE_NEWS_TITLE);
//				String title = cursor.getString(columnIndex);
//				news.setTitle(title);
//				//此处省略n行代码
//				result.add(news);
//			}
//			cursor.close();
//		}
//		return result;
//	}

}
