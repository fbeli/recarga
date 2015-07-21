package br.com.middlewarerecarga.tests.produtos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.becb.middlewarerecarga.entidades.Usuario;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.UserService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/springTests.xml"})
public class TestUsuario {

	@Autowired
	UserService us;
	
	@Test
	public void bloquearUser() throws Exception{
		
		Usuario user = us.getUsuariosAtivos().get(0);
		System.out.println("user: "+user.getLogin()+" ativo: "+user.isAtivo());
		us.bloquearUsuario(user);
		System.out.println("user: "+user.getLogin()+" ativo: "+user.isAtivo());
		
		user.setAtivo(true);
		us.atualizaUser(user);
	}
}
