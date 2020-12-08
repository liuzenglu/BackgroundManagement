/**
 * File Name: DataDictConfig.java
 * Date: 2020-05-28 17:02:25
 */
package com.hyrcb.hydp.modules.tool.dataDict.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Description: 数据字典的配置类
 * @author shenzulun
 * @date 2020-05-28
 * @version 1.0
 */
@Configuration
@PropertySource(value= {"classpath:config/dict/db-index-config.properties"}, encoding="UTF-8")
@ConfigurationProperties(prefix = "dict", ignoreUnknownFields = true)
public class DataDictConfig {
	
	private String dataExcelPath;
	
	private String dataExcelNameRegex;
	
	private String codeValueExcelName;
	
	private String dataCategory;
	
	private String dataCodevalue;
	
	private String datafilePath;

	public String getDataExcelPath() {
		return dataExcelPath;
	}

	public void setDataExcelPath(String dataExcelPath) {
		this.dataExcelPath = dataExcelPath;
	}

	public String getDataExcelNameRegex() {
		return dataExcelNameRegex;
	}

	public void setDataExcelNameRegex(String dataExcelNameRegex) {
		this.dataExcelNameRegex = dataExcelNameRegex;
	}

	public String getCodeValueExcelName() {
		return codeValueExcelName;
	}

	public void setCodeValueExcelName(String codeValueExcelName) {
		this.codeValueExcelName = codeValueExcelName;
	}

	public String getDataCategory() {
		return dataCategory;
	}

	public void setDataCategory(String dataCategory) {
		this.dataCategory = dataCategory;
	}

	public String getDataCodevalue() {
		return dataCodevalue;
	}

	public void setDataCodevalue(String dataCodevalue) {
		this.dataCodevalue = dataCodevalue;
	}

	public String getDatafilePath() {
		return datafilePath;
	}

	public void setDatafilePath(String datafilePath) {
		this.datafilePath = datafilePath;
	}

	@Override
	public String toString() {
		return "DataDictConfig [dataExcelPath=" + dataExcelPath + ", dataExcelNameRegex=" + dataExcelNameRegex
				+ ", codeValueExcelName=" + codeValueExcelName + ", dataCategory=" + dataCategory + ", dataCodevalue="
				+ dataCodevalue + ", datafilePath=" + datafilePath + "]";
	}
	
}
