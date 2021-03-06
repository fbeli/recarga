package br.com.becb.middlewarerecarga.servicos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateDDDProduto;
import br.com.becb.middlewarerecarga.dao.hibernate.HibernateErro;
import br.com.becb.middlewarerecarga.dao.hibernate.HibernateProduto;
import br.com.becb.middlewarerecarga.entidades.DDDProduto;
import br.com.becb.middlewarerecarga.entidades.EntityFabric;
import br.com.becb.middlewarerecarga.entidades.Erro;
import br.com.becb.middlewarerecarga.entidades.Produto;
import br.com.becb.middlewarerecarga.exceptions.ConfirmacaoDeTransacaoException;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.exceptions.ImpossivelCarregarProdutosException;
import br.com.becb.middlewarerecarga.suporte.OrdenaConjunto;
import br.com.becb.middlewarerecarga.suporte.Suporte;

@Component("listaProdutosService")
public class ListaProdutosService {

	@Autowired
	private HibernateDDDProduto<DDDProduto> hDaoDDDProduto;

	@Autowired
	private HibernateProduto<Produto> hDaoProduto;

	@Autowired
	private HibernateErro<Erro> hDaoErro;

	@Autowired
	private ErroService erroService;
	
	static Logger log = Logger.getLogger(ListaProdutosService.class.getName());

	public ListaProdutosService() {
		// TODO Auto-generated constructor stub
	}

	
	public List<Produto> buscarProdutos(String ddd, String operadora)
			throws ImpossivelCarregarProdutosException {
		Erro erro;
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			
			DDDProduto dddProduto = this.buscarDDD(ddd);
			
			Iterator<Produto> it = dddProduto.getProdutos().iterator();
			Produto prod;

			if(operadora == null)
				return dddProduto.getProdutos();
			
			while (it.hasNext()) {
				prod = it.next();
				
			
				if (prod.getOperadora().getNomeOperadora().toUpperCase()
						.equals(operadora.toUpperCase())){
					if(prod.isAtivo()) 
							if( prod.getLucro()>Suporte.valorMinimo)
								produtos.add(prod);
				}
			}
			return OrdenaConjunto.ordenaProdutos(produtos);
		} catch (ImpossivelCarregarProdutosException i) {
			i.printStackTrace(); 
			throw i;
		} catch (Exception e) {
			e.printStackTrace();
			erro = erroService
					.createErro(
							"33",
							"Problemas para carregar lista de produtos. ListaProduto.buscarProduts(ddd,operadora) \n Favor informar ao suporte");
			throw new ImpossivelCarregarProdutosException(erro);
		}
	}

	/**
	 * Listar todos os produtos
	 * 
	 * @return Lista de Produto
	 * @throws ErroException
	 */
	public List<Produto> listarTodosProdutos() throws ErroException {
		return hDaoProduto.getTodosProdutos();
	}

	/**
	 * Busca os produtos para os DDDs, se der erro tenta novamente.
	 * @param ddd
	 * @return
	 * @throws NumberFormatException
	 * @throws ErroException
	 */
	private  DDDProduto buscarDDD(String ddd) throws NumberFormatException, ErroException{
		DDDProduto dddProduto = hDaoDDDProduto.buscarDDD(ddd);
		
			if (dddProduto == null || dddProduto.getProdutos() == null) {				
				Erro erro = erroService.createErro("33",	"Problemas para carregar lista de produtos.. \n Favor informar ao Suporte");
				log.info("Problemas para carregar lista de produtos.. \n Favor informar ao Suporte");
				throw new ImpossivelCarregarProdutosException(erro);
			}
		
		
		return dddProduto;
	}
	public HibernateDDDProduto<DDDProduto> gethDaoDDDProduto() {
		return hDaoDDDProduto;
	}

	public void sethDaoDDDProduto(HibernateDDDProduto<DDDProduto> hDaoDDDProduto) {
		this.hDaoDDDProduto = hDaoDDDProduto;
	}

	public HibernateErro<Erro> gethDaoErro() {
		return hDaoErro;
	}

	public void sethDaoErro(HibernateErro<Erro> hDaoErro) {
		this.hDaoErro = hDaoErro;
	}

}
