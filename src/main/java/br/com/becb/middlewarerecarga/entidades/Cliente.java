package br.com.becb.middlewarerecarga.entidades;

/**
 * Utilizado para pagamento MOIP
 * 
 */
public class Cliente {
 

	private int id;
	private String nome;
	private String email;
	
	private Endereco endereco;
	
	

	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	public Cliente(int id, String nome, String email, Endereco endereco) {
		super();
		this.id = id;
		nome = nome;
		this.email = email;
		this.endereco = endereco;
	}
	
	
	
	public Cliente(int id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
