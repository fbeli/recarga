package br.com.becb.middlewarerecarga.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.sql.rowset.serial.SerialArray;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.becb.middlewarerecarga.entidades.autorizacao.Autorizador;
import br.com.becb.middlewarerecarga.suporte.ServicoAcessorio;

@Entity
@Table(name = "usuario")
public class Usuario implements java.io.Serializable {

	@Id
	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	protected long id;

	public long getId() {
		return id;
	}

	public void setId(long valor) {
		this.id = valor;
	}

	@NotNull
	@NotEmpty
	@Column(name = "nome", nullable = false, length = 128)
	private String nome;
	
	@Column(name = "ativo", nullable = false, length = 128)	
	private boolean ativo;
	
	@Column(name = "email", nullable = false, length = 128, unique = true)
	private String email;
	@NotNull
	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro = new Date();
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<PermissaoUsuario> permissaoUsuarios;
	
	@NotNull
	@NotEmpty
	@Size(min = 8, max = 32, message = "Login muito curto ou muito longo")
	@Column(name = "login", nullable = false, unique = true, length = 64)
	private String login;
	

	private transient String senha;
	@Column(name = "ultimo_login", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimoLogin;

	@Column(name = "hash_senha", nullable = false, length = 128)
	private String hashSenha;

	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER) 
	@IndexColumn(name="ID_AUTORIZADOR") 
	private List<Autorizador> autorizacoes; 
	
	public Usuario(String login, String password, String nome){
		setNome(nome);
		setLogin(login);
		setSenha(password);
	}
	public Usuario(){}
	
	
	public String getHashSenha() {
		return hashSenha;
	}

	public void setHashSenha(String valor) {
		hashSenha = valor;
	}

	public Date getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Date data) {
		ultimoLogin = data;
	}

	public String getLogin() {
		return login;
	}
	
		public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return getHashSenha();
	}

	public void setSenha(String senha) {
		setHashSenha(ServicoAcessorio.getMd5(senha));
		
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<PermissaoUsuario> getPermissaoUsuarios() {
		return permissaoUsuarios;
	}


	public void setPermissaoUsuarios(List<PermissaoUsuario> permissaoUsuarios) {
		this.permissaoUsuarios = permissaoUsuarios;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
