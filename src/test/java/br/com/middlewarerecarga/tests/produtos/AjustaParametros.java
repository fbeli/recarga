package br.com.middlewarerecarga.tests.produtos;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class AjustaParametros {

	public AjustaParametros() {
		// TODO Auto-generated constructor stub
	}
	public static String getFone(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmss");
		String date = sdf.format(new Date()); 
	

        return "9"+date;
		
       
	}
	 @Test
     public void testeContexto(){
     	File file = new File("coringa");
     	System.out.println(file.getAbsolutePath());
     }

}
