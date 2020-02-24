package example.controller.view;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import example.constantes.Constantes;

@Controller
@RequestMapping("/practica")
public class LoginController {
	private static final Log LOGGER=LogFactory.getLog(LoginController.class);
	
	@GetMapping("/login")
	public String showLoginForm(Model model, @RequestParam(name="error", required=false) String error,  @RequestParam(name="logout", required=false) String logout) 
	{
		LOGGER.info("showLoginForm");
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return Constantes.LOGIN_VIEW;
	}
	
	@GetMapping("/loginsuccess")
	public String loginCheck()
	{
		LOGGER.info("logincheck");
		return "redirect:/";
	}
}
