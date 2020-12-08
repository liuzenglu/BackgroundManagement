/**
 * File Name: BeanFactory.java
 * Date: 2020-05-28 17:15:38
 */
package com.hyrcb.hydp.common.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Description: Bean工厂
 * @author shenzulun
 * @date 2020-05-28
 * @version 1.0
 */
@Component
public class BeanFactory implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext; 

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		BeanFactory.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
	
	/**
	 * 手动获取Bean
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T>T getBean(Class<T> clazz){
        return  applicationContext.getBean(clazz);        
    }
	
	/**
	 * 手动获取Bean
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }
	

}
