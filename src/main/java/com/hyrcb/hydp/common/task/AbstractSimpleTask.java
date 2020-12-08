/**
 * File Name: AbstractSimpleTask.java
 * Date: 2018-3-1 上午11:35:26
 */
package com.hyrcb.hydp.common.task;

import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 功能说明: 任务抽象基类
 * @author shenzulun
 * @date 2018-3-1
 * @version 1.0
 */
public abstract class AbstractSimpleTask<T> implements Callable<T>{
	protected Logger log = LoggerFactory.getLogger(getClass());

	private String threadName = null;
	
	private Object object = null;
	
	/**
	 * 执行具体的任务逻辑
	 */
	public abstract T exec(Object object);
	
	public AbstractSimpleTask(String ThreadName, Object object){
		this.threadName = ThreadName + "-" + System.currentTimeMillis();
		this.object = object;
	}
	
	public T call(){
		log.info("开始执行线程: {}", threadName);
		T result = exec(object);
		log.info("线程[{}]执行完毕", threadName);
		return result;
	}

	public String getThreadName() {
		return threadName;
	}
	
}