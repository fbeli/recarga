package br.com.becb.middleware.controladores;





import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import  br.com.tst.JasonResponse;
import br.com.tst.UserAjax;
 
/**
 * @author Crunchify.com
 * 
 */
 
@Controller
public class AjaxController {
 
    @RequestMapping("/ajax")
    public ModelAndView helloAjaxTest() {
    	//ModelAndView mv = new ModelAndView();
        return new ModelAndView("testAjax", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
    }
 
    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    public @ResponseBody String getTime() { 
        Random rand = new Random();
        float r = rand.nextFloat() * 100;
        String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
        //System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
        return result;
    }
    
    @RequestMapping(value = "/ajaxtestNome", method = RequestMethod.GET)
    public @ResponseBody  String getNome() {
 
        return "Fred";
    }
    
    /**
     * Teste com formulário vindo da página
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/testeForm", method = RequestMethod.POST)
    public @ResponseBody JasonResponse addUser(@ModelAttribute(value="user") UserAjax user, 
    		BindingResult result ){
        JasonResponse res = new JasonResponse();
        ValidationUtils.rejectIfEmpty(result, "name", "Name can not be empty.");
        ValidationUtils.rejectIfEmpty(result, "education", "Educatioan not be empty");
     	
        
        
        if("Fred".equalsIgnoreCase(user.getName())){
        	res.setStatus("SUCCESS");
    		res.setResult( "Não é que o nome é Fred");
        }else{
        	res.setStatus("Fail");
    		res.setResult( "O nome veio errado");
        }
    		return res;
    }
    
}