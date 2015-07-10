package br.com.becb.middleware.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.becb.middlewarerecarga.entidades.EntityFabric;
import br.com.becb.middlewarerecarga.entidades.Produto;
import br.com.becb.middlewarerecarga.entidades.Recarga;
import br.com.becb.middlewarerecarga.entidades.StatusRecarga;
import br.com.becb.middlewarerecarga.entidades.StatusRecargaServer;
import br.com.becb.middlewarerecarga.entidades.Usuario;
import br.com.becb.middlewarerecarga.entidades.autorizacao.Autorizador;
import br.com.becb.middlewarerecarga.entidades.enums.CodErro;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.exceptions.ImpossivelCarregarProdutosException;
import br.com.becb.middlewarerecarga.servicos.AutorizadorService;
import br.com.becb.middlewarerecarga.servicos.ErroService;
import br.com.becb.middlewarerecarga.servicos.ListaProdutosService;
import br.com.becb.middlewarerecarga.servicos.RecarregarService;
import br.com.becb.middlewarerecarga.servicos.UserService;
import br.com.becb.middlewarerecarga.suporte.PegaProperties;
import br.com.becb.middlewarerecarga.suporte.Suporte;

@Controller
public class RecargasController {

	@Autowired
	ListaProdutosService listaProdutosService;

	@Autowired
	RecarregarService recarregarService;

	@Autowired(required = true)
	private HttpServletRequest request;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AutorizadorService autorizadorService;

	@Autowired
	private ErroService erroService;

	public RecargasController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/admin/cancelarRecarga")
	public ModelAndView cancelarRegarga(
			@RequestParam(required = false, value = "id") String id,
			HttpSession sessao) {

		ModelAndView resultado = new ModelAndView();

		Recarga recarga = null;
			

		try {

			recarga = recarregarService.getRecargaById(id);
			
			if(recarga.getStatusRecarga()== StatusRecarga.EFETUADO && recarga.getStatusRecargaServer().equals(StatusRecargaServer.CONFIRMADO)){
				
				resultado.addObject("mensagem", "Regarga "+recarga.getCodOnline()+ " já efetuada, não é possível cancelar");
				resultado.addObject("recarga", recarga);
				resultado.setViewName("admin");
			
			}else{
				resultado.setViewName("admin");
				
				recarga = recarregarService.confirmarRecarga(recarga, "1");
				if(recarga.getStatusRecarga() == StatusRecarga.CANCELADO)
					resultado.addObject("mensagem", "Recarga "+recarga.getCodOnline()+" cancelada com sucesso");
				else
					resultado.addObject("mensagem", "Não foi possível cancelar regarga "+recarga.getCodOnline() +" Erro ao tentar cancelar");
					
				resultado.addObject("recarga", recarga);
		
			}

		} catch (ErroException e) {
			resultado.addObject("erro", e);
			if(e.getErro() == null){
				EntityFabric.createErro(CodErro.IMPOSSIVELCANCELAR, "Impossível cancelar recarga " +id);
			}
			resultado.addObject("recarga", recarga);
			resultado.addObject("mensagem", "Não foi possível cancelar regarga "+recarga.getCodOnline() +" Erro ao tentar cancelar");
			resultado.setViewName("erroRecarga");
			e.printStackTrace();
		} finally {
			sessao.removeAttribute("recarga");
		}
		
		return resultado;
	}

	/**
	 * Usado para finalizar recarga por vendedores.
	 * @param recarga
	 * @param sessao
	 * @return
	 */
	@RequestMapping(value = "/finalizarRecarga")
	public ModelAndView cadastrarRegarga(
			@RequestParam(required = false, value = "recarga") Recarga recarga,
			HttpSession sessao) {

		ModelAndView resultado = new ModelAndView();
		
		if (recarga == null)
			recarga = (Recarga) sessao.getAttribute("recarga");
		
		if (recarga == null){
			resultado.setViewName("index");
			return resultado;
		}
			
		try {

			Autorizador aut = new Autorizador(
					((Usuario) sessao.getAttribute("usuario")),recarga);
			autorizadorService.salvar(aut);
			recarga.setAutorizador(aut);
			recarga = recarregarService.confirmarRecarga(recarga, "0");
			resultado.addObject("recarga", recarga);

			resultado = Suporte.getInfosComprovante(resultado);
			
			if (null != recarga.getPin() && recarga.getPin() != "") {
				resultado.setViewName("confirmacaoDeRecargaPIN");
			} else
				resultado.setViewName("confirmacaoDeRecarga");

		} catch (ErroException e) {
			resultado.addObject("erro", e);
			if(e.getErro() == null)
				e.setErro( EntityFabric.createErro(CodErro.IMPOSSIVELFINALIZARRECARGA, "Erro ao tentar finalizar a recarga"));
			resultado.addObject("recarga", recarga);
			resultado.setViewName("erroRecarga");
			e.printStackTrace();
		} finally {
			sessao.removeAttribute("recarga");
		}

		return resultado;

	}

