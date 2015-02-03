package com.zhangbz.dbdemo.dao.base;

import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zhangbz.dbdemo.dao.DBHelper;
//问题一：表名的获取
//问题二：如何将实体中的数据，按照对应关系导入到数据库表中 
//问题三：将数据表中列的数据，按照对应关系导入到实体中
//问题四：明确实体中的主键，获取到主键中封装的值
//问题五：实体的对象创建
public abstract class DAOSupport<M> implements DAO<M>{
	protected Context context;
	protected DBHelper helper;
	protected SQLiteDatabase db;
	
	public DAOSupport(Context context) {
		super();
		this.context = context;
		helper = new DBHelper(context);
		db = helper.getWritableDatabase();
	}

	@Override
	public long insert(M m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(M m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<M> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
