package example.controller.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.constantes.Constantes;
import example.crypt.TestCrypt;
import example.entity.User;
import example.model.UserModel;
import example.model.UserRoleModel;
import example.service.impl.UserRoleServiceImpl;
import example.service.impl.UserServiceImpl;


@Controller
@RequestMapping("/practica")
public class RegistroController {
	
	@Autowired
	@Qualifier("UserServiceImpl")
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	@Qualifier("userRoleServiceImpl")
	private UserRoleServiceImpl userRoleImpl;
	
	private TestCrypt enc=new TestCrypt();

	
	@GetMapping("/registro")
	public ModelAndView registrar() {
		ModelAndView mav =new ModelAndView(Constantes.REGISTRO_VIEW);
		mav.addObject("user",new User());
		return mav;
	}
	
	@PostMapping("/addUser")
	public ModelAndView addUser(@ModelAttribute("user") UserModel userModel,@RequestParam(name="patata")  String rol, UserRoleModel userRoleModel, Model model, RedirectAttributes flash) {
		ModelAndView mav = new ModelAndView();
		userModel.setPassword(enc.generatepassword(userModel.getPassword()));
		userServiceImpl.addUser(userModel);
		userRoleModel.setRole(rol);
		userRoleModel.setUser(userModel);
		userRoleImpl.addUsuario(userRoleModel);
		mav.setViewName("redirect:/practica/login");
		return mav;
	}
}
