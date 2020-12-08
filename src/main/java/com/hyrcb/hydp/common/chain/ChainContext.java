/**
 * File Name: RequestParam.java
 * Date: 2020-07-06 10:40:21
 */
package com.hyrcb.hydp.common.chain;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Description: 上下文
 * @author shenzulun
 * @date 2020-07-06
 * @version 1.0
 */
public class ChainContext {
	
	private String chainName;
	
	private ConcurrentMap<String, Object> context = new ConcurrentHashMap<>();
	
	public Object get(String key) {
		return context.get(key);
	}
	
	public void set(String key, Object value) {
		context.put(key, value);
	}

	public String getChainName() {
		return chainName;
	}

	public void setChainName(String chainName) {
		this.chainName = chainName;
	}
	
}
