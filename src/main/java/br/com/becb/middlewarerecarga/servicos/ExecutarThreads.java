package br.com.becb.middlewarerecarga.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service("executarThreads")
@Scope("singleton")
public class ExecutarThreads {

	//@Autowired
//	private ThreadManterConexao manter;
	public ExecutarThreads(){
		
	}
	public void excutarDBConnection() throws InterruptedException{
/*		 manter =  new ThreadManterConexao();
		manter.start();
		while (true) {
			long start = System.currentTimeMillis();
			manter.sleep(2000);*/
			
		}
		
	}
	

