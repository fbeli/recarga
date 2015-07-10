package br.com.middlewarerecarga.tests.produtos;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.exceptions.ImpossivelCarregarProdutosException;
import br.com.becb.middlewarerecarga.servicos.ListaProdutosService;


@Component("carga")
public class Carga implements Runnable {

	
	List produtos;
	
	
	@Autowired
	ListaProdutosService listaProdutosService;
	
	public Carga() {
		
	}

	@Override
	public void run() {
		
		try {
			
			String ddd =getDDD()+"";
			String  operadora = getOperadora();
			produtos = listaProdutosService.buscarProdutos(ddd,operadora);
			System.out.println(operadora +"  "+ ddd);		
			
		} catch (ImpossivelCarregarProdutosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	private String getOperadora()
	{
		String ddds[] = new String[4];
		ddds[1] = "oi";
		ddds[2] = "tim";
		ddds[3] = "vivo";
		ddds[0] = "claro";
		
		Random r = new Random();
		String ret = ddds[r.nextInt(4)];
		return  ret;
		
	}
	private int getDDD(){
		
		int ddds[] = new int[8];
		ddds[1] = 21;
		ddds[2] = 27;
		ddds[3] = 11;
		ddds[4] = 41;
		ddds[5] = 31;
		ddds[6] = 24;
		ddds[7] = 22;
		ddds[0] = 51;
		
		Random r = new Random();
		int ret =ddds[r.nextInt(7)];
		return ret;
		
		
		
		
	}
}
