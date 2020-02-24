package example.controller.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import example.constantes.Constantes;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping("/")
	public ModelAndView Inicio() {
		ModelAndView mav=new ModelAndView(Constantes.INICIO_VIEW);
		User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username",user.getUsername());
		return mav;
	}
}
