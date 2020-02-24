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
import example.model.*;
import example.service.*;
import example.validator.PuertoValidator;
import example.constantes.Constantes;

@Controller
@RequestMapping("/practica")
public class PuertoController {


	@Autowired
	@Qualifier("puertoServiceImpl")
	private PuertoService puertoService;
	
	@Autowired
	@Qualifier("etapaServiceImpl")
	private EtapaService etapaService;
	
	@Autowired
	@Qualifier("ciclistaServiceImpl")
	private CiclistaService ciclistaService;
	
	private PuertoValidator validator=new PuertoValidator();
	
	private static final Log LOGGER = LogFactory.getLog(PuertoController.class);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/puertos")
	public ModelAndView listPuertos() {
		ModelAndView mav=new ModelAndView(Constantes.PUERTO_VIEW);
		mav.addObject("puertos",puertoService.listAllPuerto());
		LOGGER.info("\nEstamos en el método para mostrar etapas\nNos devuelve la vista "+Constantes.PUERTO_VIEW);
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/Puertoform")
	public String formularioPuerto(@RequestParam(name="nompuerto") String nompuerto, Model model) {
		PuertoModel puertoModel=new PuertoModel();
		if(nompuerto!="") {
			List<PuertoModel> list=puertoService.listAllPuerto();
			for(PuertoModel pmodel:list) {
				if(pmodel.getNompuerto().equals(nompuerto)) {
					puertoModel=pmodel;
				}
			}
		}
		model.addAttribute("puerto",puertoModel);
		model.addAttribute("ciclistas", ciclistaService.listAllCiclista());
		model.addAttribute("etapas", etapaService.listAllEtapa());
		return Constantes.PUERTO;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/removepuerto")
	public String removePuerto(@RequestParam("nompuerto") String nompuerto) {
		LOGGER.info("Borrando");
		puertoService.removePuerto(nompuerto);
		return "redirect:/practica/puertos";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addPuerto")
	public ModelAndView addPuerto(@RequestParam(name="etapa") int etapa, @RequestParam(name="nombre") String nombre, @Valid @ModelAttribute("puerto") PuertoModel puertoModel,  BindingResult result, RedirectAttributes flash, Model model) {
		ModelAndView mav=new ModelAndView();
		EtapaModel etapaModel=new EtapaModel();
		List<EtapaModel> listE=etapaService.listAllEtapa();
		for(EtapaModel emodel:listE) {
			if(emodel.getNetapa()==etapa) {
				etapaModel=emodel;
			}
		}

		CiclistaModel ciclistaModel=new CiclistaModel();
		List<CiclistaModel> listC=ciclistaService.listAllCiclista();
		for(CiclistaModel cmodel:listC) {
			if(cmodel.getNombre().equals(nombre)) {
				ciclistaModel=cmodel;
			}
		}
		
		this.validator.validate(puertoModel, result);
		if(result.hasErrors()) {
			mav.setViewName(Constantes.PUERTO);
			model.addAttribute("ciclistas", ciclistaService.listAllCiclista());
			model.addAttribute("etapas", etapaService.listAllEtapa());
		}else {
			LOGGER.info("Agregando");
			puertoModel.setNetapa(etapaModel);
			puertoModel.setDorsal(ciclistaModel);
			puertoService.addPuerto(puertoModel);
			mav.setViewName("redirect:/practica/puertos");
			flash.addFlashAttribute("success","Añadido");
		}
		return mav;
	}
	
}


	