package br.com.becb.middlewarerecarga.suporte;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PegaProperties {

	
	  @SuppressWarnings("finally")
	public static Properties getProp()  {
	        Properties props = new Properties();
	        FileInputStream file;
			try {
				String dir = System.getenv("catalina_home");
				file = new FileInputStream(dir+"/conf/recarga.properties");
				props.load(file);
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	       finally{
	        return props;
	       }
	    }
}
