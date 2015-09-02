package br.com.becb.moip;

import java.util.List;

import br.com.moip.client.response.Error;

public class MoipServiceResponse {

	
	
	private boolean resultado;
	private String token;
	private List<br.com.moip.client.response.Error> errors;
	
	
	public MoipServiceResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public MoipServiceResponse(boolean resultado, String token,
			List<Error> errors) {
		super();
		this.resultado = resultado;
		this.token = token;
		this.errors = errors;
	}
	
	
	
	public boolean isResultado() {
		return resultado;
	}
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<br.com.moip.client.response.Error> getErrors() {
		return errors;
	}
	public void setErrors(List<br.com.moip.client.response.Error> errors) {
		this.errors = errors;
	}

}
