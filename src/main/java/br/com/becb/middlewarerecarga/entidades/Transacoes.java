package br.com.becb.middlewarerecarga.entidades;

import java.util.List;

public class Transacoes {

	
	/**
	 * tipo = PIN, Online, ambos
	 */
	private String tipo;
	private double total;
	private double totalPin;
	private double totalOnline;
	private List<Recarga> recargasOnline;
	private List<Recarga> recargasPin;
	private List<Recarga> recargasNaoConfirmadas;
	
	private String dataInicio;
	private String dataFim;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getTotalPin() {
		return totalPin;
	}
	public void setTotalPin(double totalPin) {
		this.totalPin = totalPin;
	}
	public void addTotalPin(double totalPin) {
		this.totalPin += totalPin;
	}
	public double getTotalOnline() {
		return totalOnline;
	}
	public void setTotalOnline(double totalOnline) {
		this.totalOnline = totalOnline;
	}
	public void addTotalOnline(double totalOnline) {
		this.totalOnline += totalOnline;
	}
	public void addTotal(double total) {
		this.total+= total;
	}
	
	
	
	public List<Recarga> getRecargasOnline() {
		return recargasOnline;
	}
	public void setRecargasOnline(List<Recarga> recargasOnline) {
		this.recargasOnline = recargasOnline;
	}
	public List<Recarga> getRecargasPin() {
		return recargasPin;
	}
	public void setRecargasPin(List<Recarga> recargasPin) {
		this.recargasPin = recargasPin;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	public List<Recarga> getRecargasNaoConfirmadas() {
		return recargasNaoConfirmadas;
	}
	public void setRecargasNaoConfirmadas(List<Recarga> recargasNaoConfirmadas) {
		this.recargasNaoConfirmadas = recargasNaoConfirmadas;
	}
	/*public void addRecargasNaoConfirmadas(Recarga recarga) {
		if(recargasNaoConfirmadas == null)
			
	}*/
	
	
}
