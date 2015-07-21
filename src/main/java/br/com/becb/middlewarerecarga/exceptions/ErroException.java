package br.com.becb.middlewarerecarga.exceptions;

import br.com.becb.middlewarerecarga.entidades.Erro;
import br.com.becb.middlewarerecarga.entidades.enums.CodErro;
import br.com.becb.middlewarerecarga.servicos.Logar;

public class ErroException extends Exception {

	Erro erro;
	
	public ErroException() {
		// TODO Auto-generated constructor stub
	}

	public ErroException(String arg0, String codigoErro, String mensagem) {
		super(arg0);
		erro = new Erro(codigoErro, mensagem);
		Logar.info(erro.getMensagem());		
		
	}
	public ErroException(Erro erro){
		
		super(erro.getMensagem());
		Logar.info(erro.getMensagem());
		this.erro = erro;
	}
	
	public Erro getErro(){
		return erro;
	}
	public void setErro(Erro erro){
		this.erro = erro;
	}
	public ErroException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ErroException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ErroException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ErroException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
