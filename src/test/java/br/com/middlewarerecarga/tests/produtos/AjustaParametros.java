package br.com.middlewarerecarga.tests.produtos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AjustaParametros {

	public AjustaParametros() {
		// TODO Auto-generated constructor stub
	}
	public static String getFone(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmss");
		String date = sdf.format(new Date()); 
	

        return "9"+date;
		
	}

}
