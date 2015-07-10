package br.com.becb.middlewarerecarga.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.exceptions.ErroException;

/**
 * Resposável por serviços de Back Office
 * @author frederico.belisario
 *
 */
@Component("BOService")
public class BOService {

	@Autowired
	TransacaoService ts;
	
	
	/**
	 * Faz o Cancelamentos das recargas pendentes afim de não serem contabilizadas no boleto.
	 */
	@SuppressWarnings("finally")
	public String cancelarRecargasPendentes(){
		
		String retorno = "Não houve cancelamento.";
		try {
			retorno = ts.cancelarRecargasPendentes();
			Logar.info(retorno);
		} catch (ErroException e) {
			
			Logar.info("\nErro ao cancelar recargas");
		}finally{
			return retorno;
		}

	}
	
}
