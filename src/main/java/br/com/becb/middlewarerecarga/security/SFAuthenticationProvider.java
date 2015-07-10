package br.com.becb.middlewarerecarga.security;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import br.com.becb.middlewarerecarga.dao.hibernate.HBPermissaoUsuario;
import br.com.becb.middlewarerecarga.entidades.PermissaoUsuario;
import br.com.becb.middlewarerecarga.entidades.Usuario;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.AdicionarProdutosService;
import br.com.becb.middlewarerecarga.servicos.UserService;

/**
 * Exemplo de authentication provider
 * 
 * 
 */
public class SFAuthenticationProvider implements AuthenticationProvider {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HBPermissaoUsuario<PermissaoUsuario> daoPermissao;

	@Autowired
	private AdicionarProdutosService AdicionarProdutosService;

	@Autowired(required = true)
	private HttpServletRequest request;

	public Authentication authenticate(Authentication auth) {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) auth;
		String username = token.getName();
		String senha = token.getCredentials() != null ? token.getCredentials()
				.toString() : null;
		Usuario usuario;
		SFAuthentication resultado = null;
		try {
			usuario = userService.getUsuario(username, senha);

			if (usuario == null) {
				return null;
			}
			
			
			List<PermissaoUsuario> permissoes = usuario.getPermissaoUsuarios();
			resultado = new SFAuthentication(usuario, permissoes);
			resultado.setAuthenticated(usuario != null);

			request.getSession().setAttribute("permissoes", permissoes);
			request.getSession().setAttribute("usuario", usuario);
			request.getSession().setAttribute("username",
					usuario.getNome() + "  ");
			
			usuario.setUltimoLogin(new Date());
			userService.atualizaUser(usuario);
			
		} catch (ErroException e) {

			e.printStackTrace();

		}
		return resultado;
	}

	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication));
	}

}
