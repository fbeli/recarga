package br.com.becb.middlewarerecarga.entidades.autorizacao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.IndexColumn;

import br.com.becb.middlewarerecarga.entidades.Recarga;
import br.com.becb.middlewarerecarga.entidades.Usuario;

@Entity
@Table(name = "autorizacao")
public class Autorizador implements Serializable{


	@Id
	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	protected long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	
	@Fetch(FetchMode.JOIN)
	Usuario usuarioAutorizador;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date dataDeAutorizacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date dataDeRecebimento;
	int codigoDeAutorizacao;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	Recarga recarga;
	
	public Autorizador() {
		// TODO Auto-generated constructor stub
	}
	public Autorizador(Usuario user, Recarga recarga){
		this.recarga = recarga;
		this.usuarioAutorizador = user;
		dataDeAutorizacao = new Date();
	}

	/*public Usuario getUsuarioAutorizador() {
		return usuarioAutorizador;
	}

	public void setUsuarioAutorizador(Usuario usuarioAutorizador) {
		this.usuarioAutorizador = usuarioAutorizador;
	}*/

	public Date getDataDeAutorizacao() {
		return dataDeAutorizacao;
	}

	public void setDataDeAutorizacao(Date dataDeAutorizacao) {
		this.dataDeAutorizacao = dataDeAutorizacao;
	}

	

	public int getCodigoDeAutorizacao() {
		return codigoDeAutorizacao;
	}

	public void setCodigoDeAutorizacao(int codigoDeAutorizacao) {
		this.codigoDeAutorizacao = codigoDeAutorizacao;
	}

	public Recarga getRecarga() {
		return recarga;
	}

	public void setRecarga(Recarga recarga) {
		this.recarga = recarga;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDataDeRecebimento() {
		return dataDeRecebimento;
	}
	public void setDataDeRecebimento(Date dataDeRecebimento) {
		this.dataDeRecebimento = dataDeRecebimento;
	}

}
