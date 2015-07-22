package br.com.becb.middlewarerecarga.servicos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateErro;
import br.com.becb.middlewarerecarga.dao.hibernate.HibernateOperadora;
import br.com.becb.middlewarerecarga.dao.jdbc.JDBCSuporteDao;
import br.com.becb.middlewarerecarga.entidades.EntityFabric;
import br.com.becb.middlewarerecarga.entidades.Erro;
import br.com.becb.middlewarerecarga.entidades.Operadora;
import br.com.becb.middlewarerecarga.entidades.Produto;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.cliente.rv.RecargaParseXML;
import br.com.becb.middlewarerecarga.suporte.Suporte;

@Configuration
//@PropertySource("classpath:rv.properties")
@PropertySource("file:${CATALINA_BASE}\\conf\\recarga.properties")
@Component("consultaRV")
public class ConsultaRV {

	//@Value("${url}")
	private String url;
	//@Value("${loja_primaria}")
	private String loja;
	//@Value("${senha_primaria}")
	private String senha;
	//@Value("${nome_primario}")
	private String nome_primario;

	
	@Value("${versao}")
	private String versao;
	@Value("${recargaOnline}")
	private String codRecargaOnline;
	@Value("${confirmaTransacao}")
	private String confirmaTransacao;

	@Value("${consultaStatus}")
	private String consultaStatus;
	@Value("${listaTransacoesPendentes}")
	private String listaTransacoesPendentes;

	
	@Autowired
	private RecargaParseXML parse;
	@Autowired
	private HibernateErro<Erro> daoErro;
	@Autowired
	private HibernateOperadora<Operadora> daoOperadora;

	@Autowired
	private JDBCSuporteDao jdbcSuporteDao;
	
	
	/**
	 * Incluso para poder ler o arquivo de propriedades
	 * @return
	 */
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
	
	public ConsultaRV() {
		
	}
	
	private void carregarParametros(){

		if(this.nome_primario == null || this.nome_primario.equals("")){
			jdbcSuporteDao.carregaConfiguracoes();
			loja = Suporte.loja;
			nome_primario = Suporte.nome;
			senha =  Suporte.senha;
			url = Suporte.url;
			//System.out.println(url);
			
		}
	}
	public List<NameValuePair> carregaParametros(List<NameValuePair> params){
		
		this.carregarParametros();
		
		params.add(new BasicNameValuePair("nome_primario", this.nome_primario));
		params.add(new BasicNameValuePair("loja_primaria", this.loja));
		params.add(new BasicNameValuePair("senha_primaria", this.senha));
		params.add(new BasicNameValuePair("versao", versao));
		
		return params;
	}

	@SuppressWarnings("finally")
	/**
	 * Consulta padr�o ao cliente CellCard
	 * @param params
	 * @return
	 */
	public String fazerConsulta(List<NameValuePair> params) {
		String responseBody = "";

		// criar por interceptor

		params = carregaParametros(params);
		
		if (getCodigoTransacao() != "")
			params.add(new BasicNameValuePair("codigo_transacao",	getCodigoTransacao()));

		//this.imprimeParams(params);
		try {
			HttpClient httpclient = HttpClients.createDefault();
			this.carregarParametros();
			HttpPost httppost = new HttpPost(url);

			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			responseBody = httpclient.execute(httppost, responseHandler);

		} catch (HttpHostConnectException he) {

			daoErro.persistir(EntityFabric
					.createErro(200,
							"N�o foi poss�vel conectar ao servidor de consultas de servi�os"));
		} catch (Exception e) {
			e.printStackTrace(); // TODO: handle exception
		} finally {
			//Logar.info(responseBody);
			return responseBody;
		}

	}

	private void imprimeParams(List<NameValuePair> params) {

		System.out.println("*********************************");
		for (int i = 0; i < params.size(); i++) {

			System.out.println(params.get(i).getName() + " - "
					+ params.get(i).getValue());

		}

	}

	private String fazerConsulta(String codigoTransacao) throws ErroException {
		HttpClient httpclient = HttpClients.createDefault();
		carregarParametros();
		HttpPost httppost = new HttpPost(url);
		try {
			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params = carregaParametros(params);
			params.add(new BasicNameValuePair("codigo_transacao",
					codigoTransacao));
			params.add(new BasicNameValuePair("versao", this.versao));
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httppost, responseHandler);
			//salvarRetorno(responseBody);
			return responseBody;
		} catch (HttpHostConnectException he) {

			daoErro.persistir(EntityFabric
					.createErro(200,
							"N�o foi poss�vel conectar ao servidor de consultas de servi�os"));
		} catch (Exception e) {
			e.printStackTrace(); // TODO: handle exception
		}

		return null;
	}

	private void salvarRetorno(String conteudo) {

		FileWriter fw;
		BufferedWriter bw;
		try {
			File file = new File("XML_Operadoras.txt");

			System.out.println(" Impresso em: "+file.getAbsoluteFile());
			
			if (!file.exists())
				file.createNewFile();

			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(conteudo);

			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * 
	 * @param codigoTransacao
	 *            - Saber o tipo de consulta deve ser feito. ver em properties
	 * @throws ErroException 
	 */
	public String atualizarListaProdutos(String codigoTransacao) throws ErroException {
		
		StringBuffer str = new StringBuffer();
		try {
			Logar.info("******************\n\nIniciando Leitura no sistemas de Recarga");
			String xml = this
					.fazerConsulta(codigoTransacao);
			Logar.info("Lido Sistema de Recarga");
			Logar.info(xml);
			List<Operadora> operadoras = parse.parseOperadora(xml);
			/**
			 * if(null!=operadoras){ for(int i =0 ;i<operadoras.size();i++){
			 * daoOperadora.persistir(operadoras.get(i)); }
			 */
			if(null!=operadoras){
				Logar.info("Total de Operadoras:"+operadoras.size());
				str.append("Adicionadas " + operadoras.size());
				for (Operadora operadora : operadoras) {
					str.append("\n"+operadora.getNomeOperadora());
					for(Produto p: operadora.getListProdutos())
						str.append("\n"+"    "+p.getCodigoProduto()+"  "+p.getPrecoVendaProduto() );
				}
				Logar.info(str.toString());
			}
		} catch (ErroException e) {
			daoErro.persistir(e.getErro());
			e.printStackTrace();
		}

		return str.toString();
	}

	private static String classeChamadora() {
		Throwable thr = new Throwable();
		thr.fillInStackTrace();
		StackTraceElement[] ste = thr.getStackTrace();
		return ste[3].getMethodName();
	}

	public String getCodigoTransacao() {
		String codigo = "";
		String classeChamadora = classeChamadora();
		switch (classeChamadora) {
		case "buscaTransacoesPendentes":
			codigo = listaTransacoesPendentes;
			break;

		case "buscaStatusTransacao_CodOnline":
			codigo = consultaStatus;
			break;

		case "buscaStatusTransacao_idRecarga":
			codigo = consultaStatus;
			break;

		default:
			break;
		}

		return codigo;
	}

	@Required
	@Autowired
	public void setParse(RecargaParseXML parse) {
		this.parse = parse;
	}

	public RecargaParseXML getParse() {
		return parse;
	}

	public HibernateErro<Erro> getDaoErro() {
		return daoErro;
	}

	public void setDaoErro(HibernateErro<Erro> daoErro) {
		this.daoErro = daoErro;
	}

	public HibernateOperadora<Operadora> getDaoOperadora() {
		return daoOperadora;
	}

	public void setDaoOperadora(HibernateOperadora<Operadora> daoOperadora) {
		this.daoOperadora = daoOperadora;
	}
}
