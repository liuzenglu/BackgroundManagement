/**
 * File Name: ColumnInfo.java
 * Date: 2016-12-28 上午11:48:32
 */
package com.hyrcb.hydp.modules.tool.dataDict.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hyrcb.hydp.common.config.StaticCode;

import me.belucky.easytool.util.CacheUtils;

/**
 * 功能说明: 表字段明细
 * @author shenzl
 * @date 2016-12-28
 * @version 1.0
 */
public class ColumnInfo {
	
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
	private String tableCnName;
	/**
	 * 列名
	 */
	private String columnName;
	/**
	 * 列中文名
	 */
	private String columnCnName;
	/**
	 * 数据类型
	 */
	private String dataType;
	/**
	 * 数据长度
	 */
	private String dataLength;
	/**
	 * 字段码值集合
	 */
	private List<ColumnCodeValue> codeValues = new ArrayList<ColumnCodeValue>();
	/**
	 * 码值个数
	 */
	private int codeCounts;
	/**
	 * 数据类型-前端展示,类似CHAR(16)
	 */
	private String dataTypeShow;
	/**
	 * 系统名称-前端展示
	 */
	private String systemName;
	
	public ColumnInfo(){}
	
	public ColumnInfo(String schema, String tableName, String tableCnName, String columnName, String columnCnName,
			String dataType, String dataLength) {
		super();
		this.schema = schema;
		this.tableName = tableName;
		this.tableCnName = tableCnName;
		this.columnName = columnName;
		this.columnCnName = columnCnName;
		this.dataType = dataType;
		this.dataLength = dataLength;
	}

	public ColumnInfo(String schema, String tableName, String tableCnName, String columnName, String columnCnName,
			String dataType, String dataLength, List<ColumnCodeValue> codeValues) {
		super();
		this.schema = schema;
		this.tableName = tableName;
		this.tableCnName = tableCnName;
		this.columnName = columnName;
		this.columnCnName = columnCnName;
		this.dataType = dataType;
		this.dataLength = dataLength;
		this.codeValues = codeValues;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableCnName() {
		return tableCnName;
	}

	public void setTableCnName(String tableCnName) {
		this.tableCnName = tableCnName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnCnName() {
		return columnCnName;
	}

	public void setColumnCnName(String columnCnName) {
		this.columnCnName = columnCnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataLength() {
		return dataLength;
	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public List<ColumnCodeValue> getCodeValues() {
		return codeValues;
	}

	public void setCodeValues(List<ColumnCodeValue> codeValues) {
		this.codeValues = codeValues;
	}

	public int getCodeCounts() {
		return codeValues == null ? 0 : codeValues.size();
	}

	public String getDataTypeShow() {
		String result = (dataType == null ? "" : dataType);
		result = result.toUpperCase();
		if(!"DATE".equals(result) && !"TIMESTAMP".equals(result)) {
			if(dataLength != null && !"".equals(dataLength)) {
				result += "(" + dataLength + ")";
			}
		}
		return result;
	}
	
	public String getSystemName() {
		String name = "";
		Map<String, String> codeMap = CacheUtils.getCache(StaticCode.DICT_SYSTEM_CODE_MAP);
		int ind = tableName.indexOf(".");
		if(ind == -1) {
			ind = tableName.indexOf("_");
		}else {
			ind++;
		}
		name = codeMap.get(tableName.substring(0, ind));
		return name;
	}
	
}
