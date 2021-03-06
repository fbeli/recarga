package br.com.becb.middleware.controladores;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.becb.middlewarerecarga.entidades.EntityFabric;
import br.com.becb.middlewarerecarga.entidades.Recarga;
import br.com.becb.middlewarerecarga.entidades.Transacoes;
import br.com.becb.middlewarerecarga.entidades.Usuario;
import br.com.becb.middlewarerecarga.entidades.enums.CodErro;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.RecarregarService;
import br.com.becb.middlewarerecarga.servicos.TransacaoService;
import br.com.becb.middlewarerecarga.servicos.UserService;
import br.com.becb.middlewarerecarga.suporte.PegaProperties;
import br.com.becb.middlewarerecarga.suporte.Suporte;

@Controller
public class TransacaoController {

	@Autowired
	RecarregarService recarregarService;
	@Autowired
	TransacaoService transacaoService;
	
	
	@RequestMapping(value = "admin/verificaTransacao")
	public ModelAndView verificarTransacao(
			@RequestParam(required = false, value = "ddd") String ddd,
			@RequestParam(required = false, value = "fone") String fone) {

		ModelAndView resultado = new ModelAndView();

		List<Recarga> recargas;
		try {
			recargas = transacaoService.getRecargaPorFone(ddd, fone);
			resultado.addObject("recargas", recargas);
			resultado.setViewName("listaTransacoes");
			
		} catch (ErroException e) {
			resultado.addObject("erro", e);

			resultado.setViewName("erroRecarga");
		}

		return resultado;
	}

	@RequestMapping(value = "admin/listarTransacaoPin")
	public ModelAndView verificarTransacao() {

		ModelAndView resultado = new ModelAndView();

		List<Recarga> recargas = null;
		try {
			recargas = transacaoService.getRecargaPorPin();

		} catch (ErroException e) {
			resultado.addObject("erro", e);

			resultado.setViewName("erroRecarga");

		}

		resultado.addObject("recargas", recargas);
		resultado.setViewName("listaTransacoes");
		return resultado;
	}

	@RequestMapping(value = "admin/listarTransacaoPorId")
	public ModelAndView verificarTransacao(
			@RequestParam(required = false, value = "id") String id) {

		ModelAndView resultado = new ModelAndView();

		Recarga recarga;
		try {
			recarga = recarregarService.getRecargaById(id);
			
			resultado.addObject("recarga", recarga);
			
			resultado = Suporte.getInfosComprovante(resultado);
		if(null == recarga){
			
			
			resultado.addObject("erro", EntityFabric.createErro(CodErro.RECARGANAOENCONTRADA, "Recarga "+id+" não localizada.")	);
			resultado.setViewName("erro");
			
		}else{
			if (null != recarga.getPin() && recarga.getPin() != "") {
				resultado.setViewName("confirmacaoDeRecargaPIN");
			} else
				resultado.setViewName("confirmacaoDeRecarga");
			}
		} catch (ErroException e) {
			e.getErro().setMensagem("Erro ao listar recarga. \n"+e.getErro().getMensagem());
			resultado.addObject("erro", e);
			resultado.setViewName("erroRecarga");
			
		}finally{
			return resultado;
		}

	}
	
	@RequestMapping(value = "admin/listarTransacaoPendente")
	public ModelAndView verificarTransacaoPendente() {

		ModelAndView resultado = new ModelAndView();

		List<Recarga> recargas = null;
		try {
			recargas = transacaoService.buscaTransacoesPendentes();

		} catch (ErroException e) {
			resultado.addObject("erro", e);

			resultado.setViewName("erroRecarga");

		}

		resultado.addObject("recargas", recargas);
		resultado.setViewName("listaTransacoes");
		return resultado;
	}
	
	@RequestMapping(value = "admin/verificaTransacaoPorUsuario")
	public ModelAndView verificarTransacaoPorUsuario(
			@RequestParam(required = false, value = "nome") String nome,
			@RequestParam(required = false, value = "dataFim") String dataFim,
			@RequestParam(required = false, value = "dataInicio") String dataInicio,
			@RequestParam(required = false, value = "hoje") boolean hoje,
			HttpSession sessao) throws ErroException {

		ModelAndView resultado = new ModelAndView();


		
		Transacoes transacoes = null;
		if(hoje)
			dataInicio = dataFim = Suporte.conveterData(new Date(), "dd/MM/yyyy");
			
		if(!dataInicio.equals(dataFim))
			transacoes = transacaoService.getRecargaPorUserEData(nome, getDate(dataInicio), getDate(dataFim));
		else
			transacoes = transacaoService.getRecargaPorUserNaData(nome, getDate(dataInicio));
		
		transacoes.setDataFim(dataFim);
		transacoes.setDataInicio(dataInicio);
		
		resultado.addObject("transacoes", transacoes);
		resultado.setViewName("listaTransacoes2");
		
		return resultado;
		
	}
	
	/**
	 * 
	 * @param data dd/mm/yyyy
	 * @return
	 */
	private Date getDate(String data){
		
		int dia =Integer.parseInt(data.substring(0, 2));
		int mes =Integer.parseInt( data.substring(3,5))-1;
		int ano =Integer.parseInt( data.substring(6,10))-1900;
		Date datar = new Date (ano,mes,dia);
		
		return datar;
	}

}
