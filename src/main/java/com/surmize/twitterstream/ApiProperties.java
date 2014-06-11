package com.surmize.twitterstream;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiProperties {
private static Properties properties;
    
    private ApiProperties() {
    }
    
    private synchronized static void initialize() {
        try {
            properties = new Properties();
            //TODO get property file based on environment variable
            properties.load(ApiProperties.class.getResourceAsStream("/twitterapi.properties"));
        } catch (IOException ex) {
            Logger.getLogger(ApiProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getConsumerKey(){
        return getSetting("consumerKey");
    }

    public static String getConsumerSecret(){
        return getSetting("consumerSecret");
    }
    
    public static String getToken(){
        return getSetting("token");
    }
    
    public static String getSecret(){
        return getSetting("secret");
    }
    
    public static String getSetting(String key, String defaultValue) {
    	if( propertyFile() == null ) {
    		initialize();
    	}
        return propertyFile().getProperty(key, defaultValue);
    }

    public static String getSetting(String key) {
        return ApiProperties.getSetting(key, null);
    }
    
   private static Properties propertyFile(){
    	return properties;
    }
    
    public static void reload() {
    	initialize();
    }
}
