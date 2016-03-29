package br.com.becb.moip;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.moip.client.instruction.Address;
import br.com.moip.client.instruction.Payer;
import br.com.moip.client.instruction.SendInstruction;
import br.com.moip.client.instruction.SingleInstruction;
import br.com.moip.client.instruction.Values;
import br.com.moip.client.response.SendSingleInstructionResponse;
import br.com.moip.client.send.SandboxMoip;

@Configuration
@PropertySource("file:${CATALINA_BASE}\\conf\\recarga.properties")
@Component("moipService")
public class MoipService {

	// @Value("${moip.token}")
	private String token = "ZGJT7OF9O9Y71ODEUHGN0TCJNVUIZOZC";

	// @Value("${moip.key}")
	private String key = "WWGPFXQBJL4CVDEMLWRZ9ZKXKGCHNRVRVV3MFHZA";

	public MoipService() {

	}



	public SandboxMoip authService() {

		SandboxMoip sbm = new SandboxMoip();
		sbm.withToken(token).withKey(key);

		return sbm;

		// new ProductionMoip().withToken(token).withKey(key);
	}

	public MoipServiceResponse sendXML() {
		MoipServiceResponse resposta = null;
		
		
		SendInstruction enviarInstrucao = new SendInstruction()
				.withSingleInstruction(new SingleInstruction()
						.withTransparentValidation()
						.withUniqueId(new Date().getDay()+"d")
						.with(new Values().withValue("100.00"))
						.withReason("Razão / Motivo do pagamento")
						.with(new Payer()
								.withName("Nome Sobrenome")
								.withEmail("email@cliente.com.br")
								.withPayerID("111")
								.withBillingAddress(
										new Address()
												.withAddress(
														"Rua do Zézinho Coração")
												.withNumber("45")
												.withComplement("z")
												.withCity("São Paulo")
												.withNeighborhood("Palhaço Jão")
												.withState("SP")
												.withCountry("BRA")
												.withZipCode("01230-000")
												.withPhone("(11)8888-8888"))));
		enviarInstrucao.validate();
		
			
			SandboxMoip sm =  new SandboxMoip();
		try {	
			SendSingleInstructionResponse response = sm
					.withToken(token).withKey(key).send(enviarInstrucao);

			/** Resposta a requisição */

			boolean successful = response.isSuccessful();
			String token = null;
			List<br.com.moip.client.response.Error> errors = null;
			if (successful) {
				token = response.getResponse().getToken();
			} else {
				errors = response.getResponse().getErrors();
			}
			
			resposta = new MoipServiceResponse(successful, token, errors);
			
			    
		} catch (Exception e) {
			e.printStackTrace();
		}catch(Throwable t){
			t.printStackTrace();
		}finally{
			return resposta;
		}
	}

}
