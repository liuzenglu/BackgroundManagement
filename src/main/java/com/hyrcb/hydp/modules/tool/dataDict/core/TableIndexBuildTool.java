/**
 * File Name: TableIndexBuildTool.java
 * Date: 2019-06-13 10:19:43
 */
 package com.hyrcb.hydp.modules.tool.dataDict.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hyrcb.hydp.common.config.StaticCode;
import com.hyrcb.hydp.modules.tool.dataDict.model.ColumnCodeValue;
import com.hyrcb.hydp.modules.tool.dataDict.model.ColumnInfo;
import com.hyrcb.hydp.modules.tool.dataDict.model.TableInfo;

import me.belucky.easytool.util.CacheUtils;
import me.belucky.easytool.util.FileTools;

/**
  *   功能说明：数据表的索引建立工具类
 * @author shenzl
 * @date 2019-06-13
 * @version 1.0
 */
public class TableIndexBuildTool {
	protected static Logger log = LoggerFactory.getLogger(TableIndexBuildTool.class);

	/**
	 * 生成数据字典索引
	 * @param fileName	
	 * @param prefix
	 */
	public static void buildTableIndex(String fileName, String prefix){
		buildTableIndex(fileName, prefix, null);
	}
	
	/**
	 * 生成数据字典索引
	 * @param fileName	数据文件名   绝对路径
	 * @param prefix	缓存key前缀
	 * @param schema	数据库模式名
	 */
	public static void buildTableIndex(String fileName, String prefix, String schema){
		long start = System.currentTimeMillis();
		log.info("开始解析数据文件[{}]", fileName);
		File file = FileTools.getFile(fileName);
		if(file == null) {
			log.error("数据文件[{}]不存在,请检查...", fileName);
			return;
		}
		if(schema == null){
			//先找出文件名
			String fName = file.getName();
			schema = fName.substring(0, fName.indexOf("-"));
		}
		List<String> lines = FileTools.getContentList(file, StaticCode.DEFAULT_FILE_ENCODE);
		if(lines == null || lines.size() == 0){
			log.error("数据文件[{}]为空,请检查...", fileName);
			return;
		}
		String[] firstArr = lines.get(0).split("\\|");
		String tmpTableName = firstArr[0];
		String tmpCnName = firstArr[1];
		String tiMapKey = prefix + "_tab_full_map";
		String tisKey = prefix + "_tab_full_lists";
		String detailsFullListKey = prefix + "_tab_col_full_lists";
		Map<String, TableInfo> tiMap = null;
		if(CacheUtils.getCache(tiMapKey) == null){
			tiMap = new HashMap<String, TableInfo>();
		}else{
			tiMap = CacheUtils.getCache(tiMapKey);
		}
		List<TableInfo> tis = null;    
		if(CacheUtils.getCache(tisKey) == null){
			tis = new ArrayList<TableInfo>();
		}else{
			tis = CacheUtils.getCache(tisKey);
		}
		List<ColumnInfo> detailsFullList = null;    
		if(CacheUtils.getCache(detailsFullListKey) == null){
			detailsFullList = new ArrayList<ColumnInfo>();
		}else{
			detailsFullList = CacheUtils.getCache(detailsFullListKey);
		}
		List<ColumnInfo> details = new ArrayList<ColumnInfo>();
		for(int i=0,len=lines.size();i<len;i++){
			String line = lines.get(i);
			String[] arr = line.split("\\|");
			if(arr.length != 6){
				log.info(arr[0] + ";" + arr.length + ";" + line);
				continue;
			}
			if(i > 0 && !tmpTableName.equals(arr[0])){
				//表名不一样 说明遇到下一张表
				//先保存上张表 
				TableInfo ti = new TableInfo();
				ti.setSchema(schema);
				ti.setTableName(tmpTableName);
				ti.setCnName(tmpCnName);
				ti.setColumns(details);
				tiMap.put(tmpTableName, ti);
				tis.add(ti);
				//再初始化当前表
				details = new ArrayList<ColumnInfo>();
				tmpTableName = arr[0];
				tmpCnName = arr[1];
			}
			ColumnInfo td = new ColumnInfo(schema,arr[0],arr[1],arr[2],arr[3],arr[4],arr[5]);
			//
			Map<String, List<ColumnCodeValue>>  columnCodeMap = CacheUtils.getCache(StaticCode.DICT_CODE_VALUE_MAP);
			td.setCodeValues(columnCodeMap.get(arr[0] + "-" + arr[2]));
			details.add(td);
			detailsFullList.add(td);
		}
		CacheUtils.putCache(tiMapKey, tiMap);
		CacheUtils.putCache(tisKey, tis);
		CacheUtils.putCache(detailsFullListKey, detailsFullList);
		log.info("数据文件[{}]解析成功", fileName);
		long end = System.currentTimeMillis();
		log.info("解析耗时-{}ms", (end - start));
	}
}
