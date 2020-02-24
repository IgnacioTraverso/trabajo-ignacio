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
import example.validator.EtapaValidator;
import example.model.*;
import example.service.*;
import example.constantes.Constantes;

@Controller
@RequestMapping("/practica")
public class EtapaController {

	@Autowired
	@Qualifier("etapaServiceImpl")
	private EtapaService etapaService;
	
	@Autowired
	@Qualifier("ciclistaServiceImpl")
	private CiclistaService ciclistaService;
	
	private EtapaValidator validator=new EtapaValidator();
	
	private static final Log LOGGER = LogFactory.getLog(EtapaController.class);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/etapas")
	public ModelAndView listEtapas() {
		ModelAndView mav=new ModelAndView(Constantes.ETAPA_VIEW);
		mav.addObject("etapas",etapaService.listAllEtapa());
		LOGGER.info("Lista etapas");
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/Etapaform")
	public String formularioEtapa(@RequestParam(name="netapa") int netapa, Model model) {
		EtapaModel etapaModel=new EtapaModel();
		if(netapa!=-1) {
			List<EtapaModel> list=etapaService.listAllEtapa();
			for(EtapaModel emodel:list) {
				if(emodel.getNetapa()==netapa) {
					etapaModel=emodel;
				}
			}
		}
		model.addAttribute("etapa",etapaModel);
		model.addAttribute("ciclistas", ciclistaService.listAllCiclista());
		return Constantes.ETAPA;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addEtapa")
	public ModelAndView addEtapa(@RequestParam(name="nombre") String nombre, @Valid @ModelAttribute("etapa") EtapaModel etapaModel,  BindingResult result, RedirectAttributes flash, Model model) {
		ModelAndView mav=new ModelAndView();
		CiclistaModel ciclistaModel=new CiclistaModel();
		List<CiclistaModel> list=ciclistaService.listAllCiclista();
		for(CiclistaModel cmodel:list) {
			if(cmodel.getNombre().equals(nombre)) {
				ciclistaModel=cmodel;
			}
		}

		this.validator.validate(etapaModel, result);
		if(result.hasErrors()) {
			mav.setViewName(Constantes.ETAPA);
			model.addAttribute("ciclistas", ciclistaService.listAllCiclista());
			
		}else {
			LOGGER.info("Agregando");
			etapaModel.setDorsal(ciclistaModel);
			etapaService.addEtapa(etapaModel);
			mav.setViewName("redirect:/practica/etapas");
			flash.addFlashAttribute("success","Añadido");
		}
		return mav;
	}
	
	@PostMapping("/removeEtapa")
	public String removeEtapa(@RequestParam("netapa") int netapa) {
		LOGGER.info("Borrando");
		etapaService.removeEtapa(netapa);
		return "redirect:/practica/etapas";
	}
	
}


