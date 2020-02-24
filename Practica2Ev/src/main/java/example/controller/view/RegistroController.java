package example.controller.view;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.constantes.Constantes;
import example.crypt.TestCrypt;
import example.model.UserModel;
import example.model.UserRoleModel;
import example.service.UserRoleService;
import example.service.UserService;
import example.validator.RegistroValidator;


@Controller
@RequestMapping("/practica")
public class RegistroController {
	
	@Autowired
	@Qualifier("UserServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("userRoleServiceImpl")
	private UserRoleService userRoleService;
	
	private TestCrypt encrypt=new TestCrypt();
	
	private RegistroValidator validate=new RegistroValidator();
	private static final Log LOGGER = LogFactory.getLog(CiclistaController.class);

	@GetMapping("/registro")
	public String registrar(Model model) {
		UserModel userModel=new UserModel();
		model.addAttribute("user",userModel);
		return Constantes.REGISTRO_VIEW;
	}
	
	@PostMapping("/addUser")
	public ModelAndView addUser(@ModelAttribute("user") UserModel userModel,UserRoleModel userRoleModel, Model model, BindingResult result, RedirectAttributes flash) {
		ModelAndView mav=new ModelAndView();
		LOGGER.info(userModel.getUsername());
		this.validate.validate(userModel, result);
		if(result.hasErrors() || userService.checkUser(userModel.getUsername())) {
			if(userService.checkUser(userModel.getUsername())) {
				model.addAttribute("error",1);
			}
			mav.setViewName(Constantes.REGISTRO_VIEW);
		}else {
			userModel.setPassword(encrypt.generatepassword(userModel.getPassword()));
			userModel.setEnabled(true);
			userService.save(userModel);
			userRoleModel.setUser(userModel);
			userRoleService.addUserRole(userRoleModel);
			mav.setViewName("redirect:/practica/login");
			flash.addFlashAttribute("success","User registrado");
		}
		return mav;
	}
}