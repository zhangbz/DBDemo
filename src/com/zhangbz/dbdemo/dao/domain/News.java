package com.zhangbz.dbdemo.dao.domain;

import com.zhangbz.dbdemo.dao.DBHelper;
import com.zhangbz.dbdemo.dao.annotation.TableName;
/**
 * 新闻实体
 * @author Administrator
 *
 */
@TableName(DBHelper.TABLE_NEWS_NAME)
public class News {
	private int id;
	private String title;
	private String summary;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
}
