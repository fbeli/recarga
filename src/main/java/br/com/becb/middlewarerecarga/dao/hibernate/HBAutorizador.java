package br.com.becb.middlewarerecarga.dao.hibernate;

import org.springframework.stereotype.Repository;

import br.com.becb.middlewarerecarga.entidades.Operadora;
import br.com.becb.middlewarerecarga.entidades.PermissaoUsuario;
import br.com.becb.middlewarerecarga.entidades.Usuario;
import br.com.becb.middlewarerecarga.entidades.autorizacao.Autorizador;
import br.com.becb.middlewarerecarga.exceptions.ErroException;


@Repository("daoAutorizador")
public class HBAutorizador extends HBDAO<Autorizador> {

	public HBAutorizador() {}

	@Override
	protected Class getClazz() {
		return Autorizador.class;
	}
	
	

}
