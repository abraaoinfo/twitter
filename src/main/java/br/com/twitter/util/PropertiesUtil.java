package br.com.twitter.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	
	
	
	private   Properties getConfig()  {
		Properties props = new Properties();
	    InputStream file = getClass().getClassLoader().getResourceAsStream("twitter4j.properties"); 	 
		try {
			props.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props;

	}
	
	
	public static Properties getProp (){
		
		PropertiesUtil util = new PropertiesUtil();
		return util.getConfig();
	}
	
	

}
