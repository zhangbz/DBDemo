package com.zhangbz.dbdemo.dao.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract.Data;
import android.util.Log;

import com.zhangbz.dbdemo.dao.DBHelper;
import com.zhangbz.dbdemo.dao.annotation.Column;
import com.zhangbz.dbdemo.dao.annotation.ID;
import com.zhangbz.dbdemo.dao.annotation.TableName;
//问题一：表名的获取
//问题二：如何将实体中的数据，按照对应关系导入到数据库表中 
//问题三：将数据表中列的数据，按照对应关系导入到实体中
//问题四：明确实体中的主键，获取到主键中封装的值
//问题五：实体的对象创建

public abstract class DAOSupport<M> implements DAO<M> {
	private static final String TAG = "DAOSupport";
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
		ContentValues values = new ContentValues();

		fillColumn(m, values);// 数据源是第一个参数，导入的目标是第二个参数
		return db.insert(getTableName(), null, values);
	}

	@Override
	public int delete(Serializable id) {

		return db.delete(getTableName(), DBHelper.TABLE_ID + " =?",
				new String[] { id.toString() });
	}

	@Override
	public int update(M m) {
		ContentValues values = new ContentValues();
		fillColumn(m, values);
		return db.update(getTableName(), values, DBHelper.TABLE_ID + " =?",
				new String[] { getId(m) });
	}
	
	public List<M> findByCondition(String[] columns, String selection,
            String[] selectionArgs,
            String orderBy){
		return findByCondition(columns, selection, selectionArgs, null, null, orderBy);
	}
	/**
	 * 条件查询
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @return
	 */
	public List<M> findByCondition(String[] columns, String selection,
            String[] selectionArgs, String groupBy, String having,
            String orderBy) {
		List<M> result = null;
		Cursor cursor = db.query(getTableName(), columns, selection, selectionArgs, groupBy, having,
				orderBy);
//		db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)//limit：分页--上（下）拉刷新

		if (cursor != null) {
			result = new ArrayList<M>();
			while (cursor.moveToNext()) {
				M m = getInstance();
				fillField(cursor, m);

				result.add(m);
			}
			cursor.close();
		}
		return result;
	}
	@Override
	public List<M> findAll() {
		List<M> result = null;
		Cursor cursor = db.query(getTableName(), null, null, null, null, null,
				null);

		if (cursor != null) {
			result = new ArrayList<M>();
			while (cursor.moveToNext()) {
				M m = getInstance();
				fillField(cursor, m);

				result.add(m);
			}
			cursor.close();
		}
		return result;
	}

	/**
	 * 问题一：表名的获取
	 * 
	 * @return
	 */
	private String getTableName() {
		// 每个表对应一个具体实体
		// 方案一：如果能够获取到实体，获取到实体的简单名称，首字母小写
		// 方案二：利用注解，实体和数据库表的名称脱离关系

		// 获取到实体--问题五
		M m = getInstance();
		// 获取到实体的注解，依据value里设置的值确定操作的数据库表
		TableName tableName = m.getClass().getAnnotation(TableName.class);// annotationType:注解的类型
		if (tableName != null) {
			return tableName.value();
		}
		return "";
	}

	// 问题二：如何将实体中的数据，按照对应关系导入到数据库表中
	private void fillColumn(M m, ContentValues values) {
		// values.put(DBHelper.TABLE_NEWS_TITLE, news.getTitle());
		// 此处省略n行代码

		// for all public fields for the class
		// m.getClass().getFields()
		Field[] fields = m.getClass().getDeclaredFields();
		for (Field item : fields) {
			item.setAccessible(true);
			Column column = item.getAnnotation(Column.class);
			if (column != null) {
				String key = column.value();
				// Returns the values of the field in the specified object.
				try {
					String value = item.get(m).toString();

					// 如果该field是主键，并且是自增的，不能添加到集合中
					ID id = item.getAnnotation(ID.class);
					if (id != null && id.autoincrement()) {
					} else {
						values.put(key, value);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 问题三：将数据表中列的数据， 按照对应关系导入到实体中
	 * 
	 * @param cursor
	 * @param m
	 */
	private void fillField(Cursor cursor, M m) {
		Field[] fields = m.getClass().getDeclaredFields();
		for (Field item : fields) {
			item.setAccessible(true);
			Column column = item.getAnnotation(Column.class);
			if (column != null) {
				int columnIndex = cursor.getColumnIndex(column.value());
				String value = cursor.getString(columnIndex);
				// Sets the value of the field in the specified objuect to the
				// value.
				try {
					if (item.getType() == int.class) {
						item.set(m, Integer.parseInt(value));
					} else if (item.getType() == Data.class) {
						// 字符串装换成时间
					} else {
						item.set(m, value);
					}
					item.set(m, value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 问题四：明确实体中的主键，获取到主键中封装的值
	 */
	private String getId(M m) {
		Field[] fields = m.getClass().getDeclaredFields();
		for (Field item : fields) {
			item.setAccessible(true);
			ID id = item.getAnnotation(ID.class);
			if (id != null) {
				try {
					return item.get(m).toString();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 问题五：实体的对象创建
	 * 
	 * @return
	 */
	private M getInstance() {
		// 实体是何时确定的

		// 1哪个孩子调用的该方法--哪个在运行孩子
		// 返回次object的运行时 类
		Class clazz = getClass();// class
									// com.zhangbz.dbdemo.dao.impl.NewsDaoImpl
		Log.i(TAG, clazz.toString());
		// 2获取该孩子的父类("支持泛型"的父类)
		// clazz.getSuperclass();//class com.zhangbz.dbdemo.dao.base.DAOSupport
		Type superclass = clazz.getGenericSuperclass();// com.zhangbz.dbdemo.dao.base.DAOSupport<com.zhangbz.dbdemo.dao.domain.News>
		// 泛型实现接口（参数化的类型），规定了泛型的通用操作
		if (superclass != null && superclass instanceof ParameterizedType) {
			Type[] arguments = ((ParameterizedType) superclass)
					.getActualTypeArguments();// [class
												// com.zhangbz.dbdemo.dao.domain.News]
			try {
				return (M) ((Class) arguments[0]).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			Log.i(TAG, "");
		}

		// 3获取到泛型中的参数
		return null;
	}
}
