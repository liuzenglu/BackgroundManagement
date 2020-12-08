/**
 * File Name: CommonUtils.java
 * Date: 2019-10-15 10:22:31
 */
package com.hyrcb.hydp.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.belucky.easytool.util.DateTimeUtils;



/**
 * Description: 通用工具类
 * @author shenzulun
 * @date 2019-10-15
 * @version 1.0
 */
public class CommonUtils extends me.belucky.easytool.util.CommonUtils{
	protected static Logger log = LoggerFactory.getLogger(CommonUtils.class);
	
	/**
	 * 列转行
	 * @param columns
	 * @return
	 */
	public static String columnToRow(String columns){
		String result = "";
		StringBuffer buff = new StringBuffer();
		if(columns != null){
			String[] arr = columns.split("\n");
			if(arr != null){
				for(int i = 0,len = arr.length;i < len;i++){
					buff.append("'").append(arr[i]).append("'");
					if(i != len - 1){
						buff.append(",");
					}
				}
			}
		}
		result = buff.toString();
		return result;
	}
	
	/**
	 * 列转行
	 * @param columns
	 * @return
	 */
	public static String columnToRow(List<String> list){
		StringBuffer buff = new StringBuffer();
		if(list != null){
			for(String str : list) {
				buff.append("'").append(str).append("',");
			}
		}
		String result = buff.toString();
		result = result.substring(0, result.length() - 1);
		return result;
	}
	
	/**
	 * 集合分割
	 * @param <T>
	 * @param list
	 * @param per
	 * @return
	 */
	public static <T> List<List<T>> divide(List<T> list, int per){
		int size = list.size();
		int count = size / per;
		if(count == 0 || count % per != 0) {
			count++;
		}
		List<List<T>> result = new ArrayList<List<T>>();
		for(int i=0;i<count;i++) {
			int start = i * per;
			int end = (i + 1) * per;
			if(end > size) {
				end = size;
			}
			result.add(list.subList(start, end));
		}
		return result;
	}
	
	/**
	 * 去除字符串右边的空格
	 * @param str
	 * @return
	 */
	public static String trimRight(String str) {
		int offset = 0;
		char[] arr = str.toCharArray();
		for(int i=arr.length -1;i>=0;i--) {
			char c = arr[i];
			if(c != ' ') {
				offset = i;
				break;
			}
		}
		String v = String.copyValueOf(arr, 0, offset + 1);
		return v;
	}
	
	/**
	 * 获取日期
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Date getDate(String str, String pattern) {
		Date date = null;
		DateFormat df = new SimpleDateFormat(pattern);
		try {
			date = df.parse(str);
		} catch (ParseException e) {
			log.error("",e);
		}
		return date;
	}
	
	/**
	 * 返回昨天日期
	 * @return
	 */
	public static String getYesterdayStr() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		return DateTimeUtils.getDateStr(cal.getTime(), "yyyy-MM-dd");
	}
	
}

