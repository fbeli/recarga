package br.com.becb.middlewarerecarga.dao.hibernate;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.becb.middlewarerecarga.entidades.Usuario;
import br.com.becb.middlewarerecarga.exceptions.ConfirmacaoDeTransacaoException;
import br.com.becb.middlewarerecarga.exceptions.ErroException;

@Transactional(propagation=Propagation.SUPPORTS)
@Repository("hDaoUsuario")
public class HBUsuario extends HBDAO<Usuario> {
	
	

	
	protected Class<Usuario> getClazz() {
		return Usuario.class;
	}

	public Usuario getUsuario(String login, String senha) throws ErroException {
		Session session = getSession();
		Query query = session.createQuery("from Usuario usr where usr.login = ? and usr.hashSenha = ?");
		query.setString(0, login);
		query.setString(1, DigestUtils.sha256Hex(senha));
		Usuario user= (Usuario)query.uniqueResult();		
		if (user == null)
			return null;
		
		if(user.getPermissaoUsuarios().size() == 0){
			query = this.getSession().createQuery("from PermissaoUsuario pu where pu.usuario = ?");
			query.setEntity(0, user);
			user.setPermissaoUsuarios( query.list() );
		}
		
		session.clear();
		return user;
	}

	public Usuario getUsuario(String login) throws ErroException {
		Query query = getSession().createQuery("from Usuario usr where usr.login = ?");
		query.setString(0, login);
		return (Usuario) query.uniqueResult();
	}
	
	public Usuario salvarUsuario(Usuario user) throws ConfirmacaoDeTransacaoException, ErroException{
		if(null != getUsuario(user.getLogin() ) )
				persistir(user);
		return user;
		
	}
	
	
	public List<Usuario> getUsuarios() throws ErroException{
		Criteria criteria = getSession().createCriteria(Usuario.class);
		
		return criteria.list();
		
	}
	
	
}