	/**
	 * 
	 * Solicita a recarga e Autoriza automaticamente
	 * 
	 * 
	 * @param operadora
	 * @param ddd
	 * @param fone
	 * @param valor
	 *            Recebido do form como codProduto
	 * @param sessao
	 * @return
	 */
	@RequestMapping(value = "/solicitarRecarga")
	public ModelAndView solicitarRecarga(
			@RequestParam(required = false, value = "operadora") String operadora,
			@RequestParam(required = false, value = "ddd") String ddd,
			@RequestParam(required = false, value = "fone") String fone,
			@RequestParam(required = false, value = "codProduto") String valor,
			HttpSession sessao) {

		ModelAndView resultado = new ModelAndView();
		Map<String, Produto> mapProduto = (Map<String, Produto>) sessao.getAttribute("produtos");
		

		if (valor == null || valor == "") {
			resultado.setViewName("index");
			return resultado;
		}

		Produto produto = mapProduto.get(Float.parseFloat(valor) + "");
		Recarga recarga = null;

		String usuario = "";
		if (((Usuario) sessao.getAttribute("usuario")) != null)
			usuario = ((Usuario) sessao.getAttribute("usuario")).getNome();

		try {
			recarga = recarregarService.executarRecarga(
					produto.getCodigoProduto(), ddd, fone,
					request.getRemoteAddr(), usuario);

			if (userService.verificaRole("ROLE_VENDEDOR", sessao))
				resultado.setViewName("redirect:/finalizarRecarga");
			else
				if((userService.verificaRole("ROLE_SUB_VENDEDOR", sessao)))
					resultado.setViewName("buscaVendedor");
				else
					resultado.setViewName("tipoPagamento");

		} catch (ErroException e) {

			resultado.addObject("erro", e);
			resultado.addObject("recarga", recarga);
			resultado.setViewName("erroRecarga");
			e.printStackTrace();

		}

		sessao.setAttribute("recarga", recarga);
		resultado.addObject("recarga", recarga);
		// Se logado vai direto pra página

		return resultado;
	}

	@RequestMapping(value = "/buscarRecargasDisponiveis")
	public ModelAndView buscarRecargasDisponiveis(
			@RequestParam(required = false, value = "operadora") String operadora,
			@RequestParam(required = false, value = "ddd") String ddd,
			@RequestParam(required = false, value = "fone") String fone,
			HttpSession sessao) throws InterruptedException  {
		ModelAndView resultado = new ModelAndView();
	
		if(null == operadora || null==ddd ||null == fone ){
			resultado.setViewName("index");
			return resultado;
		}

		List<Produto> produtos = null;
		operadora = operadora.toLowerCase();
		
		try {
			produtos = listaProdutosService.buscarProdutos(ddd, operadora);
		} catch (ImpossivelCarregarProdutosException e) {

			resultado.addObject("erro", e);
			resultado.setViewName("erroRecarga");
			try{
				Thread.sleep(500);
				
				produtos = listaProdutosService.buscarProdutos(ddd, operadora);
			}catch(ImpossivelCarregarProdutosException e2){
				return resultado;
			}
		}

		if (produtos == null) {

			Exception e = new ErroException( erroService.createErro("33",
					"Impossível carregar produtos"));
			resultado.addObject("erro", e);
			resultado.setViewName("erroRecarga");

		} else {

			Map<String, Produto> mapProdutos = new HashMap<String, Produto>();
			for (int i = 0; i < produtos.size(); i++) {
				mapProdutos.put(produtos.get(i).getValorMaximoProduto() + "",
						produtos.get(i));
				
			//	System.out.println(produtos.get(i).getOperadora().getNomeOperadora() +" - "
				//		+ produtos.get(i).getCodigoProduto()+" - "+produtos.get(i).getPrecoCompraProduto()
					//	+" - "+produtos.get(i).getPrecoVendaProduto());
			}
			sessao.setAttribute("produtos", mapProdutos);
			resultado.addObject("msg", "( "+ddd+ " ) "+fone+", escolha o valor");
			resultado.addObject("produtos", produtos);
			resultado.addObject("ddd", ddd);
			resultado.addObject("fone", fone);
			resultado.addObject("operadora", operadora);
			resultado.setViewName("valores");
		}
		return resultado;
	}


	public ListaProdutosService getListaProdutosService() {
		return listaProdutosService;
	}

	public void setListaProdutosService(
			ListaProdutosService listaProdutosService) {
		this.listaProdutosService = listaProdutosService;
	}

	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
 
		
		ModelAndView model = new ModelAndView("erroRecarga");
		model.addObject("erro", new ErroException(EntityFabric.createErro("99", ex.getMessage()) ));
		
		return model;
 
	}
}
