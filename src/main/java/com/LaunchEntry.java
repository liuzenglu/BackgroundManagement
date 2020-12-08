/**
 * File Name: LaunchEntry.java
 * Date: 2020-05-14 15:49:34
 */
package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

/**
 * Description: 启动入口
 * @author shenzulun
 * @date 2020-05-14
 * @version 1.0
 */
@SpringBootApplication(exclude = { DruidDataSourceAutoConfigure.class })
public class LaunchEntry {
    private static final Logger log = LoggerFactory.getLogger(LaunchEntry.class);

	
	public static void main(String[] args){
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(LaunchEntry.class, args);
        log.info("===============================================启动成功===============================================");
    }
}
