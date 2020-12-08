/**
 * File Name: AbstractChainHandler.java
 * Date: 2020-07-10 20:20:16
 */
package com.hyrcb.hydp.common.chain;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hyrcb.hydp.common.utils.CommonUtils;
import com.jfinal.plugin.activerecord.Record;

/**
 * Description: 抽象链
 * @author shenzulun
 * @date 2020-07-10
 * @version 1.0
 */
public abstract class AbstractChainHandler implements IChain{
	public Logger log = LoggerFactory.getLogger(this.getClass());

	private String chainName = null;
	
	public AbstractChainHandler(String chainName) {
		this.chainName = chainName;
	}
	/**
	 * 执行逻辑
	 * @param context
	 * @throws Exception
	 */
	public abstract void execute(ChainContext context) throws Exception;
	
	/**
	 * 子任务单元
	 * @param list
	 * @param context
	 * @throws Exception
	 */
	public abstract void subTask(List<Record> list, ChainContext context) throws Exception;
	
	public void handle(ChainContext context) throws Exception{
		log.info("开始执行链条: {}", chainName);
		execute(context);
		log.info("链条[{}]执行完毕", chainName);
	}
	
	/**
	 * 并行执行
	 * @param list
	 * @param perSize
	 * @throws Exception 
	 */
	public void parallel(List<Record> list, int perSize, ChainContext context) throws Exception {
		List<List<Record>> divideList = CommonUtils.divide(list, perSize);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		CountDownLatch latch = new CountDownLatch(divideList.size());
		int i=0;
		for(List<Record> divide : divideList) {
			Thread t = new Thread("t-" + i++) {
				public void run() {
					log.info("线程[{}]开始运行...", Thread.currentThread().getName());
					try {
						subTask(divide, context);
					} catch (Exception e) {
						log.error("", e);
					}
					log.info("线程[{}]运行结束...", Thread.currentThread().getName());
					latch.countDown();
				}
			};
			executor.execute(t);
		}
		latch.await();
		executor.shutdown();
	}
	
}
