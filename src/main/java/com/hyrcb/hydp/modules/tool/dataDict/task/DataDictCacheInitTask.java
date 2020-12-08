/**
 * File Name: DataDictCacheInitTask.java
 * Date: 2019-06-13 10:21:35
 */
package com.hyrcb.hydp.modules.tool.dataDict.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hyrcb.hydp.common.config.StaticCode;
import com.hyrcb.hydp.common.core.BeanFactory;
import com.hyrcb.hydp.modules.tool.dataDict.config.DataDictConfig;
import com.hyrcb.hydp.modules.tool.dataDict.core.TableIndexBuildTool;
import me.belucky.easytool.task.AbstractTask;
import me.belucky.easytool.util.CacheUtils;
import me.belucky.easytool.util.FileTools;
import com.hyrcb.hydp.modules.tool.dataDict.model.SystemCode;


/**
  *   功能说明：数据字典的缓存初始化
 * @author shenzl
 * @date 2019-06-13
 * @version 1.0
 */
public class DataDictCacheInitTask extends AbstractTask{

	public DataDictCacheInitTask(String taskName) {
		super(taskName);
	}

	public void execute() {
		log.info("开始建立数仓数据字典索引...");
		Map<String, String> schemaMap = CacheUtils.getCache(StaticCode.DICT_SCHEMA_MAP);
		if(schemaMap == null){
			schemaMap = new HashMap<String, String>();
		}
		DataDictConfig dictConfig = BeanFactory.getBean(DataDictConfig.class);
		String dbIndConf = dictConfig.getDataCategory();
		String[] confs = dbIndConf.split(",");
		for(String conf : confs){
			String[] arr = conf.split("\\|");
			TableIndexBuildTool.buildTableIndex(dictConfig.getDatafilePath() + arr[1],StaticCode.CACHE_PREFIX_DATA_STORAGE, arr[0]);
			schemaMap.put(StaticCode.CACHE_PREFIX_DATA_STORAGE + "_" + arr[0].toLowerCase(), arr[1]);
		}
		CacheUtils.putCache(StaticCode.DICT_SCHEMA_MAP, schemaMap);
		//开始系统分类的缓存初始化
		List<SystemCode> sysList = new ArrayList<SystemCode>();
		Map<String, String> codeMap = new HashMap<>();
		String datafilePath = dictConfig.getDatafilePath();
		List<String> lines = FileTools.getContentList(datafilePath + "system-codes.txt",true, StaticCode.DEFAULT_FILE_ENCODE);
		if(lines != null && lines.size() != 0){
			for(String line : lines) {
				String[] arr = line.split("\\|");
				sysList.add(new SystemCode(arr[0],arr[1]));
				codeMap.put(arr[0], arr[1]);
			}
		}
		CacheUtils.putCache(StaticCode.DICT_SYSTEM_CODE, sysList);
		CacheUtils.putCache(StaticCode.DICT_SYSTEM_CODE_MAP, codeMap);
		log.info("数仓数据字典索引建立成功");
	}

}
