package br.com.middlewarerecarga.tests.produtos;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.becb.middlewarerecarga.entidades.Usuario;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.UserService;
import br.com.becb.middlewarerecarga.suporte.ServicoAcessorio;
import junit.framework.Assert;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/springTests.xml"})
public class UsuarioTesteIntegracao {

	@Autowired
	UserService us;
	
	
	long id;
	String login;
	String senhaHash;
	String senha;
	Usuario user;
	
	
	@Before
	public void getValores() throws ErroException{
	
		List<Usuario> usuarios = us.getUsuariosAtivos();
		user = usuarios.get(0);
		login = user.getLogin();
		id = user.getId();
		senhaHash = user.getHashSenha();
		senha = user.getSenha();
	}
	
	
	@Test
	public void bloquearUser() throws Exception{
		
		Usuario usuario;
		Usuario usuario1 = null;
		
		List<Usuario> usuarios = us.getUsuariosAtivos();
		if(null != usuarios && usuarios.size()>0){
			usuario = usuarios.get(0);
			us.bloquearUsuario(usuario);
			
			usuario1 = us.getUsuarioById(usuario.getId());
			
			Assert.assertEquals(usuario1.isAtivo(), false);
		}
		if(null != usuario1){
			us.liberarUsuario(usuario1);
			Assert.assertEquals(usuario1.isAtivo(), true);
		}
			
	}
	
	@Test
	public void getUsuarioAtivos() throws ErroException{
	
		List<Usuario> users = us.getUsuariosAtivos();		
		for (Usuario user : users) {
			Assert.assertEquals(user.isAtivo(), true);
		}
	}
	
	@Test
	public void getUsuarioByLogin() throws ErroException{
		
		Usuario user = us.getUsuarioByLogin(this.login);		
		Assert.assertEquals(user.getId(), this.user.getId());
	}
	

	@Test
	public void getUsuarioById() throws ErroException{
		
		Usuario user = us.getUsuarioById(this.id);		
		Assert.assertEquals(user.getNome(), this.user.getNome());
	}
	
	@Test
	public void alterarSenha() throws ErroException{
		
		String senha = "senhaNova";
		String username = "mauricio";
		
		us.alteraSenha(username, senha);
		
		Usuario user = us.getUsuarioByLogin(username);
		
		user = us.getUsuarioByLogin(username);
		
		Assert.assertEquals(ServicoAcessorio.getMd5( senha ), user.getSenha());		
	
	}
	
	
}
