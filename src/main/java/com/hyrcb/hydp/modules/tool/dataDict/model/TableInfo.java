/**
 * File Name: TableInfo.java
 * Date: 2016-12-28 上午09:09:12
 */
package com.hyrcb.hydp.modules.tool.dataDict.model;

import java.util.ArrayList;
import java.util.List;
import me.belucky.easytool.config.ConstCode;

/**
 * 功能说明: 数据库表结构信息
 * @author shenzl
 * @date 2016-12-28
 * @version 1.0
 */
public class TableInfo {
	
	/**
	 * schema
	 */
	private String schema;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 表中文名
	 */
	private String cnName;
	/**
	 * 表字段集合
	 */
	private List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
	
	public TableInfo(){}
	
	public TableInfo(String schema, String tableName, String cnName, List<ColumnInfo> columns) {
		super();
		this.schema = schema;
		this.tableName = tableName;
		this.cnName = cnName;
		this.columns = columns;
	}

	public String getTableName() {
		return tableName == null ? ConstCode.BLANK_STRING : tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getCnName() {
		return cnName == null ? ConstCode.BLANK_STRING : cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public List<ColumnInfo> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnInfo> columns) {
		this.columns = columns;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}
	
}
