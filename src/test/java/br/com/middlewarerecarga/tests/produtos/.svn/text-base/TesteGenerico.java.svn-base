package br.com.middlewarerecarga.tests.produtos;

import java.io.File;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.becb.middlewarerecarga.entidades.rv.jax.ConversoesRV;
import br.com.becb.middlewarerecarga.servicos.ConsultaRV;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:springTests.xml"})
public class TesteGenerico {

		@Autowired
	ConsultaRV consultaRV;
	
	public TesteGenerico() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testeRecargaOnline(){
	
		File file = new File("teste");
		System.out.println(file.getAbsolutePath());
		consultaRV.getCodigoTransacao();
	}
	
	@Test
	public void conversor(){
	
		System.out.println(ConversoesRV.converterDataRV(new Date(), "YYYYMMDD"));
	}

}
