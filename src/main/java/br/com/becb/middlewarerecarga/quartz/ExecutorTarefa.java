package br.com.becb.middlewarerecarga.quartz;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.becb.middlewarerecarga.dao.hibernate.HBUsuario;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.AdicionarProdutosService;
import br.com.becb.middlewarerecarga.servicos.Logar;
import br.com.becb.middlewarerecarga.suporte.Suporte;

public class ExecutorTarefa {

	@Autowired
	AdicionarProdutosService aps;
	
	@Autowired
	HBUsuario hu;
	
	public void ajustarProdutos(){
		
		try {
			Logar.info("Adicionando Produtos");
			aps.adicionarProdutos();
		} catch (ErroException e) {
			Logar.erro("Problemas ao carregar lista de Produtos para o dia!");
		}
	}
	
	public void manterConexao(){
		
		try {
			hu.get(new Long(1));
			Logar.info("manter conexão com o banco " +Suporte.conveterData(new Date(), "HH:MM:ss dd mm YYYY"));
		} catch (ErroException e) {
			
		}
	}
}
