package com.zhangbz.dbdemo.dao.impl;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zhangbz.dbdemo.dao.BookDao;
import com.zhangbz.dbdemo.dao.DBHelper;
import com.zhangbz.dbdemo.dao.base.DAOSupport;
import com.zhangbz.dbdemo.dao.domain.Book;

public class BookDaoImpl extends DAOSupport<Book> implements BookDao {

	
	
	public BookDaoImpl(Context context) {
		super(context);
	}

//	@Override
//	public long insert(Book Book) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int delete(int id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int update(Book Book) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<Book> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
