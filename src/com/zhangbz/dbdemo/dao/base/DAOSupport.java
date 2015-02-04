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
//����һ�������Ļ�ȡ
//���������ν�ʵ���е����ݣ����ն�Ӧ��ϵ���뵽���ݿ���� 
//�������������ݱ����е����ݣ����ն�Ӧ��ϵ���뵽ʵ����
//�����ģ���ȷʵ���е���������ȡ�������з�װ��ֵ
//�����壺ʵ��Ķ��󴴽�

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

		fillColumn(m, values);// ����Դ�ǵ�һ�������������Ŀ���ǵڶ�������
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
	 * ������ѯ
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
//		db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)//limit����ҳ--�ϣ��£���ˢ��

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
	 * ����һ�������Ļ�ȡ
	 * 
	 * @return
	 */
	private String getTableName() {
		// ÿ�����Ӧһ������ʵ��
		// ����һ������ܹ���ȡ��ʵ�壬��ȡ��ʵ��ļ����ƣ�����ĸСд
		// ������������ע�⣬ʵ������ݿ������������ϵ

		// ��ȡ��ʵ��--������
		M m = getInstance();
		// ��ȡ��ʵ���ע�⣬����value�����õ�ֵȷ�����������ݿ��
		TableName tableName = m.getClass().getAnnotation(TableName.class);// annotationType:ע�������
		if (tableName != null) {
			return tableName.value();
		}
		return "";
	}

	// ���������ν�ʵ���е����ݣ����ն�Ӧ��ϵ���뵽���ݿ����
	private void fillColumn(M m, ContentValues values) {
		// values.put(DBHelper.TABLE_NEWS_TITLE, news.getTitle());
		// �˴�ʡ��n�д���

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

					// �����field�������������������ģ�������ӵ�������
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
	 * �������������ݱ����е����ݣ� ���ն�Ӧ��ϵ���뵽ʵ����
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
						// �ַ���װ����ʱ��
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
	 * �����ģ���ȷʵ���е���������ȡ�������з�װ��ֵ
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
	 * �����壺ʵ��Ķ��󴴽�
	 * 
	 * @return
	 */
	private M getInstance() {
		// ʵ���Ǻ�ʱȷ����

		// 1�ĸ����ӵ��õĸ÷���--�ĸ������к���
		// ���ش�object������ʱ ��
		Class clazz = getClass();// class
									// com.zhangbz.dbdemo.dao.impl.NewsDaoImpl
		Log.i(TAG, clazz.toString());
		// 2��ȡ�ú��ӵĸ���("֧�ַ���"�ĸ���)
		// clazz.getSuperclass();//class com.zhangbz.dbdemo.dao.base.DAOSupport
		Type superclass = clazz.getGenericSuperclass();// com.zhangbz.dbdemo.dao.base.DAOSupport<com.zhangbz.dbdemo.dao.domain.News>
		// ����ʵ�ֽӿڣ������������ͣ����涨�˷��͵�ͨ�ò���
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

		// 3��ȡ�������еĲ���
		return null;
	}
}
