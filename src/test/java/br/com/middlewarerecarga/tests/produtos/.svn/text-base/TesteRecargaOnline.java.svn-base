package br.com.middlewarerecarga.tests.produtos;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateRecarga;
import br.com.becb.middlewarerecarga.entidades.Recarga;
import br.com.becb.middlewarerecarga.entidades.StatusRecargaServer;
import br.com.becb.middlewarerecarga.entidades.Transacoes;
import br.com.becb.middlewarerecarga.exceptions.ConfirmacaoDeTransacaoException;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.RecarregarService;
import br.com.becb.middlewarerecarga.servicos.TransacaoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/springTests.xml" })
public class TesteRecargaOnline {

	@Autowired
	RecarregarService recarregarService;

	@Autowired
	Carga carga;
	@Autowired
	HibernateRecarga<Recarga> hDaoRecarga;

	@Autowired
	TransacaoService transacaoService;
	
	//@Test
	public void testeRecargaOnline() throws ErroException {

		Recarga recarga = recarregarService.executarRecarga("M5", "21",
				AjustaParametros.getFone(), "172.0.0.1", "usuarioTeste");
		// TODO: problema para recarregar, o retorno não é confirmado.
		
		System.err.println("Codigo: "+recarga.getCodOnline());

		try {
			recarga = recarregarService.confirmarRecarga(recarga, "0");
		} catch (ConfirmacaoDeTransacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		transacaoService.ajustarStatusTransacao(recarga);
		
		System.out.println("cod:" + recarga.getCodOnline() + " status:"
				+ recarga.getStatusRecarga()+ " status:"
						+ recarga.getStatusRecargaServer());
	}

	@Test
	public void verificaPedidos() throws ErroException{
		
		@SuppressWarnings("deprecation")
		Date dataInicio =new Date(2015-1900, 03, 22);
		Date dataFim =new Date(2015-1900, 03, 22);
		String nome ="fred belisario";
		//dataInicio = new Date("20150424");
		//dataFim = new Date("20150424");
		Transacoes recargas;
		if(!dataInicio.equals(dataFim))
			recargas = transacaoService.getRecargaPorUserEData(nome, dataInicio, dataFim);
		else
			recargas = transacaoService.getRecargaPorUserNaData(nome, dataInicio);
		

		 recargas  = transacaoService.getRecargaPorUserEData("fred belisario",dataInicio , dataFim );
		System.out.println("Quantidade de recargas: "+ recargas.getRecargasOnline().size());
		for (Iterator recargast = ((List<Recarga>) recargas).iterator(); recargast.hasNext();) {
			Recarga recarga = (Recarga) recargast.next();
			System.out.println(recarga.getUsuarioLocal() +" "+recarga.getCompra() +" "+recarga.getStatusRecarga() +" "+recarga.getStatusRecargaServer() +" "+
					recarga.getDataDeSolicitacao() +" ");
			
		}
		
		new Thread(carga).run();
		new Thread(carga).run();
		new Thread(carga).run();
		new Thread(carga).run();
		new Thread(carga).run();
		new Thread(carga).run();
		new Thread(carga).run();
					
		
	}
	// @Test
	 public void testeCancelarRecargaOnline() throws ErroException {

			Recarga recarga = recarregarService.executarRecarga("M5", "21",
					AjustaParametros.getFone(), "172.0.0.1", "usuarioTeste");
			// TODO: problema para recarregar, o retorno não é confirmado.
			
			System.err.println("Codigo: "+recarga.getCodOnline());

			try {
				recarga = recarregarService.confirmarRecarga(recarga, "1");
			} catch (ConfirmacaoDeTransacaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			transacaoService.ajustarStatusTransacao(recarga);
			
			System.out.println("cod:" + recarga.getCodOnline() + " status:"
					+ recarga.getStatusRecarga()+ " status:"
							+ recarga.getStatusRecargaServer());
		}

	//@Test
	public void testeRecargaPin() throws ErroException {

		Recarga recarga = recarregarService.executarRecargaPIN("1099", "RJ",
				 "172.0.0.1", "usuarioTeste");
		// TODO: problema para recarregar, o retorno não é confirmado.
		try {
			recarga = recarregarService.confirmarRecarga(recarga, "0");
			
		} catch (ConfirmacaoDeTransacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		transacaoService.ajustarStatusTransacao(recarga);
		
		System.out.println("cod:" + recarga.getCodOnline() + " status:"
				+ recarga.getStatusRecarga()+ " status:"
						+ recarga.getStatusRecargaServer());
	}
	
	//@Test
	public void testeCadastrarRecargaOnline() throws ErroException {

		Recarga recarga = recarregarService.executarRecarga("M5", "21",
				AjustaParametros.getFone(), "172.0.0.1", "usuarioTeste");

	}

	//@Test
	public void pegarRecargas() throws ErroException {

		List<Recarga> recargas = hDaoRecarga.getRecargaPorFone("21",
				"987654321");

		for (int i = 0; i < recargas.size(); i++) {
			if (recargas.get(i).getStatusRecargaServer() == null && recargas.get(i)
					.getCodOnline() != null) {
				String status = transacaoService
						.buscaStatusTransacao_CodOnline(recargas.get(i)
								.getCodOnline());
				
				switch (status) {
					case "00":
						recargas.get(i).setStatusRecargaServer(
								StatusRecargaServer.CONFIRMADO);
						break;
					case "01":
						recargas.get(i).setStatusRecargaServer(
								StatusRecargaServer.INEXISTENTE);
						break;
					case "02":
						recargas.get(i).setStatusRecargaServer(
								StatusRecargaServer.PENDENTE);
						break;
					case "03":
						recargas.get(i).setStatusRecargaServer(
								StatusRecargaServer.ESTORNADA);
						break;
	
					}
				
				if (recargas.get(i).getStatusRecargaServer() != null)
					 hDaoRecarga.merge(recargas.get(i));
			}
		}
		
		for (int i = 0; i < recargas.size(); i++) {
			System.out.println(recargas.get(i).getDdd()+" "+ recargas.get(i).getFone()
					+" - "+recargas.get(i).getCodOnline()+" - "+recargas.get(i).getId()
					+" - "+recargas.get(i).getDataDeSolicitacao()+" - "+recargas.get(i).getDataDeconfirmacao()
					+" - "+recargas.get(i).getStatusRecarga()+" - "+recargas.get(i).getStatusRecargaServer());
		
		}

	}
	
}