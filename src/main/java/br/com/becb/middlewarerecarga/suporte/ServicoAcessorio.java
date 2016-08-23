package br.com.becb.middlewarerecarga.suporte;

public class ServicoAcessorio {

	
	public static String getMd5(String string){
		return org.apache.commons.codec.digest.DigestUtils
				.sha256Hex(string);
	}
}
