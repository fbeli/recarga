package br.com.middlewarerecarga.tests.produtos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.becb.middlewarerecarga.entidades.Produto;
import br.com.becb.middlewarerecarga.entidades.Recarga;
import br.com.becb.middlewarerecarga.exceptions.ConfirmacaoDeTransacaoException;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.exceptions.ProdutoNaoExisteException;
import br.com.becb.middlewarerecarga.servicos.AdicionarProdutosService;
import br.com.becb.middlewarerecarga.servicos.ListaProdutosService;
import br.com.becb.middlewarerecarga.servicos.RecarregarService;
import br.com.becb.middlewarerecarga.servicos.TransacaoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/springTests.xml" })
public class HomologaRecarga {

	@Autowired
	AdicionarProdutosService adicionarProdutosService;

	@Autowired
	ListaProdutosService listaProdutosService;

	@Autowired
	RecarregarService<Recarga> recarregarService;
	@Autowired
	TransacaoService transacaoservice;

	@Test
	public void homologaRecarga() throws ErroException {

		/*
		 *  1 consulta de valores (informar os valores disponibilizados)  5
		 * transações PIN – CONFIRMADO  5 transações ONLINE – CONFIRMADA  5
		 * transações ONLINE – CANCELADA  1 transacao ONLINE – CONFIRMADA –
		 * produto de valor variável (utilize produto 1180)
		 */

		// Executar adição de produtos.
		 adicionarProdutos();
		 listarProdutos();

		// Criar transações
		//List<Recarga> recargas = criarRecargas();

		// TODO:5 transações ONLINE – CONFIRMADA
		//confirmarRecarga(recargas);
		// TODO:5 transações ONLINE – CANCELADA
		//cancelarRecarga(recargas);

		//listarRecargas(recargas, " Online ");
		// TODO:5 transações ONLINE – produto de valor variável (utilize produto
		// 1180)

		// TODO: 5 transações PIN – CONFIRMADO

		//listarRecargas(criarRecargaPin(), " PIN ");

	}

	private boolean adicionarProdutos() throws ErroException {
		adicionarProdutosService.adicionarProdutos();

		return true;
	}

	private List<Recarga> criarRecargas() throws ErroException {

		List<Recarga> recargas = new ArrayList<Recarga>();

		String[] codigosRecarga = { "1344", "1055", "M35", "M14", "1421",
				"1376", "M47", "M7", "M40", "M24" };
		Recarga recarga = new Recarga();
		for (int i = 0; i < 10; i++) {
			try {
				recarga = recarregarService.executarRecarga(codigosRecarga[i],
						"21", AjustaParametros.getFone(), "172.0.0.1",
						"usuarioTeste");
			} catch (ProdutoNaoExisteException e) {
				
				e.printStackTrace();
			}

			recargas.add(recarga);
		}
		return recargas;
	}

	private List<Recarga> confirmarRecarga(List<Recarga> recargas) throws ErroException {

		for (int i = 0; i < 5; i++) {
			try {
				recarregarService.confirmarRecarga(recargas.get(i), "0");
			} catch (ProdutoNaoExisteException e) {
			
				e.printStackTrace();
			} catch (ConfirmacaoDeTransacaoException e) {
				
				e.printStackTrace();
			}

			transacaoservice.ajustarStatusTransacao(recargas.get(i));
		}
		return recargas;
	}

	private List<Recarga> cancelarRecarga(List<Recarga> recargas) throws ErroException {

		for (int i = 5; i < 10; i++) {
			try {
				recarregarService.confirmarRecarga(recargas.get(i), "1");
			} catch (ProdutoNaoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ConfirmacaoDeTransacaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			transacaoservice.ajustarStatusTransacao(recargas.get(i));
		}
		return recargas;
	}

	private List<Recarga> criarRecargaPin() throws ErroException {

		List<Recarga> recargas = new ArrayList<Recarga>();

		String[] codigosRecarga = {"468", "1099", "245", "245", "468"};//{ "562", "1188", "878", "792", "151" };
		Recarga recarga = new Recarga();
		for (int i = 0; i < 5; i++) {
			try {

				recarga = recarregarService.executarRecargaPIN(
						codigosRecarga[i], "RJ", "172.0.0.1", "usuarioTeste");
				recarregarService.confirmarRecarga(recarga, "0");

			} catch (ProdutoNaoExisteException e) {

				e.printStackTrace();
			} catch (ConfirmacaoDeTransacaoException e) {
				
				e.printStackTrace();
			}

			transacaoservice.ajustarStatusTransacao(recarga);
			recargas.add(recarga);
		}
		return recargas;
	}

	private boolean listarProdutos() throws ErroException {
		List<Produto> produtos = listaProdutosService.listarTodosProdutos();
		String strProdutos = "";

		try {
		for (int i = 0; i < produtos.size(); i++) {
			strProdutos = "\nOperadora: "
					+ produtos.get(i).getOperadora().getNomeOperadora()
					+ " código: " + produtos.get(i).getCodigoProduto()
					+ " valor máximo: " + produtos.get(i).getValorMaximoProduto()
					+ "\n";
			
			escreverArquivo("operadoras", strProdutos);
		}

		
		
		} catch (IOException e) {
		
			e.printStackTrace();
		}

		return true;
	}

	private void listarRecargas(List<Recarga> recargas, String tipo) {
		try {
			String strProdutos;
			for (int i = 0; i < recargas.size(); i++) {
				strProdutos = "\n"
						+ tipo
						+ "- status CellCard: "
						+ recargas.get(i).getStatusRecargaServer()
						+ "- status Local: "
						+ recargas.get(i).getStatusRecarga()
						+ " - Operadora: "
						+ recargas.get(i).getProduto().getOperadora()
								.getNomeOperadora() + "- código: "
						+ recargas.get(i).getCodOnline() ;

				escreverArquivo("recargas", strProdutos);
			}

		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	private void escreverArquivo(String nome, String conteudo)
			throws IOException {

		File file = new File(nome + ".txt");
		if (!file.exists())
			file.createNewFile();

		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(conteudo);
		bw.newLine();

		bw.close();
		fw.close();

	}
}
