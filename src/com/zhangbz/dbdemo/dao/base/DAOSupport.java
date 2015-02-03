package com.zhangbz.dbdemo.dao.base;

import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zhangbz.dbdemo.dao.DBHelper;
//����һ�������Ļ�ȡ
//���������ν�ʵ���е����ݣ����ն�Ӧ��ϵ���뵽���ݿ���� 
//�������������ݱ����е����ݣ����ն�Ӧ��ϵ���뵽ʵ����
//�����ģ���ȷʵ���е���������ȡ�������з�װ��ֵ
//�����壺ʵ��Ķ��󴴽�
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
