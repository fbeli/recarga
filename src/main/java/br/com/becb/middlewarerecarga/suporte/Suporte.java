package br.com.becb.middlewarerecarga.suporte;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.servlet.ModelAndView;

import br.com.becb.middlewarerecarga.entidades.Produto;

public class Suporte {

	
	public static String url;
	public static String loja;
	public static String senha;
	public static String nome;
	public static String endereco;
	
	
	/**
	 * Valor mínimo em percentual
	 */
	public static double  valorMinimo = 0.05;
	
	
	public Suporte() {
		
	}

	public static String conveterData(Date data, String formato) {

		SimpleDateFormat ft = new SimpleDateFormat(formato); 
		
		String dateFormat = ft.format(data);
		return dateFormat;
	
	}
	public static void resetSuportValues(){
		url=null;
		loja=null;
		senha=null;
		nome=null;
		valorMinimo = 0.05;
	}
	
	public static boolean margemValida(Produto p){
		if((1-(p.getPrecoCompraProduto()/p.getPrecoVendaProduto())) > valorMinimo)
			return true;
		return false;
	}

	public static String getEndereco(){
		if(null == endereco)
			endereco = PegaProperties.getProp().getProperty("msg.endereco");
		return endereco;
	}

	public static ModelAndView getInfosComprovante( ModelAndView resultado ){
		

		resultado.addObject("endereco", Suporte.getEndereco());			
		resultado.addObject("site", PegaProperties.getProp().get("msg.site"));
		resultado.addObject("promocao",PegaProperties.getProp().get("msg.promocao"));
		
		return resultado;
	}
	
}
