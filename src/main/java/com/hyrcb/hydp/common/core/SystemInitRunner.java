/**
 * File Name: SystemInitRunner.java
 * Date: 2020-05-28 17:50:52
 */
package com.hyrcb.hydp.common.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import me.belucky.easytool.task.TaskInitCenter;

/**
 * Description: 系统初始化
 * @author shenzulun
 * @date 2020-05-28
 * @version 1.0
 */
@Component
public class SystemInitRunner implements CommandLineRunner{
    private static final Logger log = LoggerFactory.getLogger(SystemInitRunner.class);

	private static volatile boolean taskInitFlag = false;         //任务初始化标志

	@Override
	public void run(String... args) throws Exception {
		try {
			if(!taskInitFlag){
				taskInitFlag = true;
				TaskInitCenter.go("config/task-init-config.xml");
				log.info("系统初始化成功...");
			}
		} catch (Exception e) {
			log.error("系统初始化失败...", e);
			throw e;
		}
	}

}
