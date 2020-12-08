/**
 * File Name: DataCodeValueCacheInitTask.java
 * Date: 2019-06-17 15:16:26
 */
 package com.hyrcb.hydp.modules.tool.dataDict.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hyrcb.hydp.common.config.StaticCode;
import com.hyrcb.hydp.common.core.BeanFactory;
import com.hyrcb.hydp.modules.tool.dataDict.config.DataDictConfig;
import com.hyrcb.hydp.modules.tool.dataDict.model.ColumnCodeValue;
import me.belucky.easytool.task.AbstractTask;
import me.belucky.easytool.util.CacheUtils;
import me.belucky.easytool.util.FileTools;
import me.belucky.easytool.util.StringUtils;


/**
 * 功能说明：表的字段码值缓存初始化任务
 * @author shenzl
 * @date 2019-06-17
 * @version 1.0
 */
public class DataCodeValueCacheInitTask extends AbstractTask{

	public DataCodeValueCacheInitTask(String taskName) {
		super(taskName);
	}

	public void execute() {
		log.info("开始建立字段码值索引...");
		List<ColumnCodeValue> codeValues = getObjectFromCacheInit(StaticCode.DICT_CODE_VALUE_LIST, new ArrayList<ColumnCodeValue>());
		Map<String, List<ColumnCodeValue>> columnCodeMap = getObjectFromCacheInit(StaticCode.DICT_CODE_VALUE_MAP, new HashMap<String, List<ColumnCodeValue>>());
		
		DataDictConfig dictConfig = BeanFactory.getBean(DataDictConfig.class);
		
		String datafilePath = dictConfig.getDatafilePath();
		String codeValueStr = dictConfig.getDataCodevalue();
		String[] codeValueArr = codeValueStr.split("\\|");
		String schema = codeValueArr[0];
		List<ColumnCodeValue> tmpCodeValueList = new ArrayList<ColumnCodeValue>();
		String tempCodeValueKey = null;
		List<String> lines = FileTools.getContentList(datafilePath + codeValueArr[1],true,StaticCode.DEFAULT_FILE_ENCODE);
		if(lines != null && lines.size() != 0){
			int count = lines.size();
			for(String line : lines) {
				String[] arr = StringUtils.split(line, '|');
				if(arr.length < 4) {
					log.error(line);
					continue;
				}
				ColumnCodeValue c = new ColumnCodeValue(schema,arr[0],arr[1],arr[2],arr[3]);
				codeValues.add(c);
				String key = arr[0] + "-" + arr[1];
				if(tempCodeValueKey != null && !key.equals(tempCodeValueKey)) {
					//遇到新的字段
					columnCodeMap.put(tempCodeValueKey, tmpCodeValueList);
					tempCodeValueKey = key;
					tmpCodeValueList = new ArrayList<ColumnCodeValue>();
				}else {
					tempCodeValueKey = key;
				}
				tmpCodeValueList.add(c);
				if(--count == 0) {
					//最后一行
					columnCodeMap.put(tempCodeValueKey, tmpCodeValueList);
				}
			}
		}
		CacheUtils.putCache(StaticCode.DICT_CODE_VALUE_LIST, codeValues);
		CacheUtils.putCache(StaticCode.DICT_CODE_VALUE_MAP, columnCodeMap);
		log.info("字段码值索引建立成功");
	}

}
