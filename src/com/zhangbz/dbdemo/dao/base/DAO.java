package com.zhangbz.dbdemo.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * ʵ�������ͨ�ýӿ�
 * @author Administrator
 *
 */
public interface DAO<M> {
	long insert(M m);
	int delete(Serializable id);//int long String
	int update(M m);
	List<M> findAll();
}