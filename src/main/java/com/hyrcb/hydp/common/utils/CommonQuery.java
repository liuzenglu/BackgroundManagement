/**
 * File Name: CommonQuery.java
 * Date: 2019-06-12 15:58:16
 */
 package com.hyrcb.hydp.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hyrcb.hydp.common.config.StaticCode;
import me.belucky.easytool.util.CacheUtils;
import me.belucky.easytool.util.CommonUtils;
import me.belucky.easytool.util.StringUtils;


/**
 * 功能说明：通用查询类
 * @author shenzl
 * @date 2019-06-12
 * @version 1.0
 */
public class CommonQuery {
	protected static Logger log = LoggerFactory.getLogger(CommonQuery.class);	
	
	/**
	 * 正则查询
	 * 支持指定多个属性名
	 * @param <T>
	 * @param source 	  数据集合 
	 * @param queryValue 查询条件
	 * @param fieldName  属性名
	 * @return
	 */
	public static <T> List<T> regexQuery(List<T> source, String queryValue, String... fieldName){
		return regexQueryByPrefix(source,queryValue,"",fieldName);
	}
	
	/**
	 * 正则查询
	 * 支持指定多个属性名
	 * @param <T>
	 * @param source 数据集合
	 * @param queryValue  查询条件
	 * @param prefix 前缀
	 * @param fieldName 属性名
	 * @return
	 */
	public static <T> List<T> regexQueryByPrefix(List<T> source, String queryValue, String prefix, String... fieldName){
		List<T> result = new ArrayList<T>();
		if(source == null || source.size() == 0 || !StringUtils.isNotNull(queryValue)){
			return result;
		}
		String schema = null;
		String[] arr = queryValue.split("\\.");
		if(arr.length > 1){
			//适配S04_CRD(.+?)TZ$ 这种场景没有schema
			boolean flag = false;
			char lastC = arr[0].charAt(arr[0].length() - 1);
			if((lastC >= 'a' && lastC <= 'z') || (lastC >= 'A' && lastC <= 'Z') || (lastC >= '0' && lastC <= '9')){
				flag = true;
			}
			if(flag){
				if(prefix != null && !"".equals(prefix)){
					//判断prefix_shcema是否存在
					Map<String, String> schemaMap = CacheUtils.getCache(StaticCode.DICT_SCHEMA_MAP);
					if(schemaMap.containsKey(prefix + "_" + arr[0].toLowerCase())){
						//存在
						schema = arr[0].toUpperCase();
						queryValue = queryValue.substring(queryValue.indexOf(".") + 1);
					}
				}else{
					schema = arr[0].toUpperCase();
					queryValue = arr[1];
				}
			}
		}
		Pattern pattern = Pattern.compile(queryValue.toUpperCase());
		for(T t : source){
			//反射查询值
			for(String fieldName0 : fieldName){
				if(schema != null){
					String dataSchema = CommonUtils.invoke(t, "schema");
					if(dataSchema == null || !schema.equals(dataSchema.toUpperCase())) {
						continue;
					}
				}
				String v = CommonUtils.invoke(t, fieldName0);
				if(!StringUtils.isNotNull(v)){
					continue;
				}
				Matcher m = pattern.matcher(v.toUpperCase());
				if(m.find()){
					result.add(t);
					break;
				}
			}
			
		}
		return result;
	}
	
}
