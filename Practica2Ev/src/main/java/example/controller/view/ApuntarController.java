package example.controller.view;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
import example.model.*;
import example.service.*;

@Controller
@RequestMapping("/practica")
public class ApuntarController {
	@Autowired
	@Qualifier("llevarServiceImpl")
	private LlevarService llevarService;
	
	@Autowired
	@Qualifier("etapaServiceImpl")
	private EtapaService etapaService;
	
	@Autowired
	@Qualifier("ciclistaServiceImpl")
	private CiclistaService ciclistaService;
	
	@Autowired
	@Qualifier("maillotServiceImpl")
	private MaillotService maillotService;
	
	@GetMapping("/llevars") 
	public ModelAndView Llevars() { 
		ModelAndView mav=new ModelAndView(Constantes.LLEVARS_VIEW);
		mav.addObject("llevars",llevarService.listAllLlevar());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/apuntar")
	public String apuntar(Model model) {
		LlevarModel llevarModel=new LlevarModel();
		model.addAttribute("llevar",llevarModel);
		model.addAttribute("ciclistas",ciclistaService.listAllCiclista());
		model.addAttribute("etapas",etapaService.listAllEtapa());
		model.addAttribute("maillots",maillotService.listAllMaillot());
		return Constantes.APUNTAR_VIEW;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addLlevar")
	public ModelAndView addLlevar(@RequestParam(name="etapa") int etapa, @RequestParam(name="nombre") String nombre, @RequestParam(name="maillot") String maillot, @Valid @ModelAttribute("llevar") LlevarModel llevarModel, RedirectAttributes flash, Model model) {
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
			MaillotModel maillotModel=new MaillotModel();
			List<MaillotModel> listM=maillotService.listAllMaillot();
			for(MaillotModel mmodel:listM) {
				if(mmodel.getCodigo().equals(maillot)) {
					maillotModel=mmodel;
				}
			}
			if(llevarService.check(ciclistaModel.getId(), etapaModel.getNetapa(), maillotModel.getCodigo())) {
				model.addAttribute("error",1);
				mav.setViewName(Constantes.APUNTAR_VIEW);
				model.addAttribute("ciclistas",ciclistaService.listAllCiclista());
				model.addAttribute("etapas",etapaService.listAllEtapa());
				model.addAttribute("maillots",maillotService.listAllMaillot());
			}else {
				llevarModel.setDorsal(ciclistaModel);
				llevarModel.setCodigo(maillotModel);
				llevarModel.setNetapa(etapaModel);
				llevarService.addLlevar(llevarModel);
				mav.setViewName("redirect:/practica/llevars");
				flash.addFlashAttribute("success","Añadido");
			}
			return mav;
		}
}
