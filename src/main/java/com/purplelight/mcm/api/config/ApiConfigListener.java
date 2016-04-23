package com.purplelight.mcm.api.config;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApiConfigListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ConfigUtil.msg = new Properties();
		ConfigUtil.config = new Properties();
		try{
			String path = (getClass().getClassLoader().getResource("").toURI()).getPath();
			
			FileInputStream msgFis = new FileInputStream(path + "apiMessages.properties");
			ConfigUtil.msg.load(msgFis);
			
			FileInputStream cfgFis = new FileInputStream(path + "config.properties");
			ConfigUtil.config.load(cfgFis);
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
