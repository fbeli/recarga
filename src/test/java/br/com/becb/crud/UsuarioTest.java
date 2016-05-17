package br.com.becb.crud;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.becb.middlewarerecarga.dao.hibernate.HBUsuario;
import br.com.becb.middlewarerecarga.entidades.Usuario;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.UserService;
import junit.framework.Assert;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/springTests.xml"})
public class UsuarioTest {

	@Mock
	HBUsuario hDaoUsuario;
	
	@Autowired
	@TestSubject
	UserService us;
	
	@Test
	public void getUsuario() throws ErroException{
		
		EasyMock.expect(hDaoUsuario.getUsuario("fred")).andReturn(new Usuario("fbeli","pass","frederico"));
		EasyMock.replay(hDaoUsuario);
		Usuario usuario = us.getUsuario("fred");
		Assert.assertEquals(usuario.getNome(), "frederico");
		
		
	}
	
	
}
