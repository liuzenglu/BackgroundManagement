/**
 * File Name: CommonQuery.java
 * Date: 2019-06-12 15:58:16
 */
 package com.hyrcb.hydp.modules.tool.dataDict.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hyrcb.hydp.common.config.StaticCode;
import com.hyrcb.hydp.common.utils.CommonQuery;
import com.hyrcb.hydp.modules.tool.dataDict.model.ColumnCodeValue;
import com.hyrcb.hydp.modules.tool.dataDict.model.ColumnInfo;
import com.hyrcb.hydp.modules.tool.dataDict.model.TableInfo;
import me.belucky.easytool.util.CacheUtils;
import me.belucky.easytool.util.StringUtils;


/**
 * 功能说明：通用查询类
 * @author shenzl
 * @date 2019-06-12
 * @version 1.0
 */
public class DataDictQuery {
	protected static Logger log = LoggerFactory.getLogger(DataDictQuery.class);	
	
	/**
	 * 查询数据字典,默认优先准确
	 * @param queryValue
	 * @param prefix
	 * @return
	 */
	public static List<ColumnInfo> dictQuery(String queryValue, String prefix){
		return dictQuery(queryValue, prefix, 1);
		
	}
	
	/**
	 * 查询数据字典
	 * @param queryValue
	 * @param prefix
	 * @param queryFlag   1: 优先准确   2: 完全模糊
	 * @return
	 */
	public static List<ColumnInfo> dictQuery(String queryValue, String prefix, int queryFlag){
		long start = System.currentTimeMillis();
		List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
		String tiMapKey = prefix + "_tab_full_map";
		String detailsFullListKey = prefix + "_tab_col_full_lists";
		String tisKey = prefix + "_tab_full_lists";
		if(queryValue != null && !"".equals(queryValue)){
			//查询条件非空
			Map<String, TableInfo> tiMap = CacheUtils.getCache(tiMapKey);
			List<ColumnInfo> detailsFullList = CacheUtils.getCache(detailsFullListKey);
			
			//先判断是否存在中文
			if(StringUtils.isExistChineseChar(queryValue)){
				//存在中文,根据表名模糊查询或者根据列名模糊查询
				//先按表名查询
				Object obj = tiMap.get(queryValue);
				if(obj != null && queryFlag == 1){
					TableInfo tmp = (TableInfo)obj;
					columns.addAll(tmp.getColumns());
				}else{
					List<TableInfo> tableList = CacheUtils.getCache(tisKey);
					//按表名模糊查询
					List<TableInfo> tmpList1 = CommonQuery.regexQuery(tableList, queryValue, "cnName");
					for(TableInfo ti : tmpList1){
						columns.addAll(ti.getColumns());
					}
					List<ColumnInfo> tmpList2 = CommonQuery.regexQuery(detailsFullList, queryValue, "columnCnName");
					columns.addAll(tmpList2);
				}
			}else{
				//不存在中文,根据表英文名或者列英文名模糊查询
				List<TableInfo> tableList = CacheUtils.getCache(tisKey);
				queryValue = queryValue.toUpperCase();
				String[] tables = queryValue.split(",");
				for(String tab : tables){
					//先按表名查询
					Object obj = tiMap.get(queryValue);
					if(obj != null  && queryFlag == 1){
						TableInfo tmp = (TableInfo)obj;
						columns.addAll(tmp.getColumns());
					}else{
						//查不到 按表名模糊
						List<TableInfo> tmpList1 = CommonQuery.regexQueryByPrefix(tableList, tab, prefix,"tableName");
						for(TableInfo ti : tmpList1){
							columns.addAll(ti.getColumns());
						}
						//查不到 按列名模糊
						List<ColumnInfo> tmpList2 = CommonQuery.regexQuery(detailsFullList, tab, "columnName");
						columns.addAll(tmpList2);
					}
				}
			}
			
			//码值查询
			List<ColumnCodeValue> codeValues = CacheUtils.getCache(StaticCode.DICT_CODE_VALUE_LIST);
			List<ColumnCodeValue> tmpList3 = CommonQuery.regexQuery(codeValues, queryValue, "value");
			if(tmpList3 != null) {
				for(ColumnCodeValue codeValue : tmpList3) {
					TableInfo tableInfo = tiMap.get(codeValue.getTableName());
					if(tableInfo != null) {
						for(ColumnInfo column : tableInfo.getColumns()) {
							if(codeValue.getColumnName().equals(column.getColumnName())) {
								columns.add(column);
							}
						}
					}
				}
			}
			
		}else{
			columns = CacheUtils.getCache(detailsFullListKey);
		}
		long end = System.currentTimeMillis();
		log.info("匹配[{}]条记录", columns.size());
		log.info("查询耗时-{}ms", (end - start));
		return columns;
	}
	
	/**
	 * 获取TABLE
	 * @param tableName
	 * @param prefix
	 * @return
	 */
	public static TableInfo getTableInfo(String tableName, String prefix){
		String tiMapKey = prefix + "_tab_full_map";
		Map<String, TableInfo> tiMap = CacheUtils.getCache(tiMapKey);
		TableInfo tab = tiMap.get(tableName);
		return tab;
	}
	
	/**
	 * 获取Column
	 * @param tableName
	 * @param columnName
	 * @param prefix
	 * @return
	 */
	public static ColumnInfo getColumnInfo(String tableName, String columnName, String prefix){
		ColumnInfo result = new ColumnInfo();
		TableInfo tab = getTableInfo(tableName, prefix);
		if(tab == null) {
			return null;
		}
		List<ColumnInfo> columns = tab.getColumns();
		for(ColumnInfo c : columns) {
			if(c.getColumnName().equals(columnName)) {
				result = c;
				break;
			}
		}
		return result;
	}
}
