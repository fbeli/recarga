package br.com.middlewarerecarga.tests.produtos;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.becb.middlewarerecarga.entidades.Recarga;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.TransacaoService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/springTests.xml"})
public class TesteTransacao {

	@Autowired
	TransacaoService ts;
	
	
	public TesteTransacao() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testTransacoes() throws ErroException{
		
	List<Recarga> transacoes = ts.buscaTransacoesPendentes();	
	for(int i =0;i<transacoes.size();i++)
		System.out.println(transacoes.get(i).getCodOnline());
	
	}
	//@Test
	public void testBuscaStatus() throws ErroException{
		
		String idRecargaString= "2007";
		String codOnline = "513097";
		
		System.out.println(codOnline+"\n"+ts.buscaStatusTransacao_CodOnline(codOnline));
		System.out.println(idRecargaString+"\n"+ts.buscaStatusTransacao_idRecarga(idRecargaString));
		
	
	}

}
