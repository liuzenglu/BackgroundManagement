/**
 * File Name: ColumnCodeValue.java
 * Date: 2019-06-12 14:51:33
 */
 package com.hyrcb.hydp.modules.tool.dataDict.model;

 /**
 * 功能说明：表字段的码值
 * @author shenzl
 * @date 2019-06-12
 * @version 1.0
 */
public class ColumnCodeValue {
	
	/**
	 * schema
	 */
	private String schema;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 列名
	 */
	private String columnName;
	/**
	 * 键
	 */
	private String key;
	/**
	 * 值
	 */
	private String value;
	
	public ColumnCodeValue() {}

	public ColumnCodeValue(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public ColumnCodeValue(String schema, String tableName, String columnName, String key, String value) {
		super();
		this.schema = schema;
		this.tableName = tableName;
		this.columnName = columnName;
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
}
