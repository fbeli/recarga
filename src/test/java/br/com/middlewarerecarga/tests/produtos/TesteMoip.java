package br.com.middlewarerecarga.tests.produtos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.becb.moip.MoipService;
import br.com.moip.client.send.SandboxMoip;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/springTests.xml"})
public class TesteMoip {

	
//	@Autowired
	//MoipService moipService;
	
	public TesteMoip() {
		
	}

	@Test
	public void testConexaoMoip(){
		MoipService moipService = new MoipService();
		
		SandboxMoip sbm = 	moipService.authService();
		moipService.sendXML();
	}
	
	@Test
	public void testCURL(){
		

	}
	
	
}
