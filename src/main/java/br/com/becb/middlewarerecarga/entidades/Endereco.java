package br.com.becb.middlewarerecarga.entidades;

public class Endereco {

	
	private String rua;
	private String numero;
	private String complemento;
	private String  cidade;
	private String  bairro;
	private String estado;
	private String pais = "BRA";
	/**
	 * formato 01230-000
	 */
	private String cep;
	/**
	 * Formato (11)8888-8888
	 */
	private String fone;
	
	public Endereco(String rua, String numero, String complemento,
			String cidade, String bairro, String estado, String pais,
			String cep, String fone) {
		super();
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.cidade = cidade;
		this.bairro = bairro;
		this.estado = estado;
		this.pais = pais;
		this.cep = cep;
		this.fone = fone;
	}

	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

}
