/**
 * File Name: IChain.java
 * Date: 2020-07-06 10:38:27
 */
package com.hyrcb.hydp.common.chain;

/**
 * Description: 链条
 * @author shenzulun
 * @date 2020-07-06
 * @version 1.0
 */
public interface IChain {

	public void handle(ChainContext context) throws Exception;
	
}
