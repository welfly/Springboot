package com.tony.tt.configrations;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplictionContextUtils implements ApplicationContextAware {

	private static ApplicationContext app;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplictionContextUtils.app = applicationContext;
	}
	
	public static<T> T get(Class<T> clazz) {
		return app.getBean(clazz);
	}
	
	public static Object get(String s) {
		return app.getBean(s);
	}
}
