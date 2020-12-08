package com.oneterrace.taskmanagement.common;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configurations {
	private static final Logger LOGGER = LoggerFactory.getLogger(Configurations.class);

	private static Properties config;

	static {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
		config = new Properties();
		try {
			config.load(is);
		} catch (Exception e) {
			LOGGER.error("Error! Initializing JDBC Pool");
		}
	}

	public static Properties getConfigs() {
		return config;
	}
}
