package com.zhangbz.dbdemo.dao.domain;

import com.zhangbz.dbdemo.dao.DBHelper;
import com.zhangbz.dbdemo.dao.annotation.Column;
import com.zhangbz.dbdemo.dao.annotation.ID;
import com.zhangbz.dbdemo.dao.annotation.TableName;

/**
 * 新闻实体
 * 
 * @author Administrator
 * 
 */
@TableName(DBHelper.TABLE_NEWS_NAME)
public class News {
	//主键：明确，值的获取
	//自增
	@ID(autoincrement=true)
	@Column(DBHelper.TABLE_ID)
	
	private int id;
	@Column(DBHelper.TABLE_NEWS_TITLE)
	private String title;
	@Column(DBHelper.TABLE_NEWS_SUMMARY)
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
