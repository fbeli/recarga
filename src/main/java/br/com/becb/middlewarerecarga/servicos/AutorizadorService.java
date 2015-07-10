package br.com.becb.middlewarerecarga.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.dao.hibernate.HBAutorizador;
import br.com.becb.middlewarerecarga.entidades.autorizacao.Autorizador;
import br.com.becb.middlewarerecarga.exceptions.ErroException;


@Component("AutorizadorService")
public class AutorizadorService {

	
	@Autowired
	HBAutorizador HDaoAutorizador;
	
	public AutorizadorService() {
	}
	
	public void salvar(Autorizador aut) throws ErroException{
		HDaoAutorizador.persistir(aut);
	}

}
