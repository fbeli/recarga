package br.com.becb.middleware.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.becb.middlewarerecarga.servicos.BOService;


@Controller
public class BOController {

	@Autowired
	BOService bs;
	
	@RequestMapping(value = "admin/cancelarPendentes")
	public void CancelarRecargasPendentes(){
		
		String ret;
		ModelAndView retorno =  new ModelAndView();
		ret =  bs.cancelarRecargasPendentes();
		
		
		retorno.addObject("status", ret);
		retorno.setViewName("resultadoAtualizacao");
		
	}
}
