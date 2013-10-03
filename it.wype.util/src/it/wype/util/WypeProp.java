package it.wype.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WypeProp {

	private static Properties props;

	private static FileInputStream fis;
	
	private static FileOutputStream fos;

	public static String parse(String propName, String attribute){
		
		props = new Properties();
		try {
			fis = new FileInputStream(propName);
			props.load(fis);
			fis.close();
			return props.getProperty(attribute);
		} catch (IOException e) {
			WypeProp.write(propName, attribute, "");
			return "";
		}
	}
	
	public static void write(String propName, String attribute, String value){
		props.setProperty(attribute, value);
		try {
			fos = new FileOutputStream(propName);
			props.store(fos, null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
