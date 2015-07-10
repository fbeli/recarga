package br.com.becb.middlewarerecarga.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.AdicionarProdutosService;
import br.com.becb.middlewarerecarga.servicos.Logar;

public class ExecutorTarefa {

	@Autowired
	AdicionarProdutosService aps;
	
	public void executeJob(){
		
		try {
			Logar.info("Adicionando Produtos");
			aps.adicionarProdutos();
		} catch (ErroException e) {
			Logar.erro("Problemas ao carregar lista de Produtos para o dia!");
		}
	}
}
