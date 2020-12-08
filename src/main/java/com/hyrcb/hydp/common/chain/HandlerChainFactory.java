/**
 * File Name: HandlerChainFactory.java
 * Date: 2020-07-10 19:52:13
 */
package com.hyrcb.hydp.common.chain;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 
 * @author shenzulun
 * @date 2020-07-10
 * @version 1.0
 */
public class HandlerChainFactory {
    private static final Logger log = LoggerFactory.getLogger(HandlerChainFactory.class);
	
	public static class ChainHandle{
		
		private List<IChain> chains = new ArrayList<>();
		
		private ChainContext context;
		
		public void start() throws Exception {
			if(chains == null || chains.size() == 0) {
				log.info("找不到任何chain,请检查...");
				return;
			}
			for(IChain chain : chains) {
				chain.handle(context);
			}
		}
		
		public ChainHandle(ChainContext context) {
			this.context = context;
		}
		
		public ChainHandle addHandler(IChain chain) {
			chains.add(chain);
			return this;
		}
		
	}

	public static ChainHandle build(ChainContext context) {
		return new ChainHandle(context);
	}
	
	public static ChainHandle build(String chainName) {
		ChainContext context = new ChainContext();
		context.setChainName(chainName);
		return new ChainHandle(context);
	}
	
	public static ChainHandle build(String chainName, String key, String value) {
		ChainContext context = new ChainContext();
		context.setChainName(chainName);
		context.set(key, value);
		return new ChainHandle(context);
	}
	
}
