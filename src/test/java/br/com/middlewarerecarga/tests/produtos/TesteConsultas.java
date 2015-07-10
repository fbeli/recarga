package br.com.middlewarerecarga.tests.produtos;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.becb.middlewarerecarga.entidades.Produto;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.RecarregarService;

/**
 * Busca produtos ativos nas operadoras, produtos que podem ser utilizados
 * @author fred
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/springTests.xml"})
public class TesteConsultas {

	@Autowired
	RecarregarService recarregarService;
	public TesteConsultas() {
		// TODO Auto-generated constructor stub
	}
	@Test
	public void buscaOperadora() throws ErroException{
		String ddd = "21";
		String idOperadora="31";
		
		List<Produto> produtos = recarregarService.buscarProdutosAtivosParaDDDeOperadora(ddd, idOperadora);
		if(produtos != null){
			Produto prod;
			Iterator<Produto> i = produtos.iterator();
			while(i.hasNext()){
				prod = i.next();
				System.out.println("Operadora: "+prod.getOperadora().getNomeOperadora()+
						" Produto: " +prod.getCodigoProduto()+
						" DDD: "+ddd+
						" Modelo de Recarga: "+prod.getModeloRecarga()+
						" Valor Venda: "+prod.getPrecoVendaProduto());
			}
		}
		
	}
	

}
