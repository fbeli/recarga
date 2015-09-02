package br.com.becb.middleware.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.becb.moip.MoipService;
import br.com.becb.moip.MoipServiceResponse;

@Controller
public class MoipController {

	@Autowired
	MoipService ms;
	
	public MoipController() {
	
	}
	
	@RequestMapping("/moip/configure")
	public ModelAndView atualizarProdutos() {
		
		ModelAndView resultado = new ModelAndView();
		MoipServiceResponse mr = ms.sendXML();
		
		resultado.setViewName("pagamento_moip");
		resultado.addObject("mr", mr);
		
		return resultado;
	}

}
