package br.com.becb.middlewarerecarga.servicos;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.dao.hibernate.HBPermissaoUsuario;
import br.com.becb.middlewarerecarga.dao.hibernate.HBUsuario;
import br.com.becb.middlewarerecarga.entidades.PermissaoUsuario;
import br.com.becb.middlewarerecarga.entidades.Usuario;
import br.com.becb.middlewarerecarga.exceptions.ConfirmacaoDeTransacaoException;
import br.com.becb.middlewarerecarga.exceptions.ErroException;



@Component("userService") 
public class UserService {

	@Autowired
	private HBUsuario hDaoUsuario;
	
	@Autowired
	private HBPermissaoUsuario<PermissaoUsuario> hDaoPermissaoUsuario;
	
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario novoUsuario(String username, String password, String nome, String role) throws ErroException{
		Usuario user = new Usuario(username, password, nome);
		hDaoUsuario.persistir(user);
		hDaoPermissaoUsuario.addRole(role, user);
		
		user = hDaoUsuario.getUsuario(username);
		return user;
	}
	
	/**
	 * 
	 * @param role "ROLE_ANONYMOUS,ROLE_ADMIN,ROLE_SUPERVISOR,ROLE_MEMBRO, ROLE_VENDEDOR"
	 * @return
	 */
	public boolean verificaRole(String role, HttpSession sessao){
		boolean retorno = false;
		List<PermissaoUsuario> permissoes = (List<PermissaoUsuario>) sessao.getAttribute("permissoes");
		if( permissoes==null)
			return false;
		for (PermissaoUsuario permissaoUsuario : permissoes) {
			if(permissaoUsuario.getRole().equals(role)){
				return true;
			}
		}
		
		return retorno;
	}
	
	public List<Usuario> getUsuarios() throws ErroException{		
		return hDaoUsuario.getUsuarios();		
	}
	
	public Usuario atualizaUser(Usuario user) throws ConfirmacaoDeTransacaoException, ErroException{
		 hDaoUsuario.merge(user);
		return user;
		//return hDaoUsuario.salvarUsuario(user);
	}
	public Usuario getUsuario(String username, String senha) throws ErroException{
		return hDaoUsuario.getUsuario(username, senha);
	}

}
