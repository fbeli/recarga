package br.com.middlewarerecarga.tests.produtos;


import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateDDDProduto;
import br.com.becb.middlewarerecarga.dao.hibernate.HibernateOperadora;
import br.com.becb.middlewarerecarga.dao.hibernate.HibernateProduto;
import br.com.becb.middlewarerecarga.entidades.DDDProduto;
import br.com.becb.middlewarerecarga.entidades.Operadora;
import br.com.becb.middlewarerecarga.entidades.Produto;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.cliente.rv.RecargaParseXML;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/springTests.xml"})
public class TestConsultaProduto  extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private RecargaParseXML parse;

	@Autowired
	private HibernateOperadora<Operadora> hDaoOperadora;
	@Autowired
	private HibernateProduto<Produto> hDaoproduto;

	@Autowired
	private HibernateDDDProduto<DDDProduto> hDaoDDDProduto;
	
	
	
	public TestConsultaProduto() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testParseOperadoras(){
	
				
		try {
			Scanner scanner = new Scanner(new File("C:\\Java\\workspace\\middlewarerecarga\\operadorasTest.xml"));
			String xml = scanner
			             .useDelimiter("\\Z").next();
		System.out.println(xml);
		List<Operadora> operadoras = parse.parseOperadora(xml);
	
		assertTrue("Salvo com sucesso",true);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void buscarPorDDD() throws ErroException{
	
		int ddd = 68;
		DDDProduto dddP = hDaoDDDProduto.buscarDDD(ddd);
		System.out.println("Para o ddd "+ddd+" temos os Produtos");
		if(dddP != null)
		for(int i=0;i<dddP.getProdutos().size();i++){

			System.out.println(dddP.getProdutos().get(i).getOperadora().getNomeOperadora()+
					" "+dddP.getProdutos().get(i).getCodigoProduto()+
					" venda: "+dddP.getProdutos().get(i).getPrecoVendaProduto()+
					" valor pago: "+dddP.getProdutos().get(i).getPrecoCompraProduto()+
					" % ganho: "+ (1-dddP.getProdutos().get(i).getPrecoCompraProduto()/dddP.getProdutos().get(i).getPrecoVendaProduto()));
		}
	}
	private boolean salvarOperadoras(List<Operadora> operadoras ){
		try{
			
			
			for(int i =0 ;i<operadoras.size();i++)
				hDaoOperadora.persistir(operadoras.get(i));
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
