package com.zhangbz.dbdemo.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ָ����ʵ����ֶ������ݿ��б����еĶ�Ӧ��ϵ
 * @author Administrator
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	/**
	 * ���ݿ��б���
	 * @return
	 */
	String value();

}
