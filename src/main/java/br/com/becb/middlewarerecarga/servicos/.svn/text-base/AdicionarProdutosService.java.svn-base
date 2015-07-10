package br.com.becb.middlewarerecarga.servicos;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.exceptions.ErroException;

@Configuration
@PropertySource("classpath:rv.properties")
@Component("adicionarProdutosService")
public class AdicionarProdutosService {

	public static Date atualizacao;
	
	
	
	@Value("${consProdOp}")
	private String adicionarProduto;
	
	
	@Autowired
	private ConsultaRV consultaRV;
	public AdicionarProdutosService() {
		// TODO Auto-generated constructor stub
	}
	

	
	public String adicionarProdutos() throws ErroException{
		String str = consultaRV.atualizarListaProdutos(adicionarProduto);
		atualizacao = new Date();
		
		return str;
	}

	
	public ConsultaRV getconsultaRV() {
		return consultaRV;
	}

	public void setconsultaRV(ConsultaRV consultaRV) {
		this.consultaRV = consultaRV;
	}

}
