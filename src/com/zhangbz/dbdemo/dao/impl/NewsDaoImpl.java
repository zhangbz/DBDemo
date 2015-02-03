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
//����һ�������Ļ�ȡ
//���������ν�ʵ���е����ݣ����ն�Ӧ��ϵ���뵽���ݿ���� 
//�������������ݱ����е����ݣ����ն�Ӧ��ϵ���뵽ʵ����
//�����ģ���ȷʵ���е���������ȡ�������з�װ��ֵ
//�����壺ʵ��Ķ��󴴽�
public class NewsDaoImpl extends DAOSupport<News> implements NewsDao {

	public NewsDaoImpl(Context context) {
		super(context);

	}

//	@Override
//	public long insert(News news) {
//		//����һ�������Ļ�ȡ
//		//���������ν�ʵ���е����ݣ����ն�Ӧ��ϵ���뵽���ݿ���� 
//		ContentValues values = new ContentValues();
//		values.put(DBHelper.TABLE_NEWS_TITLE, news.getTitle());
//		// �˴�ʡ��n�д���
//
//		return db.insert(DBHelper.TABLE_NEWS_TITLE, null, values);
//	}
//
//	@Override
//	public int delete(int id) {
//		// ���⣺�����Ļ�ȡ
//		return db.delete(DBHelper.TABLE_NEWS_NAME, DBHelper.TABLE_ID + " =?",
//				new String[] { id + "" });
//	}
//
//	@Override
//	public int update(News news) {
//		//����һ�������Ļ�ȡ
//		//���������ν�ʵ���е����ݣ����ն�Ӧ��ϵ���뵽���ݿ���� 
//		//����������ȷʵ���е���������ȡ�������з�װ��ֵ
//		ContentValues values = new ContentValues();
//		values.put(DBHelper.TABLE_NEWS_TITLE, news.getTitle());
//		// �˴�ʡ��n�д���
//		return db.update(DBHelper.TABLE_NEWS_NAME, values, DBHelper.TABLE_ID
//				+ " =?", new String[] { news.getId() + "" });
//	}
//
//	@Override
//	public List<News> findAll() {
//		//����һ�������Ļ�ȡ
//		//�������ʵ��Ķ���Ĵ���
//		//�������������ݱ����ҵ����ݣ����ն�Ӧ��ϵ���뵽ʵ����
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
//				//�˴�ʡ��n�д���
//				result.add(news);
//			}
//			cursor.close();
//		}
//		return result;
//	}

}
