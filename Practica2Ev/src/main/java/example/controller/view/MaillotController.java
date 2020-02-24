package example.controller.view;

import java.util.List;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import example.model.MaillotModel;
import example.service.MaillotService;
import example.constantes.Constantes;
import example.validator.MaillotValidator;

@Controller
@RequestMapping("/practica")
public class MaillotController {

	@Autowired
	@Qualifier("maillotServiceImpl")
	private MaillotService maillotService;
	
	private MaillotValidator validator=new MaillotValidator();
	
	private static final Log LOGGER=LogFactory.getLog(MaillotController.class);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/maillots")
	public ModelAndView listMaillots() {
		ModelAndView mav=new ModelAndView(Constantes.MAILLOT_VIEW);
		mav.addObject("maillots",maillotService.listAllMaillot());
		LOGGER.info("\nEstamos en el método para mostrar maillot\nNos devuelve la vista "+Constantes.MAILLOT_VIEW);
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/Maillotform")
	public String formularioMaillot(@RequestParam(name="codigo") String codigo, Model model) {
		MaillotModel maillotModel=new MaillotModel();
		if(codigo!="") {
			List<MaillotModel> list=maillotService.listAllMaillot();
			for(MaillotModel mmodel:list) {
				if(mmodel.getCodigo().equals(codigo)) {
					maillotModel=mmodel;
				}
			}
		}
		model.addAttribute("maillot",maillotModel);	
		return Constantes.MAILLOT;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addMaillot")
	public ModelAndView addMaillot(@Valid @ModelAttribute("maillot") MaillotModel maillotModel, BindingResult result, RedirectAttributes flash,Model model) {
		ModelAndView mav=new ModelAndView();
		this.validator.validate(maillotModel, result);
		if(result.hasErrors()) 
		{
			mav.setViewName(Constantes.MAILLOT);
		}else {
			LOGGER.info("Agregando");
			maillotService.addMaillot(maillotModel);
			mav.setViewName("redirect:/practica/maillots");
			flash.addFlashAttribute("success","Añadido");
		}			
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/removeMaillot")
	public String removeMaillot(@RequestParam(name="codigo") String codigo, RedirectAttributes flash) {
		maillotService.removeMaillot(codigo);
		flash.addFlashAttribute("success","Eliminado con exito");	
		return "redirect:/practica/maillots";
	}

	
}


	