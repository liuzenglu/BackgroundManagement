/**
 * File Name: SystemCode.java
 * Date: 2019-06-13 15:35:21
 */
 package com.hyrcb.hydp.modules.tool.dataDict.model;


/**
  *   功能说明：系统分类
 * @author shenzl
 * @date 2019-06-13
 * @version 1.0
 */
public class SystemCode{
	/**
	 * key
	 */
	private String key;
	/**
	 * value
	 */
	private String value;
	
	public SystemCode() {}
	
	public SystemCode(String key, String value) {
		super();
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
}
