package br.com.becb.middleware.controladores;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.becb.middlewarerecarga.entidades.Usuario;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.RecarregarService;
import br.com.becb.middlewarerecarga.servicos.TransacaoService;
import br.com.becb.middlewarerecarga.servicos.UserService;
import br.com.becb.middlewarerecarga.suporte.Suporte;

@Controller
public class HomeController {

	
	@Autowired
	UserService userService;
	
	@Autowired
	private	TransacaoService ts;
	@Autowired
	private RecarregarService rs;
	
	

	@RequestMapping("/")
	public ModelAndView index(Map<String, Object> model) throws InterruptedException {

		ModelAndView resultado = new ModelAndView();

		resultado.addObject("msg", "Escolha a operadora");
		
		resultado.setViewName("index");
		return resultado;
	}
	@RequestMapping("/logar")
	public String logar(Map<String, Object> model) throws InterruptedException {

	
		return "entrar";

	}

	@RequestMapping("/admin")
	
	public ModelAndView admin(Map<String, Object> model, HttpSession sessao) throws ErroException {

		ModelAndView resultado = new ModelAndView();

		resultado.addObject("data", Suporte.conveterData(new Date(), "dd/MM/yyyy hh:mm:ss"));
		resultado.addObject("usuarios", userService.getUsuarios());
		resultado.setViewName("admin");

		
		return resultado;

	}

	@RequestMapping("/logout")
	public ModelAndView logout(Map<String, Object> model, HttpSession sessao) throws ErroException {

		ModelAndView resultado = new ModelAndView();

		Usuario user = (Usuario)sessao.getAttribute("usuario");
		
		ts.cancelarRecargas(ts.buscaTransacoesPendentes(user));
		
		System.out.println("Saindo............");
		
		resultado.setViewName("logout");

		
		return resultado;

	}
}